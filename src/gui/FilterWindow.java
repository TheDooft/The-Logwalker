package gui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		mainPannel.add(new FilterWindowTypePanel());
		mainPannel.add(buttonPannel);
		
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
