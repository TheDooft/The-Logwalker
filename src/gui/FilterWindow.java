package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import filter.Filter;

public class FilterWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Filter filter;
	private JButton cancelButton;
	private JButton okButton;
	
	public FilterWindow() {
		setVisible(false);
		//setSize(1024, 768);
		setTitle("Filter");
		setType(Type.UTILITY);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		//setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel leftLabel = new JLabel("Excluded event types");
		JLabel rightLabel = new JLabel("Included event types");
		
		JTextField leftTextField = new JTextField("<i>Type to filter</i>");
        leftTextField.setMaximumSize(new Dimension(leftTextField.getMaximumSize().width, leftTextField.getPreferredSize().height));
		JTextField rightTextField = new JTextField("<i>Type to filter</i>");
		rightTextField.setMaximumSize(new Dimension(rightTextField.getMaximumSize().width, rightTextField.getPreferredSize().height));
		
		DefaultListModel<String> leftListModel = new DefaultListModel<String>();
		leftListModel.addElement("test 1");
		leftListModel.addElement("test 2");
		JList<String> leftList = new JList<String>(leftListModel);
		leftList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		leftList.setLayoutOrientation(JList.VERTICAL);
		DefaultListModel<String> rightListModel = new DefaultListModel<String>();
		JList<String> rightList = new JList<String>(rightListModel);
		
		JButton addEventButton = new JButton("Add >");
		addEventButton.setEnabled(false);
		JButton remEventButton = new JButton("< Remove");
		remEventButton.setEnabled(false);
		JPanel leftTypePannel = new JPanel();
		
		leftTypePannel.setLayout(new BoxLayout(leftTypePannel, BoxLayout.PAGE_AXIS));
		leftTypePannel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		leftTypePannel.setBackground(Color.YELLOW);
		JPanel rightTypePannel = new JPanel();
		rightTypePannel.setLayout(new BoxLayout(rightTypePannel, BoxLayout.PAGE_AXIS));
		rightTypePannel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel middleTypePannel = new JPanel();
		middleTypePannel.setLayout(new BoxLayout(middleTypePannel, BoxLayout.PAGE_AXIS));
		middleTypePannel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel typePannel = new JPanel();
		typePannel.setLayout(new BoxLayout(typePannel, BoxLayout.LINE_AXIS));
		typePannel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		typePannel.setBackground(Color.PINK);
		
		JPanel topLeftPanel = new JPanel();
		topLeftPanel.setLayout(new BoxLayout(topLeftPanel, BoxLayout.PAGE_AXIS));
		topLeftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		//leftLabel.setAlignmentX(leftTextField.getAlignmentX());
		leftLabel.setAlignmentX(LEFT_ALIGNMENT);
		leftTextField.setAlignmentX(LEFT_ALIGNMENT);
		topLeftPanel.add(leftLabel);
		topLeftPanel.add(leftTextField);
		topLeftPanel.setBackground(Color.RED);
		leftTypePannel.add(topLeftPanel, BorderLayout.NORTH);
		leftTypePannel.add(new JScrollPane(leftList),BorderLayout.CENTER);
		
		middleTypePannel.add(addEventButton);
		middleTypePannel.add(remEventButton);
		
		JPanel topRightPanel = new JPanel();
		topRightPanel.setLayout(new BoxLayout(topRightPanel, BoxLayout.PAGE_AXIS));
		rightLabel.setAlignmentX(rightTextField.getAlignmentX());
		topRightPanel.add(rightLabel);
		topRightPanel.add(rightTextField);
		rightTypePannel.add(topRightPanel,BorderLayout.NORTH);
		rightTypePannel.add(new JScrollPane(rightList),BorderLayout.CENTER);
		
		typePannel.add(leftTypePannel);
		typePannel.add(middleTypePannel);
		typePannel.add(rightTypePannel);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setName("filterWindowCancel");
		okButton = new JButton("Ok");
		okButton.setName("filterWindowOk");
		JPanel buttonPannel = new JPanel();
		buttonPannel.setLayout(new BoxLayout(buttonPannel, BoxLayout.LINE_AXIS));
		buttonPannel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		buttonPannel.add(okButton);
		buttonPannel.add(cancelButton);
		
		JPanel mainPannel = new JPanel();
		mainPannel.setLayout(new BoxLayout(mainPannel, BoxLayout.PAGE_AXIS));
		mainPannel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainPannel.add(typePannel);
		mainPannel.add(buttonPannel);
		mainPannel.setBackground(Color.GREEN);
		
		add(mainPannel);
		this.pack();
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	
	public JButton getCancelButton() {
		return cancelButton;
	}
	
	public JButton getOkButton() {
		return okButton;
	}
}
