package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import parser.LogParser;
import report.Splitter;

public class ParsingTab extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8617942858027114616L;
	private JButton buttonParse;
	private JButton buttonCancel;
	private JFileChooser fileChooser;
	private JTextField textFieldFileName;
	private JProgressBar progressBar;
	private LogParser parser;

	public ParsingTab() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		/* Source File Choosing */

		JButton buttonBrowse = new JButton("Browse...");
		JPanel sourcePanel = new JPanel();

		this.textFieldFileName = new JTextField(
				"./sample/WoWCombatLog-light.txt", 40);
		this.textFieldFileName.setMaximumSize(textFieldFileName
				.getPreferredSize());
		buttonBrowse.addActionListener(this);
		sourcePanel.setLayout(new BoxLayout(sourcePanel, BoxLayout.LINE_AXIS));
		sourcePanel.setBorder(BorderFactory
				.createTitledBorder("Choose source file"));
		sourcePanel.add(textFieldFileName);
		sourcePanel.add(buttonBrowse);
		sourcePanel.setAlignmentX(LEFT_ALIGNMENT);
		
		buttonParse = new JButton("Parse");
		buttonParse.addActionListener(this);

		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(this);
		buttonCancel.setVisible(false);
		
		JPanel parsePanel = new JPanel();
		parsePanel.setLayout(new BoxLayout(parsePanel, BoxLayout.LINE_AXIS));
		parsePanel.add(buttonParse);
		parsePanel.add(buttonCancel);
		parsePanel.setAlignmentX(LEFT_ALIGNMENT);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setVisible(false);
		progressBar.setAlignmentX(LEFT_ALIGNMENT);
		
		this.add(sourcePanel, BorderLayout.LINE_START);
		this.add(parsePanel);
		this.add(Box.createVerticalGlue());
		this.add(progressBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Browse...") {
			int returnVal;
			fileChooser = new JFileChooser(this.textFieldFileName.getText());
			fileChooser.setMultiSelectionEnabled(false);
			returnVal = fileChooser.showOpenDialog(buttonParse);
			if (returnVal == JFileChooser.APPROVE_OPTION)
				textFieldFileName.setText(fileChooser.getSelectedFile()
						.getAbsolutePath());
		}
		if (e.getActionCommand() == "Parse") {
			buttonParse.setEnabled(false);
			progressBar.setMinimum(0);
			progressBar.setMaximum(10000);
			progressBar.setValue(0);
			progressBar.setString("0,00 %");
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			progressBar.setVisible(true);
			buttonCancel.setVisible(true);
			parser = new LogParser(textFieldFileName.getText(), this);
			parser.execute();
		}
		if (e.getActionCommand() == "Cancel") {
			parser.cancel(true);
		}
	}

	public void update(String phase,int progress) {
		DecimalFormat df = new DecimalFormat ( ) ;
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2); 
		progressBar.setValue(progress);
		progressBar.setString(phase + df.format(progress / 100.0) + " %");
	}
	
	public void parseDone(){
		Splitter splitter = new Splitter(this);
		splitter.execute();
	}
	
	public void done() {
		buttonParse.setEnabled(true);
		setCursor(null);
		progressBar.setVisible(false);
		buttonCancel.setVisible(false);
		MainWindow mainWindow = MainWindow.getInstance();
		mainWindow.refreshTab();
	}
	
}
