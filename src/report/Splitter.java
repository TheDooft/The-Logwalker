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
		ReportEngine report = ReportEngine.getInstance();
		ArrayList<LogEvent> evenList = report.getTrash().getEventList();
		int i = 0;
		int last = 0;
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

			if (currentFight == null) {
				for (Boss boss : ReportEngine.getXmlBossList()) {
					for (Integer uid : boss.getUids()) {
						String suid = event.getSource().getGuid();
						if (Integer.parseInt(suid) == uid)
							return;
					}
				}
			}
		}
	}

	@Override
	protected Void doInBackground() throws Exception {
		split();
		return null;
	}
	
	@Override
	protected void process(List<Integer> chunks) {
		for (Integer nb : chunks){
			parsingTab.update("Spliting - ",nb);
		}
	}
	
	@Override
	protected void done() {
		parsingTab.done();
	}
}
