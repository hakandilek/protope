package org.protope.designer.web.model;

import java.io.IOException;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WHorizontalLine extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WHorizontalLine16.gif";//$NON-NLS-1$

	public static Image ICON = createImage(WHorizontalLine.class, ICON_PATH);

	private static int count;

	public WHorizontalLine() {
		super();
		size.width = 50;
	}

	public Dimension getSize() {
		return new Dimension(size.width, 1);
	}

	public void setSize(Dimension d) {
		d.height = 1;
		super.setSize(d);
	}

	public Image getIconImage() {
		return ICON;
	}

	protected String getNewID() {
		return Integer.toString(count++);
	}

	private void readObject(java.io.ObjectInputStream s) throws IOException,
			ClassNotFoundException {
		s.defaultReadObject();
	}

	public String toString() {
		return WebPaletteMessages.WebPalette_Tool_WImage;
	}

	@Override
	public Object clone() {
		return new WHorizontalLine();
	}

}
