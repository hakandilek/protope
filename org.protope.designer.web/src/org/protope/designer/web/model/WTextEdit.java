package org.protope.designer.web.model;

import java.io.IOException;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WTextEdit extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WTextEdit16.gif";//$NON-NLS-1$

	public static Image ICON = createImage(WTextEdit.class, ICON_PATH);

	private static int count;

	public WTextEdit() {
		super();
		size.width = 100;
		size.height = 20;
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
		return WebPaletteMessages.WebPalette_Tool_WTextEdit;
	}

	@Override
	public Object clone() {
		return new WTextEdit();
	}

}
