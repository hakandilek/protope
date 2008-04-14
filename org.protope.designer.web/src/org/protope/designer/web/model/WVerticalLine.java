package org.protope.designer.web.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WVerticalLine extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WVerticalLine16.gif";//$NON-NLS-1$

	public static Image ICON = createImage(WVerticalLine.class, ICON_PATH);

	private static int count;

	public WVerticalLine() {
		super();
		size.height = 50;
	}

	public Dimension getSize(){
		return new Dimension(1, size.height);
	}

	public void setSize(Dimension d) {
		d.width = 1;
		super.setSize(d);
	}

	public Image getIconImage() {
		return ICON;
	}

	protected String getNewID() {
		return Integer.toString(count++);
	}

	public String toString() {
		return WebPaletteMessages.WebPalette_Tool_WImage;
	}

	@Override
	public Object clone() {
		return new WVerticalLine();
	}

}
