package gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import report.Fight;
import tools.UnitModel;
import world.Unit;

public class ReportUnitsTab extends JPanel {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -3444983139584944248L;

	public ReportUnitsTab(Fight fight) {
		setLayout(new GridLayout(1, 2));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		this.add(createUnitPanel(fight.getPlayerList(), "Players"));
		this.add(createUnitPanel(fight.getNpcList(), "NPC"));
	}

	private Component createUnitPanel(ArrayList<Unit> unitList, String name) {
		JPanel unitPanel = new JPanel();
		unitPanel.setLayout(new BoxLayout(unitPanel, BoxLayout.PAGE_AXIS));
		unitPanel.setBorder(BorderFactory.createTitledBorder(name));
		UnitModel unitModel = new UnitModel(unitList);
		UnitTable table = new UnitTable(unitModel);
		
		JScrollPane unitPanelScrollPane = new JScrollPane(table);
		unitPanelScrollPane.setBorder(BorderFactory.createEmptyBorder());
		unitPanel.add(unitPanelScrollPane);
		return unitPanel;
	}
}
