package org.protope.designer.webbuk.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class WTextEditFigure extends Figure {

	final static Color COLOR_BOTTOM_RIGHT = new Color(
			ColorConstants.tooltipBackground.getDevice(), 212, 208, 200);
	final static Color COLOR_TOP_LEFT = new Color(
			ColorConstants.tooltipBackground.getDevice(), 128, 128, 128);
	final static Color COLOR_INNER_TOP_LEFT = new Color(
			ColorConstants.tooltipBackground.getDevice(), 64, 64, 64);

	public WTextEditFigure() {
		super();
		setBackgroundColor(ColorConstants.tooltipBackground);
		setForegroundColor(ColorConstants.tooltipForeground);
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle rect = getBounds().getCopy();

		graphics.translate(getLocation());

		graphics.setForegroundColor(COLOR_BOTTOM_RIGHT);
		graphics.drawLine(1, rect.height-1, rect.width-1, rect.height-1);
		graphics.drawLine(rect.width-1, rect.height, rect.width-1, 1);

		graphics.setForegroundColor(COLOR_TOP_LEFT);
		graphics.drawLine(0, 0, 0, rect.height);
		graphics.drawLine(0, 0, rect.width, 0);

		graphics.setForegroundColor(COLOR_INNER_TOP_LEFT);
		graphics.drawLine(1, 1, 1, rect.height - 1);
		graphics.drawLine(1, 1, rect.width - 1, 1);

		graphics.translate(getLocation().getNegated());
	}

}
