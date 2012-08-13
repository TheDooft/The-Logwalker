package tools;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import world.Unit;

public class UnitModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6994560067308241505L;

	private final String[] headers = { "Name", "uid", "id" };
	private ArrayList<Unit> unitList;
	private ArrayList<Unit> extandList;

	public UnitModel(ArrayList<Unit> unitList) {
		this.unitList = new ArrayList<Unit>();
		for (Unit unit : unitList)
			if (unit.getOwner() == Unit.nil)
				this.unitList.add(unit);
		this.extandList = new ArrayList<Unit>();
	}

	@Override
	public int getRowCount() {
		return unitList.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public String getColumnName(int column) {
		return headers[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			Unit unit = unitList.get(rowIndex);
			String name = unit.getName();
			while (unit.getOwner() != Unit.nil) {
				name = "    " + name;
				unit = unit.getOwner();
			}
			return name;
		case 1:
			return "0x" + Long.toHexString(unitList.get(rowIndex).getUid());
		case 2:
			return unitList.get(rowIndex).getId();
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return Integer.class;
		default:
			return Object.class;
		}
	}

	public String getTooltip(int rowAtPoint) {
		return unitList.get(rowAtPoint).getTooltip();
	}

	public void clickOnRow(int selectedRow) {
		Unit unit = unitList.get(selectedRow);
		ArrayList<Unit> summonList = unit.getSummonList();
		if (summonList.isEmpty())
			return;
		int i = unitList.indexOf(unit);
		if (extandList.contains(unit)) {
			while ((unitList.size() > i + 1)
					&& (unitList.get(i + 1).getOwner() == unit))
				unitList.remove(i + 1);
			extandList.remove(unit);
		} else {
			for (Unit pet : summonList)
				if (unitList.size() > i + 1)
					unitList.add(i + 1, pet);
				else
					unitList.add(pet);
			extandList.add(unit);
		}
	}

}
