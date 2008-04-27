package org.protope.designer.web.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class WTableFigure extends Figure {

	final static Color COLOR_HEADER = new Color(
			ColorConstants.tooltipBackground.getDevice(), 239, 239, 239);
	final static Color COLOR_OUTER_FRAME = new Color(
			ColorConstants.tooltipBackground.getDevice(), 0, 0, 0);
	final static Color COLOR_INNER_FRAME = new Color(
			ColorConstants.tooltipBackground.getDevice(), 206, 206, 206);
	final static Color COLOR_ROW_EVEN = new Color(
			ColorConstants.tooltipBackground.getDevice(), 255, 255, 255);
	final static Color COLOR_ROW_ODD = new Color(
			ColorConstants.tooltipBackground.getDevice(), 222, 222, 222);

	private Integer columnCount;
	private Integer rowCount;

	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle rect = getBounds().getCopy();

		graphics.translate(getLocation());

		// draw outer frame
		graphics.setForegroundColor(COLOR_OUTER_FRAME);
		graphics.drawRectangle(0, 0, rect.width - 1, rect.height - 1);

		// draw column headers
		final int headerHeight = 16;
		graphics.setBackgroundColor(COLOR_INNER_FRAME);
		graphics.setForegroundColor(COLOR_INNER_FRAME);
		graphics.fillRectangle(1, 1, rect.width - 2, headerHeight);
		graphics.setForegroundColor(COLOR_HEADER);
		final int width = (rect.width - 3) / columnCount;
		final int height = headerHeight - 1;
		int x = 1;
		for (int col = 0; col < columnCount; col++) {
			final int x1 = x;
			x = x1 + width;
			final int wid = col < columnCount - 1 ? width : rect.width - x1 - 2;
			Rectangle colrec = new Rectangle(x1, 1, wid, height);
			graphics.drawRectangle(colrec);
		}
		graphics.translate(getLocation().getNegated());
	}

	public Integer getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(Integer columnCount) {
		this.columnCount = columnCount;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

}
