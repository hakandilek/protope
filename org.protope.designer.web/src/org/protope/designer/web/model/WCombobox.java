package org.protope.designer.web.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.base.model.property.PropertyHandler;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WCombobox extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WCombobox16.gif";//$NON-NLS-1$

	private String text = WebPaletteMessages.WebPalette_Tool_WCombobox;
	private boolean selected = false;

	public static Image ICON = createImage(WCombobox.class, ICON_PATH);

	private static int count;

	public WCombobox() {
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

	public void setSize(Dimension d) {
		d.height = 20;
		super.setSize(d);
	}

	public String toString() {
		return WebPaletteMessages.WebPalette_Tool_WCombobox;
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
		WCombobox clone = new WCombobox();
		clone.setText(text);
		clone.setSelected(selected);
		return clone;
	}

	@Override
	public PropertyHandler getPropertyHandler() {
		return new WComboboxProperties(this);
	}

	
}
