package org.protope.designer.web.model;

import java.io.IOException;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WImage extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WImage16.gif";//$NON-NLS-1$

	public static Image ICON = createImage(WImage.class, ICON_PATH);

	private static int count;

	public WImage() {
		super();
		size.width = 50;
		size.height = 50;
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

	private void readObject(java.io.ObjectInputStream s) throws IOException,
			ClassNotFoundException {
		s.defaultReadObject();
	}

	public void setSize(Dimension d) {
		super.setSize(d);
	}

	public String toString() {
		return WebPaletteMessages.WebPalette_Tool_WImage;
	}

	@Override
	public Object clone() {
		return new WImage();
	}

}
