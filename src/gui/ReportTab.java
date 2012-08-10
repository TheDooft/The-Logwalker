package gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import report.Fight;
import report.ReportEngine;

public class ReportTab extends JPanel implements ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5983019447298032535L;
	JTabbedPane tabs;
	Fight fight;

	public ReportTab(Fight fight) {
		tabs = new JTabbedPane(JTabbedPane.TOP);
		add(tabs);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.fight = fight;
	}

	public void updateContent() {
		if (!fight.getComputed()) {
			ReportEngine reportEngine = ReportEngine.getInstance();
			reportEngine.setCurrentFight(fight);
			fight.compute(); // TODO -> worker
			tabs.addTab("Units", new ReportUnitsTab(fight));
			tabs.addTab("Log", new LogTab(fight));
			tabs.addChangeListener(this);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (tabs.getSelectedComponent().getClass().equals(LogTab.class)){
			LogTab tab = (LogTab) tabs.getSelectedComponent();
			tab.updateContent();
		}
	}
}
