package gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

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
		
		/* Creating defaults tabs*/
		
		tabs.addTab("Parsing",new ParsingTab());
		tabs.setTabComponentAt(0, createTab("Parsing", "./img/small/inv_scroll_01.jpg"));
		tabs.setToolTipTextAt(0, "Parse the log file");	
		tabs.addTab("All",new ParsingTab());
		tabs.setTabComponentAt(1, createTab("All", "./img/small/inv_scroll_02.jpg"));
		tabs.setToolTipTextAt(1, "All fights");
		/*tabs.getTabComponentAt(0).set*/
		add(tabs);
	}
	
	private JLabel createTab(String name,String iconName){
		JLabel lbl = new JLabel(name);
		Icon icon = new ImageIcon(iconName);
		
		lbl.setIcon(icon);
		lbl.setIconTextGap(5);
		lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
		
		return lbl;
	}

}
