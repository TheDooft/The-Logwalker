package gui;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class Gui {
	private MainWindow mainWindows;

	public Gui() {
		setLookAndFeel();
		// JFrame.setDefaultLookAndFeelDecorated(false);
		ToolTipManager.sharedInstance().setInitialDelay(0);
		mainWindows = MainWindow.getInstance();
	}

	public void start() {
		mainWindows.setLocationRelativeTo(null);
		mainWindows.setVisible(true);
	}

	private void setLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
			/* UIManager.setLookAndFeel(new NimbusLookAndFeel()); */
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

	}
}
