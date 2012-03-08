package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2740437090361841747L;

	public MainWindow() {
		init();
	}

	private void init() {
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT);
		
		setSize(1024, 768);
		setTitle("The LogWalker");
		setIconImage(new ImageIcon("./img/small/spell_nature_magicimmunity.jpg")
				.getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		tabs.addTab("Parsing",new ImageIcon("./img/small/inv_scroll_01.jpg"),new ParsingTab(),"Parse the log file");
		tabs.addTab("All",new ImageIcon("./img/small/inv_scroll_02.jpg"),new ParsingTab(),"All combats");
		add(tabs);
	}

}
