package org.protope.designer.web.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.base.model.property.PropertyHandler;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WCheckbox extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WCheckbox16.gif";//$NON-NLS-1$

	private String text = WebPaletteMessages.WebPalette_Tool_WCheckbox;
	private boolean selected = false;

	public static Image ICON = createImage(WCheckbox.class, ICON_PATH);

	private static int count;

	public WCheckbox() {
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

	public String toString() {
		return WebPaletteMessages.WebPalette_Tool_WCheckbox;
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
		WCheckbox clone = new WCheckbox();
		clone.setText(text);
		clone.setSelected(selected);
		return clone;
	}

	@Override
	public PropertyHandler getPropertyHandler() {
		return new WCheckboxProperties(this);
	}

	
}
