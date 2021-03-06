package gui;

import java.awt.Cursor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

import report.Fight;
import world.Timestamp;
import event.LogEvent;
import filter.Filter;

public class LogTab extends JPanel implements PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4958850279033297910L;
	private Fight currentFight;
	private JTextArea logArea;
	private JProgressBar progressBar;
	private DefaultStyledDocument doc;

	private Update update;

	class Update extends SwingWorker<Void, Integer> {

		@Override
		protected Void doInBackground() throws Exception {
			ArrayList<LogEvent> eventList = currentFight.getEventList();
			doc = new DefaultStyledDocument();
			progressBar.setVisible(true);
			setProgress(0);
			int i = 0;
			int last = 0;
			int max = eventList.size();
			try {
				for (LogEvent e : eventList) {
					int percent = (++i * 10000) / max;
					if (percent > last) {
						last = percent;
						publish(percent);
					}

					List<Filter> filters = currentFight.getFilters();
					boolean filtered = true;
					if (filters.isEmpty()) {
						filtered = false;
					}
					for (Filter filter : filters) {
						if (filter.isMatching(e)) {
							filtered = false;
						}
					}

					if (!filtered) {
						String displayStr = "";

						displayStr += Timestamp.displayTime(e.getTime()) + '\t'
								+ e.getText() + '\n';
						doc.insertString(doc.getEndPosition().getOffset(),
								displayStr, null);
					}
				}
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
			return null;
		}

		@Override
		protected void process(List<Integer> chunks) {
			for (Integer nb : chunks) {
				progressBar.setValue(nb);
				progressBar.setString("Generating log -" + nb / 100 + "%");
			}
		}

		@Override
		protected void done() {
			logArea.setDocument(doc);
			progressBar.setVisible(false);
			setCursor(null);
		}

	}

	public LogTab(Fight fight) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		currentFight = fight;

		logArea = new JTextArea();
		logArea.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(logArea);
		logScrollPane.setAlignmentX(LEFT_ALIGNMENT);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setVisible(false);
		progressBar.setMaximum(10000);
		progressBar.setAlignmentX(LEFT_ALIGNMENT);

		this.add(logScrollPane);
		this.add(Box.createVerticalGlue());
		this.add(progressBar);
	}

	public void updateContent() {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		update = new Update();
		update.addPropertyChangeListener(this);
		update.execute();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}
}
