package org.protope.designer.web.model;

import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.property.BasePropertyHandler;
import org.protope.designer.base.model.property.PropertyHandler;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WTextArea extends WTextEdit {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WTextArea16.gif";//$NON-NLS-1$

	public static Image ICON = createImage(WTextArea.class, ICON_PATH);

	private static int count;

	public WTextArea() {
		super();
		size.width = 200;
		size.height = 100;
	}

	public Image getIconImage() {
		return ICON;
	}

	protected String getNewID() {
		return Integer.toString(count++);
	}

	public String toString() {
		return WebPaletteMessages.WebPalette_Tool_WTextArea;
	}

	@Override
	public PropertyHandler getPropertyHandler() {
		return new BasePropertyHandler<WTextArea>(this);
	}

}
