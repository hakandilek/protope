package org.protope.designer.webbuk.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.FontMetrics;

public class WImageFigure extends Figure {

	public WImageFigure() {
		super();
		setBackgroundColor(ColorConstants.tooltipBackground);
		setForegroundColor(ColorConstants.tooltipForeground);
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle rect = getBounds().getCopy();

		graphics.translate(getLocation());

		// fill the note
		PointList outline = new PointList();
		outline.addPoint(0, 0);
		outline.addPoint(rect.width - 1, 0);
		outline.addPoint(rect.width - 1, 0);
		outline.addPoint(rect.width - 1, rect.height - 1);
		outline.addPoint(0, rect.height - 1);
		graphics.fillPolygon(outline);

		// draw the inner outline
		PointList innerLine = new PointList();
		innerLine.addPoint(0, 0);
		innerLine.addPoint(0, rect.height - 1);
		innerLine.addPoint(rect.width - 1, rect.height - 1);
		innerLine.addPoint(rect.width - 1, 0);
		graphics.drawPolygon(innerLine);

		graphics.drawLine(0, 0, rect.width, rect.height);
		graphics.drawLine(0, rect.height, rect.width, 0);

		FontMetrics fontMetrics = graphics.getFontMetrics();
		int h = fontMetrics.getHeight();
		int cw = fontMetrics.getAverageCharWidth();
		String text = "image";
		int w = text.length() * cw;
		graphics.fillText(text, (rect.width - w) / 2, (rect.height - h) / 2);

		graphics.translate(getLocation().getNegated());
	}

}
