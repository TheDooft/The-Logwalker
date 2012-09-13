package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import event.DamageShieldEvent;
import event.DamageSplitEvent;
import event.EnchantAppliedEvent;
import event.EnchantRemovedEvent;
import event.EnvironmentalDamageEvent;
import event.LogEvent;
import event.PartyKillEvent;
import event.RangeEvent;
import event.SpellAuraAppliedDoseEvent;
import event.SpellAuraAppliedEvent;
import event.SpellAuraBrokenSpellEvent;
import event.SpellAuraRefreshEvent;
import event.SpellAuraRemovedDoseEvent;
import event.SpellAuraRemovedEvent;
import event.SpellCastFailedEvent;
import event.SpellCastStartEvent;
import event.SpellCastSuccessEvent;
import event.SpellCreateEvent;
import event.SpellDamageEvent;
import event.SpellDispelEvent;
import event.SpellDrainEvent;
import event.SpellEnergizeEvent;
import event.SpellEvent;
import event.SpellExtraAttacksEvent;
import event.SpellHealEvent;
import event.SpellInstakillEvent;
import event.SpellInterruptEvent;
import event.SpellLeechEvent;
import event.SpellMissedEvent;
import event.SpellPeriodicDamageEvent;
import event.SpellPeriodicDrainEvent;
import event.SpellPeriodicEnergizeEvent;
import event.SpellPeriodicHealEvent;
import event.SpellPeriodicMissedEvent;
import event.SpellResurrectEvent;
import event.SpellStolenEvent;
import event.SpellSummonEvent;
import event.SwingDamageEvent;
import event.SwingEvent;
import event.SwingMissedEvent;
import event.UnitDestroyedEvent;
import event.UnitDiedEvent;
import filter.Filter;

