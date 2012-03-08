package gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ParsingTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8617942858027114616L;

	public ParsingTab() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JTextField textFieldFileName = new JTextField("./sample/WoWCombatLog.txt",30);
		
		this.add(textFieldFileName);
	}
}
