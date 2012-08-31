package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class FilterWindowTypePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7369962342508754852L;

	private JLabel leftLabel;
	private JLabel rightLabel;
	private JTextField leftTextField;
	private JTextField rightTextField;
	private JList<String> leftList;
	private JList<String> rightList;
	private JButton addEventButton;
	private JButton remEventButton;
	private JPanel leftTypePanel;
	private JPanel rightTypePanel;
	private JPanel middleTypePanel;
	private JPanel topLeftPanel;
	private JPanel topRightPanel;
	private DefaultListModel<String> leftListModel;
	private DefaultListModel<String> rightListModel;

	public FilterWindowTypePanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		generateLeftTypePanel();
		generateMiddleTypePanel();
		generateRightTypePanel();
	}

	void generateLeftTypePanel() {
		leftLabel = new JLabel("Excluded event types");

		leftTextField = new JTextField("<i>Type to filter</i>");
		leftTextField.setMaximumSize(new Dimension(leftTextField
				.getMaximumSize().width,
				leftTextField.getPreferredSize().height));

		leftListModel = new DefaultListModel<String>();
		leftListModel.addElement("test 1");
		leftListModel.addElement("test 2");

		leftList = new JList<String>(leftListModel);
		leftList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		leftList.setLayoutOrientation(JList.VERTICAL);

		leftTypePanel = new JPanel();
		leftTypePanel.setLayout(new BorderLayout());
		leftTypePanel
				.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		topLeftPanel = new JPanel();
		topLeftPanel
				.setLayout(new BoxLayout(topLeftPanel, BoxLayout.PAGE_AXIS));
		topLeftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		leftLabel.setAlignmentX(LEFT_ALIGNMENT);
		leftTextField.setAlignmentX(LEFT_ALIGNMENT);

		topLeftPanel.add(leftLabel);
		topLeftPanel.add(leftTextField);
		leftTypePanel.add(topLeftPanel, BorderLayout.NORTH);
		leftTypePanel.add(new JScrollPane(leftList), BorderLayout.CENTER);
		this.add(leftTypePanel);
	}

	void generateRightTypePanel() {
		rightLabel = new JLabel("Included event types");
		rightTextField = new JTextField("<i>Type to filter</i>");
		rightTextField.setMaximumSize(new Dimension(rightTextField
				.getMaximumSize().width,
				rightTextField.getPreferredSize().height));

		rightListModel = new DefaultListModel<String>();

		rightList = new JList<String>(rightListModel);

		rightTypePanel = new JPanel();
		rightTypePanel.setLayout(new BorderLayout());
		rightTypePanel.setBorder(BorderFactory
				.createEmptyBorder(10, 10, 10, 10));

		topRightPanel = new JPanel();
		topRightPanel.setLayout(new BoxLayout(topRightPanel,
				BoxLayout.PAGE_AXIS));

		rightLabel.setAlignmentX(LEFT_ALIGNMENT);
		rightTextField.setAlignmentX(LEFT_ALIGNMENT);

		topRightPanel.add(rightLabel);
		topRightPanel.add(rightTextField);
		rightTypePanel.add(topRightPanel, BorderLayout.NORTH);
		rightTypePanel.add(new JScrollPane(rightList), BorderLayout.CENTER);
		this.add(rightTypePanel);
	}

	void generateMiddleTypePanel() {
		middleTypePanel = new JPanel();
		middleTypePanel.setLayout(new BoxLayout(middleTypePanel,
				BoxLayout.PAGE_AXIS));
		middleTypePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,
				10));

		addEventButton = new JButton("Add >");
		addEventButton.setEnabled(false);
		remEventButton = new JButton("< Remove");
		remEventButton.setEnabled(false);

		middleTypePanel.add(addEventButton);
		middleTypePanel.add(remEventButton);
		this.add(middleTypePanel);
	}
}
