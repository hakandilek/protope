package org.protope.designer.web.figures;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

public class WComboboxFigure extends Figure {

	final static Color COLOR_BOTTOM_RIGHT = new Color(
			ColorConstants.tooltipBackground.getDevice(), 212, 208, 200);
	final static Color COLOR_TOP_LEFT = new Color(
			ColorConstants.tooltipBackground.getDevice(), 128, 128, 128);
	final static Color COLOR_INNER_TOP_LEFT = new Color(
			ColorConstants.tooltipBackground.getDevice(), 64, 64, 64);

	/** the inner label */
	private Label label = null;

	static final Image IMAGE = createImage("images/comboboxenabled.png"); //$NON-NLS-1$

	private static Image createImage(String name) {
		InputStream stream = WComboboxFigure.class.getResourceAsStream(name);
		Image image = new Image(null, stream);
		try {
			stream.close();
		} catch (IOException ioe) {
		}
		return image;
	}

	/**
	 * Constructs a Combobox with the passed text in its label.
	 * 
	 * @param text
	 *            The label text
	 */
	public WComboboxFigure(String text) {
		getInsets().left = 2;
		setLayoutManager(new BorderLayout());
		add(label = new Label(text), BorderLayout.LEFT, -1);
	}

	/**
	 * Constructs an empty Combobox.
	 */
	public WComboboxFigure() {
		this("");
	}

	public void setText(String s) {
		label.setText(s);
	}

	public String getText() {
		return label.getText();
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		//draw label
		super.paintFigure(graphics);

		Rectangle rect = getBounds().getCopy();
		graphics.translate(getLocation());

		//draw the text editor outline
		graphics.setForegroundColor(COLOR_BOTTOM_RIGHT);
		graphics.drawLine(1, rect.height-1, rect.width-1, rect.height-1);
		graphics.drawLine(rect.width-1, rect.height, rect.width-1, 1);

		graphics.setForegroundColor(COLOR_TOP_LEFT);
		graphics.drawLine(0, 0, 0, rect.height);
		graphics.drawLine(0, 0, rect.width, 0);

		graphics.setForegroundColor(COLOR_INNER_TOP_LEFT);
		graphics.drawLine(1, 1, 1, rect.height - 1);
		graphics.drawLine(1, 1, rect.width - 1, 1);

		//draw dropdown icon on the left
		graphics.drawImage(IMAGE, rect.width-16, 1);
		
		graphics.translate(getLocation().getNegated());
	}
	
	
	
}
