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
	private JTabbedPane tabs;
	private Fight fight;
	private LogTab logTab;
	private boolean firstUpdate;
	
	public ReportTab(Fight fight) {
		firstUpdate = true;
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
			logTab = new LogTab(fight);
			tabs.addTab("Log", logTab);
			tabs.addTab("Filters", new FiltersTab(fight));
			tabs.addChangeListener(this);
		}
	}

	public LogTab getLogTab() {
		return logTab;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (tabs.getSelectedComponent().getClass().equals(LogTab.class)){
			LogTab tab = (LogTab) tabs.getSelectedComponent();
			if (firstUpdate){
				tab.updateContent();
			}
		}
		firstUpdate = false;
	}
}
