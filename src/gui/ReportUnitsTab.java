package gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import report.Fight;
import world.Unit;

public class ReportUnitsTab extends JPanel {
	/**
	 * 
	 */
	private final String[] headerUnitList = {"","name", "id"};
	
	private static final long serialVersionUID = -3444983139584944248L;

	public ReportUnitsTab(Fight fight) {
		setLayout(new GridLayout(2, 2));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		this.add(createUnitPanel(fight.getPlayerList(), "Players"));
		this.add(createUnitPanel(fight.getNpcList(), "NPC"));
		this.add(createUnitPanel(fight.getOwnedPetList(), "Pet with owner"));
		this.add(createUnitPanel(fight.getFreePetList(), "Pet without owner"));
		// this.add(Box.createVerticalGlue());
	}

	private Component createUnitPanel(ArrayList<Unit> unitList, String name) {
		JPanel unitPanel = new JPanel();
		unitPanel.setLayout(new BoxLayout(unitPanel, BoxLayout.PAGE_AXIS));
		unitPanel.setBorder(BorderFactory.createTitledBorder(name));
		JPanel unitPanelList = new JPanel(); /*http://thierry-leriche-dessirier.developpez.com/tutoriels/java/afficher-tableau-avec-tablemodel-5-min/*/
		unitPanelList.setLayout(new BoxLayout(unitPanelList,
				BoxLayout.PAGE_AXIS));
		JScrollPane unitPanelScrollPane = new JScrollPane(unitPanelList);
		unitPanelScrollPane.setBorder(BorderFactory.createEmptyBorder());
		unitPanel.add(unitPanelScrollPane);
		for (Unit unit : unitList) {
			JLabel unitLabel = new JLabel(unit.getName());
			unitLabel.setToolTipText(unit.getTooltip());
			unitPanelList.add(unitLabel);
		}
		return unitPanel;
	}
}
