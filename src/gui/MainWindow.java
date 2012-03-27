package gui;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import report.Fight;
import report.ReportEngine;

public class MainWindow extends JFrame {
	/**
	 * 
	 */

	private static final long serialVersionUID = 2740437090361841747L;
	static private JTabbedPane tabs;
	static private MainWindow instance;

	public MainWindow() {
		init();
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

		/* Creating defaults tabs */

		tabs.addTab("Parsing", new ParsingTab());
		tabs.addTab("All", new ReportTab());
		tabs.addTab("test1", new ReportTab());
		tabs.addTab("test2", new ReportTab());
		tabs.setTabComponentAt(0,
				createTab("Parsing", "./img/small/inv_scroll_01.jpg"));
		tabs.setTabComponentAt(1,
				createTab("All", "./img/small/inv_scroll_02.jpg"));

		add(tabs);
	}

	private JLabel createTab(String name, String iconName) {
		JLabel jLabel = new JLabel(name, new ImageIcon(iconName), JLabel.LEFT);
		jLabel.setIconTextGap(5);

		return jLabel;
	}

	public void refreshTab() {
		System.out.println("before invoke : " + Thread.currentThread());
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				System.out.println("after invoke : " + Thread.currentThread());
				ReportEngine report = ReportEngine.getInstance();
				ArrayList<Fight> fightList = report.getFightList();
				int max = tabs.getTabCount();
				for (int i = 2; i < max; i++)
					tabs.removeTabAt(2);
				for (Fight fight : fightList) {
					tabs.addTab(fight.getBoss().getName(), new ReportTab());
					tabs.setTabComponentAt(
							tabs.getTabCount() - 1,
							createTab(fight.getBoss().getName(), "./img/small/"
									+ fight.getBoss().getIcon()));
				}
				revalidate();
				repaint();
			}
		});
	}
}
