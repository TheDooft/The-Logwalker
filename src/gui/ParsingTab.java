package gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class ParsingTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8617942858027114616L;

	public ParsingTab() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextField textFieldFileName = new JTextField("./sample/WoWCombatLog.txt",30);
		
		this.add(textFieldFileName);
	}
}
