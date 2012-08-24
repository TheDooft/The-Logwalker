package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import report.Fight;
import filter.Filter;

public class FiltersTab extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Fight fight;
	private FilterWindow filterWindow;

	public FiltersTab(Fight fight) {
		this.fight = fight;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		filterWindow = new FilterWindow();
		filterWindow.getCancelButton().addActionListener(this);
		filterWindow.getOkButton().addActionListener(this);
		
		JButton addButton = new JButton();
		addButton.setName("add");
		addButton.setText("Add a new filter");
		addButton.addActionListener(this);

		JPanel filtersPanel = new JPanel();
		filtersPanel.add(addButton);
		filtersPanel.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));

		JButton applyButton = new JButton();
		applyButton.setText("Apply Filters");

		this.add(filtersPanel);
		this.add(applyButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton;
		Filter filter = new Filter();
		;
		if (e.getSource() instanceof JButton) {
			clickedButton = (JButton) e.getSource();
			if (clickedButton.getName() == "add") {
				filter = new Filter();
				filterWindow.setFilter(filter);
				filterWindow.setVisible(true);
				filterWindow.requestFocus();
				
			}
			if (clickedButton.getName() == "filterWindowOk"){
				filterWindow.setVisible(false);
				fight.setFilter(filterWindow.getFilter());
			}
			if (clickedButton.getName() == "filterWindowCancel"){
				filterWindow.setVisible(false);
			}
		}

	}

}
