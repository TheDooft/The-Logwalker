package report;

import event.LogEvent;
import event.SpellEvent;
import gui.ParsingTab;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import boss.Boss;

public class Splitter extends SwingWorker<Void, Integer> {
	private ParsingTab parsingTab;
	private Fight currentFight;
	private ArrayList<LogEvent> buffer;
	private ReportEngine report;
	private final int[] spellBlackList = { 1130, // Hunter's Mark
			110753, 108221, 104901 // Glowing Blood of Shu'ma
	};

	public Splitter(ParsingTab parsingTab) {
		this.parsingTab = parsingTab;
	}

	public void split() {
		System.out.println("Spliting begins");
		report = ReportEngine.getInstance();
		ArrayList<LogEvent> evenList = report.getRaw().getEventList();
		buffer = new ArrayList<LogEvent>();
		int i = 0;
		int last = 0;
		int lastActivity = 0;
		int max = evenList.size();
		setProgress(0);
		currentFight = null;

		while (i < evenList.size()) {
			int percent = (int) (((double) i / (double) max) * 10000.0);
			if (percent > last) {
				last = percent;
				publish(percent);
			}
			LogEvent event = evenList.get(i);
			int time = event.getTime();

			if (currentFight == null) {
				for (Boss boss : ReportEngine.getXmlBossList()) {
					for (Integer uid : boss.getUids()) {
						int suid = event.getSource().getId();
						int tuid = event.getTarget().getId();
						if ((suid == uid || tuid == uid)
								&& notBlacklisted(event)) {
							currentFight = new Fight(boss, time);
							lastActivity = event.getTime();
							currentFight.addEvent(event);
							break;
						}
					}
				}
				if (currentFight == null) {
					report.addTrashEvent(event);
				}
			} else {
				if (time - lastActivity > (currentFight.getBoss().getIdleTime() * 1000)) {
					currentFight.setEndTime(time);
					report.addFight(currentFight);
					clearBuffer();
					currentFight = null;
				} else {
					buffer.add(event);
					for (Integer uid : currentFight.getBoss().getUids()) {
						int suid = event.getSource().getId();
						int tuid = event.getTarget().getId();
						if (suid == uid || tuid == uid) {
							pushBuffer();
							lastActivity = event.getTime();
						}
					}
				}
			}
			i++;
		}
		System.out.println("Spliting ends");
	}

	private boolean notBlacklisted(LogEvent event) {
		if (!(event instanceof SpellEvent))
			return true;
		int id = ((SpellEvent) event).getSpell().getId();
		for (int spellId : spellBlackList)
			if (spellId == id)
				return false;
		return true;
	}

	private void clearBuffer() {
		for (LogEvent event : buffer) {
			report.addTrashEvent(event);
		}
		buffer.clear();
	}

	private void pushBuffer() {
		for (LogEvent event : buffer) {
			currentFight.addEvent(event);
		}
		buffer.clear();
	}

	@Override
	protected Void doInBackground() throws Exception {
		split();
		return null;
	}

	@Override
	protected void process(List<Integer> chunks) {
		for (Integer nb : chunks) {
			parsingTab.update("Spliting - ", nb);
		}
	}

	@Override
	protected void done() {
		parsingTab.done();
	}
}
