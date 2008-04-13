package org.protope.designer.web.model;

import java.io.IOException;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WNote extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WNote16.gif"; //$NON-NLS-1$

	private String text = WebPaletteMessages.WebPalette_Tool_WNote;

	public static Image ICON = createImage(WNote.class, ICON_PATH);

	private static int count;

	public WNote() {
		super();
		size.width = 50;
	}

	public String getLabelContents() {
		return text;
	}

	public Image getIconImage() {
		return ICON;
	}

	protected String getNewID() {
		return Integer.toString(count++);
	}

	public Dimension getSize() {
		return new Dimension(size.width, -1);
	}

	private void readObject(java.io.ObjectInputStream s) throws IOException,
			ClassNotFoundException {
		s.defaultReadObject();
	}

	public void setSize(Dimension d) {
		d.height = -1;
		super.setSize(d);
	}

	public void setLabelContents(String s) {
		text = s;
		firePropertyChange("labelContents", null, text); //$NON-NLS-2$//$NON-NLS-1$
	}

	public String toString() {
		return WebPaletteMessages.WebPalette_Tool_WNote
				+ " #" + getID() + " " //$NON-NLS-1$ //$NON-NLS-2$
				+ WebPaletteMessages.WebPalette_Tool_WNote_Text
				+ "=" + getLabelContents(); //$NON-NLS-1$ 
	}

	@Override
	public Object clone() {
		WNote clone = new WNote();
		clone.setLabelContents(text);
		return clone;
	}

}
