package gui;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import report.Fight;
import world.Unit;

public class ReportUnitsTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3444983139584944248L;

	public ReportUnitsTab(Fight fight) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.PAGE_AXIS));
		playerPanel.setBorder(BorderFactory.createTitledBorder("Players"));
		playerPanel.setAlignmentX(LEFT_ALIGNMENT);
		for (Unit player : fight.getPlayerList()){
			JLabel playerLabel = new JLabel(player.getName());
			playerPanel.add(playerLabel);
		}

		JPanel npcPanel = new JPanel();
		npcPanel.setLayout(new BoxLayout(npcPanel, BoxLayout.PAGE_AXIS));
		npcPanel.setBorder(BorderFactory.createTitledBorder("NPC"));
		npcPanel.setAlignmentX(RIGHT_ALIGNMENT);
		for (Unit npc : fight.getNpcList()){
			JLabel npcLabel = new JLabel(npc.getName() + " (" + npc.getId() + ")");
			npcPanel.add(npcLabel);
		}
		
		
		this.add(playerPanel);
		this.add(npcPanel);
		this.add(Box.createVerticalGlue());
	}
}
