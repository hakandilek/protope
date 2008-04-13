package org.protope.designer.web.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class WVerticalLineFigure extends Figure {

	public WVerticalLineFigure() {
		super();
		setBackgroundColor(ColorConstants.tooltipBackground);
		setForegroundColor(ColorConstants.tooltipForeground);
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle rect = getBounds().getCopy();

		graphics.translate(getLocation());

		graphics.drawLine(0, 0, 0, rect.height);

		graphics.translate(getLocation().getNegated());
	}

}
