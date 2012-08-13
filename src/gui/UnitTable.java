package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import tools.UnitModel;

public class UnitTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8345576434876970528L;
	private UnitModel unitModel;
	
	public UnitTable(UnitModel unitModel) {
		super(unitModel);
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				((UnitTable)e.getSource()).click(e) ;
			}
		});
		this.unitModel = unitModel;
	}
	
	protected void click(MouseEvent e) {
		unitModel.clickOnRow(this.getSelectedRow());
		this.revalidate();
		this.repaint();
	}

	public String getToolTipText(MouseEvent e){
		TableModel model = getModel();
		java.awt.Point p = e.getPoint();
		return ((UnitModel) model).getTooltip(rowAtPoint(p));
	}
	
	
	
}
