package gui;

import javax.swing.JFrame;

import filter.Filter;

public class FilterWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Filter filter;
	
	public FilterWindow() {
		setVisible(false);
		setSize(1024, 768);
		setTitle("Filter");
		setType(Type.UTILITY);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}
}
