package gui;

import gui.synth.NimbusLookAndFeel;
import gui.synth.SynthLookAndFeel;
import gui.synth.SynthTabbedPaneUI;

import java.awt.FontMetrics;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import javax.swing.text.View;

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
		tabs.setUI(new SynthTabbedPaneUI());
		System.err.println(tabs.getUI());
		tabs.addTab("Parsing", new ImageIcon("./img/small/inv_scroll_01.jpg"),
				new ParsingTab(), "Parse the log file");
		tabs.addTab("All", new ImageIcon("./img/small/inv_scroll_01.jpg"),
				new ParsingTab(), "All fights");
		add(tabs);
	}

	// private JPanel createTab(String name,String iconName){
	// JLabel lbl = new JLabel(name,new ImageIcon(iconName),JLabel.LEFT);
	// JPanel panel = new JPanel (new GridBagLayout());
	// GridBagConstraints gridBagConstraints = new GridBagConstraints();
	//
	// lbl.setIconTextGap(5);
	// lbl.setOpaque(false);
	//
	// gridBagConstraints.gridx = 0;
	// gridBagConstraints.gridy = 0;
	// gridBagConstraints.weightx = 1;
	// gridBagConstraints.anchor = GridBagConstraints.WEST;
	// gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
	//
	// panel.add(lbl,gridBagConstraints);
	//
	// gridBagConstraints.gridx = 0;
	// gridBagConstraints.gridy = 1;
	//
	// panel.add(Box.createHorizontalStrut(0),gridBagConstraints);
	// panel.setOpaque(false);
	// return panel;
	// }
	// class logTabbedPaneUI extends Basic

	class test extends MetalTabbedPaneUI {

	}

	
}
