package report;

import event.LogEvent;
import gui.ParsingTab;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import boss.Boss;

public class Splitter extends SwingWorker<Void, Integer> {
	private ArrayList<Fight> result;
	private ParsingTab parsingTab;
	private Fight currentFight;

	public Splitter(ParsingTab parsingTab) {
		this.parsingTab = parsingTab;
	}

	public void split() {
		System.out.println("Spliting begins");
		ReportEngine report = ReportEngine.getInstance();
		ArrayList<LogEvent> evenList = report.getTrash().getEventList();
		int i = 0;
		int last = 0;
		int lastActivity = 0;
		int max = evenList.size();
		setProgress(0);
		result = new ArrayList<Fight>();

		currentFight = null;
		while (i < evenList.size()) {
			System.out.println("Parsing line #"+i);
			int percent = (int) (((double) i / (double) max) * 10000.0);
			if (percent > last) {
				last = percent;
				publish(percent);
			}
			LogEvent event = evenList.get(i);
			int time = event.getTime();
			if (time - lastActivity > currentFight.getBoss().getIdleTime()) {
				currentFight.setEndTime(time);
				result.add(currentFight);
				currentFight = null;
			}
			if (currentFight == null) {
				for (Boss boss : ReportEngine.getXmlBossList()) {
					for (Integer uid : boss.getUids()) {
						int suid = event.getSource().getId();
						int tuid = event.getTarget().getId();
						if (suid == uid || tuid == uid) {
							currentFight = new Fight(boss, time);
							lastActivity = 0;
						}
					}
				}
			} else {
				currentFight.addEvent(event);
			}
			i++;
		}
		System.out.println("Spliting ends");
	}

	public ArrayList<Fight> getResult() {
		return result;
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
