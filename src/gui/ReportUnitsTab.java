package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import report.Fight;
import world.Unit;

public class ReportUnitsTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3444983139584944248L;

	public ReportUnitsTab(Fight fight) {
		setLayout(new GridLayout(2, 2));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		this.add(createUnitPanel(fight.getPlayerList(), "Players"));
		this.add(createUnitPanel(fight.getNpcList(), "NPC"));
		this.add(createUnitPanel(fight.getPetList(), "Pet"));
		this.add(createUnitPanel(fight.getGuardianList(), "Guardians"));
		// this.add(Box.createVerticalGlue());
	}

	private Component createUnitPanel(ArrayList<Unit> unitList, String name) {
		JPanel unitPanel = new JPanel();
		unitPanel.setLayout(new BoxLayout(unitPanel, BoxLayout.PAGE_AXIS));
		unitPanel.setBorder(BorderFactory.createTitledBorder(name));
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("All");
		JTree unitTree = new JTree(top);
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setOpenIcon(null);
		renderer.setClosedIcon(null);
		renderer.setLeafIcon(null);
		renderer.setBackgroundNonSelectionColor(new Color(this.getBackground()
				.getRGB()));
		unitTree.setCellRenderer(renderer);
		unitTree.setBackground(new Color(this.getBackground().getRGB()));
		unitTree.putClientProperty("JTree.linestyle", "None");
		JScrollPane unitPanelScrollPane = new JScrollPane(unitTree);
		unitPanelScrollPane.setBorder(BorderFactory.createEmptyBorder());
		unitPanel.add(unitPanelScrollPane);
		for (Unit unit : unitList) {
			DefaultMutableTreeNode unitLabel = new DefaultMutableTreeNode(
					unit.getName()
							+ ((unit.getOwner() == Unit.nil) ? "" : " ("
									+ unit.getOwner().getName() + ")"));
			top.add(unitLabel);
		}
		unitTree.expandRow(0);
		unitTree.expandRow(1);
		unitTree.setRootVisible(false);
		return unitPanel;
	}
}
