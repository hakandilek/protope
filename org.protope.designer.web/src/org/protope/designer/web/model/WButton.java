package org.protope.designer.web.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WButton extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WButton16.gif";//$NON-NLS-1$

	private String text = WebPaletteMessages.WebPalette_Tool_WButton;
	private boolean selected = false;

	public static Image ICON = createImage(WButton.class, ICON_PATH);

	private static int count;

	public WButton() {
		super();
		size.width = 50;
		size.height = 22;
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

	public void setSize(Dimension d) {
		super.setSize(d);
	}

	public String toString() {
		return WebPaletteMessages.WebPalette_Tool_WButton;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		String old = this.text;
		this.text = text;
		firePropertyChange("text", old, text); //$NON-NLS-1$
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		boolean old = this.selected;
		this.selected = selected;
		firePropertyChange("selected", old, selected); //$NON-NLS-1$
	}

	@Override
	public Object clone() {
		WButton clone = new WButton();
		clone.setText(text);
		clone.setSelected(selected);
		return clone;
	}

	
}
