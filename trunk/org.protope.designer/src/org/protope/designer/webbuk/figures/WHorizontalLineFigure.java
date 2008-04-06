package org.protope.designer.webbuk.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class WHorizontalLineFigure extends Figure {

	public WHorizontalLineFigure() {
		super();
		setBackgroundColor(ColorConstants.tooltipBackground);
		setForegroundColor(ColorConstants.tooltipForeground);
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle rect = getBounds().getCopy();

		graphics.translate(getLocation());

		graphics.drawLine(0, 0, rect.width, 0);

		graphics.translate(getLocation().getNegated());
	}

}
