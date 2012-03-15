package gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ReportTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5983019447298032535L;
	JTabbedPane tabs;

	public ReportTab() {
		tabs = new JTabbedPane(JTabbedPane.BOTTOM);

		tabs.addTab("Sumary", new JPanel());
		tabs.addTab("Units", new JPanel());
		tabs.addTab("Heal", new JPanel());
		tabs.addTab("Damage", new JPanel());
		add(tabs);

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
}