public class FilterWindowTypePanel extends JPanel implements
		ListSelectionListener, ActionListener, FocusListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7369962342508754852L;

	private static final Map<String, Class<? extends LogEvent>> eventClassList = new HashMap<String, Class<? extends LogEvent>>() {
		private static final long serialVersionUID = -7479416275344422891L;
		{
			put("Damage Shield", DamageShieldEvent.class);
			put("Damage Split", DamageSplitEvent.class);
			put("Enchant Applied", EnchantAppliedEvent.class);
			put("Enchant Removed", EnchantRemovedEvent.class);
			put("Environmental Damage", EnvironmentalDamageEvent.class);
			put("Party Kill", PartyKillEvent.class);
			put("Range", RangeEvent.class);
			put("Spell Aura Applied Dose", SpellAuraAppliedDoseEvent.class);
			put("Spell Aura Applied", SpellAuraAppliedEvent.class);
			put("Spell Aura Broken Spell", SpellAuraBrokenSpellEvent.class);
			put("Spell Aura Refresh", SpellAuraRefreshEvent.class);
			put("Spell Aura Removed Dose", SpellAuraRemovedDoseEvent.class);
			put("Spell Aura Removed", SpellAuraRemovedEvent.class);
			put("Spell Cast Failed", SpellCastFailedEvent.class);
			put("Spell Cast Start", SpellCastStartEvent.class);
			put("Spell Cast Success", SpellCastSuccessEvent.class);
			put("Spell Create", SpellCreateEvent.class);
			put("Spell Damage", SpellDamageEvent.class);
			put("Spell Dispel", SpellDispelEvent.class);
			put("Spell Drain", SpellDrainEvent.class);
			put("Spell Energize", SpellEnergizeEvent.class);
			put("Spell", SpellEvent.class);
			put("Spell Extra Attacks", SpellExtraAttacksEvent.class);
			put("Spell Heal", SpellHealEvent.class);
			put("Spell Instakill", SpellInstakillEvent.class);
			put("Spell Interrupt", SpellInterruptEvent.class);
			put("Spell Leech", SpellLeechEvent.class);
			put("Spell Missed", SpellMissedEvent.class);
			put("Spell Periodic Damage", SpellPeriodicDamageEvent.class);
			put("Spell Periodic Drain", SpellPeriodicDrainEvent.class);
			put("Spell Periodic Energize", SpellPeriodicEnergizeEvent.class);
			put("Spell Periodic Heal", SpellPeriodicHealEvent.class);
			put("Spell Periodic Missed", SpellPeriodicMissedEvent.class);
			put("Spell Resurrect", SpellResurrectEvent.class);
			put("Spell Stolen", SpellStolenEvent.class);
			put("Spell Summon", SpellSummonEvent.class);
			put("Swing Damage", SwingDamageEvent.class);
			put("Swing", SwingEvent.class);
			put("Swing Missed", SwingMissedEvent.class);
			put("Unit Destroyed", UnitDestroyedEvent.class);
			put("Unit Died", UnitDiedEvent.class);
		}
	};

	static private enum Side {
		LEFT, RIGHT
	};

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
	private List<String> leftStringList;
	private List<String> rightStringList;
	private DefaultListModel<String> leftListModel;
	private DefaultListModel<String> rightListModel;
	private Filter filter;
	
	public FilterWindowTypePanel(Filter filter) {
		this.filter = filter;
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		leftStringList = new ArrayList<String>();
		rightStringList = new ArrayList<String>();
		generateLeftTypePanel();
		generateMiddleTypePanel();
		generateRightTypePanel();
		refreshListModel(Side.LEFT);
		refreshListModel(Side.RIGHT);
		leftTypePanel.setPreferredSize(rightTypePanel.getPreferredSize());
	}

	void generateLeftTypePanel() {
		leftLabel = new JLabel("Excluded event types");

		leftTextField = new JTextField("Type to filter");
		leftTextField.setMaximumSize(new Dimension(leftTextField
				.getMaximumSize().width,
				leftTextField.getPreferredSize().height));
		leftTextField.setFont(leftTextField.getFont().deriveFont(Font.ITALIC));
		leftTextField.addFocusListener(this);
		leftTextField.addKeyListener(this);
		leftTextField.addActionListener(this);
		leftTextField.setName("LeftTextField");

		leftListModel = new DefaultListModel<String>();
		for (String eventClass : eventClassList.keySet())
			leftStringList.add(eventClass);

		leftList = new JList<String>(leftListModel);
		leftList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		leftList.setLayoutOrientation(JList.VERTICAL);
		leftList.addListSelectionListener(this);
		leftList.setName("leftList");

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
		rightTextField = new JTextField("Type to filter");
		rightTextField.setMaximumSize(new Dimension(rightTextField
				.getMaximumSize().width,
				rightTextField.getPreferredSize().height));
		rightTextField
				.setFont(rightTextField.getFont().deriveFont(Font.ITALIC));
		rightTextField.addFocusListener(this);
		rightTextField.addKeyListener(this);
		rightTextField.setName("RightTextField");

		rightListModel = new DefaultListModel<String>();

		rightList = new JList<String>(rightListModel);
		rightList.addListSelectionListener(this);
		rightList.setName("rightList");

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
		addEventButton.addActionListener(this);
		addEventButton.setName("addEventButton");
		remEventButton = new JButton("< Remove");
		remEventButton.setEnabled(false);
		remEventButton.addActionListener(this);
		remEventButton.setName("remEventButton");

		middleTypePanel.add(addEventButton);
		middleTypePanel.add(remEventButton);
		this.add(middleTypePanel);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<?> selectedList;
		if (e.getSource() instanceof JList<?>) {
			selectedList = (JList<?>) e.getSource();
			if (selectedList.getName() == "leftList") {
				if (this.addEventButton.isEnabled() == false)
					this.addEventButton.setEnabled(true);
				if (leftList.isSelectionEmpty() == true)
					this.addEventButton.setEnabled(false);
			}
			if (selectedList.getName() == "rightList") {
				if (this.remEventButton.isEnabled() == false)
					this.remEventButton.setEnabled(true);
				if (rightList.isSelectionEmpty() == true)
					this.remEventButton.setEnabled(false);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton;
		if (e.getSource() instanceof JButton) {
			clickedButton = (JButton) e.getSource();
			if (clickedButton.getName().equals("addEventButton")) {
				List<String> selectedValueList = leftList
						.getSelectedValuesList();
				for (String selectedValue : selectedValueList) {
					rightStringList.add(selectedValue);
					leftStringList.remove(selectedValue);
					filter.addEventType(eventClassList.get(selectedValue));
				}
			}
			if (clickedButton.getName().equals("remEventButton")) {
				List<String> selectedValueList = rightList
						.getSelectedValuesList();
				for (String selectedValue : selectedValueList) {
					rightStringList.remove(selectedValue);
					leftStringList.add(selectedValue);
					filter.remEventType(eventClassList.get(selectedValue));
				}
			}
			refreshListModel(Side.LEFT);
			refreshListModel(Side.RIGHT);
		}
	}

	private void refreshListModel(Side side) {
		if (side.equals(Side.LEFT)) {
			leftListModel.removeAllElements();
			String filter = leftTextField.getText().toLowerCase();
			if (filter.equals("type to filter"))
				filter = "";
			for (String selectedValue : leftStringList) {
				if (selectedValue.toLowerCase().contains(filter))
					for (int i = 0; i <= leftListModel.size(); i++) {
						if ((i == leftListModel.size())
								|| (leftListModel.get(i).compareToIgnoreCase(
										selectedValue) > 0)) {
							leftListModel.add(i, selectedValue);
							break;
						}
					}
			}
		}
		if (side.equals(Side.RIGHT)) {
			rightListModel.removeAllElements();
			String filter = rightTextField.getText().toLowerCase();
			if (filter.equals("type to filter"))
				filter = "";
			for (String selectedValue : rightStringList) {
				if (selectedValue.toLowerCase().contains(filter))
					for (int i = 0; i <= rightListModel.size(); i++) {
						if ((i == rightListModel.size())
								|| (rightListModel.get(i).compareToIgnoreCase(
										selectedValue) > 0)) {
							rightListModel.add(i, selectedValue);
							break;
						}
					}
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		JTextField selectedField;
		if (e.getSource() instanceof JTextField) {
			selectedField = (JTextField) e.getSource();
			if (selectedField.getText().equals("Type to filter")) {
				selectedField.setFont(selectedField.getFont().deriveFont(
						Font.PLAIN));
				selectedField.setText("");
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField selectedField;
		if (e.getSource() instanceof JTextField) {
			selectedField = (JTextField) e.getSource();
			if (selectedField.getText().equals("")) {
				selectedField.setFont(selectedField.getFont().deriveFont(
						Font.ITALIC));
				selectedField.setText("Type to filter");
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		JTextField textField;
		if (e.getSource() instanceof JTextField) {
			textField = (JTextField) e.getSource();
			if (textField.getName().equals("LeftTextField")) {
				refreshListModel(Side.LEFT);
			}
			if (textField.getName().equals("RightTextField")) {
				refreshListModel(Side.RIGHT);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}
}
