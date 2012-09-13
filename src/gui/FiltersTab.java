package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
	private List<Filter> filterList;
	
	public FiltersTab(Fight fight) {
		filterList = new ArrayList<Filter>();
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
		applyButton.setName("applyButton");
		applyButton.addActionListener(this);

		this.add(filtersPanel);
		this.add(applyButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton;
		Filter filter = new Filter();
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
				filterList.add(filterWindow.getFilter());
			}
			if (clickedButton.getName() == "filterWindowCancel"){
				filterWindow.setVisible(false);
			}
			if (clickedButton.getName().equals("applyButton")){
				fight.setFilters(filterList);
				ReportTab reportTab = (ReportTab) this.getParent().getParent();
				reportTab.getLogTab().updateContent();
			}
		}

	}

}
