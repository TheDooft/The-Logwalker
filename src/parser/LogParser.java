package parser;

import event.LogEvent;
import gui.ParsingTab;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingWorker;

import report.Damage;
import report.Energize;
import report.Heal;
import report.Miss;
import report.Miss.Type;
import report.ReportEngine;
import world.Timestamp;
import world.Unit;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

public class LogParser extends SwingWorker<Void, Integer> {

	public static final int BASE_PARAM1 = -1;
	public static final int BASE_PARAM2 = 0;
	public static final int BASE_PARAM3 = -1;
	public static final int BASE_PARAM4 = 1;
	public static final int BASE_PARAM5 = 2;
	public static final int BASE_PARAM6 = 3;
	public static final int BASE_PARAM7 = 4;
	public static final int BASE_PARAM8 = 5;
	public static final int BASE_PARAM9 = 6;
	public static final int BASE_PARAM10 = 7;
	public static final int BASE_PARAM11 = 8;

	public static final int PREFIX_PARAM1 = 9;
	public static final int PREFIX_PARAM2 = 10;
	public static final int PREFIX_PARAM3 = 11;

	public static final int SUFFIX_PARAM1 = 12;
	public static final int SUFFIX_PARAM2 = 13;
	public static final int SUFFIX_PARAM3 = 14;
	public static final int SUFFIX_PARAM4 = 15;
	public static final int SUFFIX_PARAM5 = 16;
	public static final int SUFFIX_PARAM6 = 17;
	public static final int SUFFIX_PARAM7 = 18;
	public static final int SUFFIX_PARAM8 = 19;
	public static final int SUFFIX_PARAM9 = 20;

	private List<EventParser> eventParsers = new ArrayList<EventParser>();
	private String fileName;
	private ParsingTab parsingTab;

	public LogParser(String fileName, ParsingTab parsingTab) {
		this.fileName = fileName;
		this.parsingTab = parsingTab;
	}

	public void parse() throws FileNotFoundException, java.text.ParseException {

		initParsers();

		ReportEngine report = ReportEngine.getInstance();
		File file = new File(this.fileName);
		long max = file.length();
		long total = 0;
		int last = 0;
		Scanner scanner = new Scanner(file, "UTF-8");

		setProgress(0);
		// int i = 0;
		while (scanner.hasNextLine() && !isCancelled()) {
			String line = scanner.nextLine();
			total += line.length();
			int percent = (int) (((double) total / (double) max) * 10000.0);
			if (percent > last) {
				last = percent;
				publish(percent);
			}
			try {
				LogEvent event = parserEvent(line);
				if (event != null) {
					report.addEvent(event);
				}
			} catch (ParseException e) {
				System.err.println(e.getMessage());
			}
		}
		publish(10000);
	}

	private void initParsers() {
		eventParsers.add(new SwingEventParser());
		eventParsers.add(new RangeEventParser());
		eventParsers.add(new SpellEventParser());
		eventParsers.add(new SpecialDamageEventParser());
		eventParsers.add(new SpecialsEventParser());
	}

	private LogEvent parserEvent(String line) throws ParseException,
			java.text.ParseException {
		String[] split = line.split(" +", 3);

		if (split.length != 3) {
			throw new ParseException(
					"Bad formated line. Excepted Date Time Other. Found: "
							+ line);
		}

		int time = Timestamp.getTime(split[0], split[1]);
		String params = split[2];

		String[] splitParam = params.split(",");

		for (int i = 0; i < splitParam.length; i++) {
			if (splitParam[i].startsWith("\"") && !splitParam[i].endsWith("\"")) {
				String[] newSplitParam = new String[splitParam.length - 1];
				for (int j = 0; j < i; j++) {
					newSplitParam[j] = splitParam[j];
				}
				newSplitParam[i] = splitParam[i] + "," + splitParam[i + 1];

				for (int j = i + 2; j < splitParam.length; j++) {
					newSplitParam[j - 1] = splitParam[j];
				}
				splitParam = newSplitParam;
				i--;
			}
		}

		if (splitParam.length < 7) {
			throw new ParseException(
					"Bad formated line. Excepted at least 7. Found: " + params);
		}

		String key = splitParam[0];

		Unit sourceUnit = Unit.getInstance(splitParam[BASE_PARAM4],
				parseString(splitParam[BASE_PARAM5]),
				parseLong(splitParam[BASE_PARAM6]),
				parseLong(splitParam[BASE_PARAM7]));
		Unit targetUnit = Unit.getInstance(splitParam[BASE_PARAM8],
				parseString(splitParam[BASE_PARAM9]),
				parseLong(splitParam[BASE_PARAM10]),
				parseLong(splitParam[BASE_PARAM11]));

		for (EventParser eventParser : eventParsers) {
			if (eventParser.match(key)) {
				return eventParser.parse(time, sourceUnit, targetUnit,
						splitParam);
			}
		}

		throw new ParseException("Unknow event " + key + " in " + params);

	}

