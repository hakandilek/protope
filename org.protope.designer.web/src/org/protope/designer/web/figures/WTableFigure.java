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
			ColorConstants.tooltipBackground.getDevice(), 247, 247, 247);

	private Integer columnCount;
	private Integer rowCount;

	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle rect = getBounds().getCopy();

		graphics.translate(getLocation());

		// draw outer frame
		graphics.setForegroundColor(COLOR_OUTER_FRAME);
		graphics.drawRectangle(0, 0, rect.width - 1, rect.height - 1);

		// fill header
		final int headerHeight = 24;
		graphics.setBackgroundColor(COLOR_HEADER);
		graphics.fillRectangle(1, 1, rect.width - 2, headerHeight);

		// fill rows
		graphics.setBackgroundColor(COLOR_ROW_ODD);
		graphics.fillRectangle(1, headerHeight + 1, rect.width - 2, rect.height
				- headerHeight - 2);

		// draw vertical lines
		graphics.setForegroundColor(COLOR_INNER_FRAME);
		final int colWidth = (rect.width - 3) / columnCount;
		final int height = rect.height - 3;
		int x = 1;
		for (int col = 0; col < columnCount; col++) {
			graphics.drawLine(x, 1, x, height);
			x += colWidth;
		}
		graphics.drawLine(rect.width - 2, 1, rect.width - 2, height);

		// draw horizontal lines
		final int width = rect.width - 2;
		final int rowHeight = (rect.height - headerHeight - 3) / rowCount;
		int y = headerHeight;
		graphics.drawLine(1, 1, width, 1);// top line
		for (int row = 0; row < rowCount; row++) {
			graphics.drawLine(1, y, width, y);
			y += rowHeight;
		}
		graphics.drawLine(1, rect.height - 2, width, rect.height - 2);// bottom

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
