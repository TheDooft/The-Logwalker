package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import report.LogReport;
import world.Unit;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import event.LogEvent;

public class LogParser {

    LogReport report;

    List<EventParser> eventParsers = new ArrayList<EventParser>();

    public LogParser(String string) throws FileNotFoundException {

        initParsers();

        report = new LogReport();

        Scanner scanner = new Scanner(new File(string));

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();


            try {
                LogEvent event = parserEvent(line);

                if(event != null) {
                    report.addEvent(event);
                }

            } catch (ParseException e) {
                System.err.println(e.getMessage());
            }


        }


    }

    private void initParsers() {
        eventParsers.add(new UnitDiedEventParser());

    }

    private LogEvent parserEvent(String line) throws ParseException {
        String[] split = line.split(" +",3);

        if(split.length != 3) {
            throw new ParseException("Bad formated line. Excepted Date Time Other. Found: "+line);
        }

        String date = split[0];
        String time = split[1];
        String params = split[2];

        String[] splitParam = params.split(",");

        if(splitParam.length < 7) {
            throw new ParseException("Bad formated line. Excepted at least 7. Found: "+params);
        }

        String key = splitParam[0];

        Unit sourceUnit = report.getUnitManager().parseUnit(parseGuid(splitParam[1]), parseString(splitParam[2]), parseInt(splitParam[3]));
        Unit targetUnit = report.getUnitManager().parseUnit(parseGuid(splitParam[4]), parseString(splitParam[5]), parseInt(splitParam[6]));


        for(EventParser eventParser: eventParsers) {
            if(key.equals((eventParser.getKey()))) {
                return eventParser.parse(date, time, sourceUnit, targetUnit, splitParam);
            }
        }



        throw new ParseException("Unknow event "+key+" in "+ params);

    }

    private long parseInt(String string) {
        return Long.parseLong(string.substring(2), 16);
    }

    private String parseString(String string) {
        return string.substring(1, string.length()-1);
    }

    private String parseGuid(String string) {
        return string.substring(2);
    }

    public static void main(String[] args) throws FileNotFoundException {
        LogParser parser = new LogParser("/home/fred/.wine/drive_c/Program Files/World of Warcraft/Logs/WoWCombatLog.txt");

        LogReport report = parser.getReport();

        System.out.println("Nombre d'evenements: "+report.getEventCount());

        List<LogEvent> eventList = report.getEventList();

        for(LogEvent event: eventList) {
            System.out.println(event.toString());
        }

    }

    public LogReport getReport() {
        return report;
    }

}