	public static long parseLong(String string) {
		return Long.parseLong(string.substring(2), 16);
	}

	public static BigInteger parseGuid(String string) {
		return new BigInteger(string.substring(2), 16);
	}

	public static int parseInt(String string) {
		return Integer.parseInt(string.substring(2), 16);
	}

	public static String parseString(String string) {
		return string.substring(1, string.length() - 1);
	}

	public static Damage parseDamage(String[] params, int index) {

		int amount = Integer.valueOf(params[index + 0]);
		int overkill = Integer.valueOf(params[index + 1]);
		int school = Integer.valueOf(params[index + 2]);
		int resisted = Integer.valueOf(params[index + 3]);
		int blocked = Integer.valueOf(params[index + 4]);
		int absorbed = Integer.valueOf(params[index + 5]);
		boolean critical = (params[index + 6].equals("nil") ? false : true);
		boolean glancing = (params[index + 7].equals("nil") ? false : true);
		boolean crushing = (params[index + 8].equals("nil") ? false : true);

		return new Damage(amount, overkill, school, resisted, blocked,
				absorbed, critical, glancing, crushing);

	}

	public static Heal parseHeal(String[] params, int index) {

		int amount = Integer.valueOf(params[index + 0]);
		int overhealing = Integer.valueOf(params[index + 1]);
		int absorbed = Integer.valueOf(params[index + 2]);
		boolean critical = (params[index + 2].equals("nil") ? false : true);

		return new Heal(amount, overhealing, absorbed, critical);

	}

	public static Miss parseMiss(String[] params, int index) {

		String reason = params[index + 0];

		int amount = 0;

		if (params.length > index + 1) {
			amount = Integer.valueOf(params[index + 1]);
		}

		Miss.Type type = null;

		if (reason.equals("ABSORB")) {
			type = Type.ABSORB;
		} else if (reason.equals("BLOCK")) {
			type = Type.BLOCK;
		} else if (reason.equals("DEFLECT")) {
			type = Type.DEFLECT;
		} else if (reason.equals("DODGE")) {
			type = Type.DODGE;
		} else if (reason.equals("EVADE")) {
			type = Type.EVADE;
		} else if (reason.equals("IMMUNE")) {
			type = Type.IMMUNE;
		} else if (reason.equals("MISS")) {
			type = Type.MISS;
		} else if (reason.equals("PARRY")) {
			type = Type.PARRY;
		} else if (reason.equals("REFLECT")) {
			type = Type.REFLECT;
		} else if (reason.equals("RESIST")) {
			type = Type.RESIST;
		}

		return new Miss(amount, type);

	}

	public static Energize parseEnergize(String[] params, int index) {

		int amount = Integer.valueOf(params[index + 0]);
		String reason = params[index + 1];

		Energize.Type type = null;

		if (reason.equals("-2")) {
			type = Energize.Type.HEALTH;
		} else if (reason.equals("0")) {
			type = Energize.Type.MANA;
		} else if (reason.equals("1")) {
			type = Energize.Type.RAGE;
		} else if (reason.equals("2")) {
			type = Energize.Type.FOCUS;
		} else if (reason.equals("3")) {
			type = Energize.Type.ENERGY;
		} else if (reason.equals("4")) {
			type = Energize.Type.PET_HAPPINESS;
		} else if (reason.equals("5")) {
			type = Energize.Type.RUNES;
		} else if (reason.equals("6")) {
			type = Energize.Type.RUNIC_POWER;
		} else if (reason.equals("7")) {
			type = Energize.Type.SOUL_SHARD;
		} else if (reason.equals("8")) {
			type = Energize.Type.ECLIPSE;
		} else if (reason.equals("9")) {
			type = Energize.Type.HOLY_POWER;
		} else if (reason.equals("10")) {
			type = Energize.Type.ATRAMEDES_SOUND;
		}

		return new Energize(amount, type);

	}

	@Override
	protected Void doInBackground() throws Exception {
		parse();
		return null;
	}

	@Override
	protected void process(List<Integer> chunks) {
		for (Integer nb : chunks) {
			parsingTab.update("Parsing - ", nb);
		}
	}

	@Override
	protected void done() {
		parsingTab.parseDone();
	}
}
