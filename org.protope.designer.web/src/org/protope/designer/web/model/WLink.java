package org.protope.designer.web.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.base.model.property.PropertyHandler;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WLink extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WLink16.gif";//$NON-NLS-1$

	private String text = WebPaletteMessages.WebPalette_Tool_WLink;

	private String target = null;

	public static Image ICON = createImage(WLink.class, ICON_PATH);

	private static int count;

	public WLink() {
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
		return WebPaletteMessages.WebPalette_Tool_WLink;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		String old = this.text;
		this.text = text;
		firePropertyChange("text", old, text); //$NON-NLS-1$
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		String old = this.target;
		this.target = target;
		firePropertyChange("target", old, target); //$NON-NLS-1$
	}

	@Override
	public Object clone() {
		return new WLink();
	}

	@Override
	public PropertyHandler getPropertyHandler() {
		return new WLinkProperties(this);
	}

	
}
