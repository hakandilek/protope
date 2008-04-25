package org.protope.designer.web.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.base.model.property.PropertyHandler;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WLabel extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WLabel16.gif";//$NON-NLS-1$

	private String text = WebPaletteMessages.WebPalette_Tool_WLabel;

	public static Image ICON = createImage(WLabel.class, ICON_PATH);

	private static int count;

	public WLabel() {
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
		return WebPaletteMessages.WebPalette_Tool_WLabel;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		String old = this.text;
		this.text = text;
		firePropertyChange("text", old, text); //$NON-NLS-1$
	}

	@Override
	public Object clone() {
		return new WLabel();
	}

	@Override
	public PropertyHandler getPropertyHandler() {
		return new WLabelProperties(this);
	}

	
}
