package org.protope.designer.web.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.base.model.property.PropertyHandler;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WTable extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WTable16.gif";//$NON-NLS-1$

	private Integer columnCount = 4;
	private Integer rowCount = 10;

	public static Image ICON = createImage(WTable.class, ICON_PATH);

	private static int count;

	public WTable() {
		super();
		size.width = 300;
		size.height = 200;
	}

	public Image getIconImage() {
		return ICON;
	}

	protected String getNewID() {
		return Integer.toString(count++);
	}

	public Dimension getSize() {
		return new Dimension(size.width, size.height);
	}

	public String toString() {
		return WebPaletteMessages.WebPalette_Tool_WTable;
	}

	
	public Integer getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(Integer columnCount) {
		Integer old = this.columnCount;
		this.columnCount = columnCount;
		firePropertyChange("columnCount", old, columnCount);
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		Integer old = this.rowCount;
		this.rowCount = rowCount;
		firePropertyChange("rowCount", old, rowCount);
	}

	@Override
	public Object clone() {
		WTable clone = new WTable();
		clone.setColumnCount(columnCount);
		clone.setRowCount(rowCount);
		return clone;
	}

	@Override
	public PropertyHandler getPropertyHandler() {
		return new WTableProperties(this);
	}

	
}
