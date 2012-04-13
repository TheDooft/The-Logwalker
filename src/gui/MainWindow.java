package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import report.Fight;
import report.ReportEngine;

public class MainWindow extends JFrame implements ChangeListener {
	/**
	 * 
	 */

	private static final long serialVersionUID = 2740437090361841747L;
	private JTabbedPane tabs;
	static private MainWindow instance;
	private Map<Integer,Fight> bossTabList;
	
	
	public MainWindow() {
		init();
		bossTabList = new HashMap<Integer, Fight>();
	}

	public static MainWindow getInstance() {
		if (instance == null)
			instance = new MainWindow();
		return instance;
	}

	private void init() {
		tabs = new JTabbedPane(JTabbedPane.LEFT);

		setSize(1024, 768);
		setTitle("The LogWalker");
		setIconImage(new ImageIcon("./img/small/spell_nature_magicimmunity.jpg")
				.getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		tabs.addTab("Parsing", new ParsingTab());
		tabs.addTab("All", new ReportTab());
		tabs.setTabComponentAt(0,
				createTab("Parsing", "./img/small/inv_scroll_01.jpg"));
		tabs.setTabComponentAt(1,
				createTab("All", "./img/small/inv_scroll_02.jpg"));
		tabs.addChangeListener(this);

		add(tabs);
	}

	private JLabel createTab(String name, String iconName) {
		JLabel jLabel = new JLabel(name, new ImageIcon(iconName), JLabel.LEFT);
		jLabel.setIconTextGap(5);

		return jLabel;
	}

	public void refreshTab() {
		ReportEngine report = ReportEngine.getInstance();
		ArrayList<Fight> fightList = report.getFightList();
		bossTabList.clear();
		int max = tabs.getTabCount();
		for (int i = 2; i < max; i++) {
			tabs.removeTabAt(2);
			tabs.revalidate();
			tabs.repaint();
		}
		for (Fight fight : fightList) {
			tabs.addTab(fight.getBoss().getName(), null);
			tabs.setTabComponentAt(
					tabs.getTabCount() - 1,
					createTab(fight.getBoss().getName(), "./img/small/"
							+ fight.getBoss().getIcon()));
			bossTabList.put(tabs.getTabCount(), fight);
		}
		this.revalidate();
		this.repaint();
		/*
		 * } });
		 */
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		System.out.println("Tab #" + tabs.getSelectedIndex() + " selected");
	}
}
