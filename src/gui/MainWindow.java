package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

		/* Creating defaults tabs */

		tabs.addTab("Parsing", new ParsingTab());
		tabs.addTab("All", new ReportTab());
		
		tabs.setTabComponentAt(0,createTab("Parsing","./img/small/inv_scroll_01.jpg"));
		tabs.setTabComponentAt(1,createTab("All","./img/small/inv_scroll_02.jpg"));
		
		add(tabs);
	}


	private JLabel createTab(String name,String iconName){
		JLabel jLabel = new JLabel(name,new ImageIcon(iconName),JLabel.LEFT);
		jLabel.setIconTextGap(5);
		
		return jLabel;
	}
}
