package org.protope.designer.webbuk.model;

import java.io.IOException;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.i18n.ProtopeMessages;

public class WLabel extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WLabel16.gif";//$NON-NLS-1$

	private String text = ProtopeMessages.UIPlugin_Tool_CreationTool_WLabel;

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

	private void readObject(java.io.ObjectInputStream s) throws IOException,
			ClassNotFoundException {
		s.defaultReadObject();
	}

	public void setSize(Dimension d) {
		super.setSize(d);
	}

	public String toString() {
		return ProtopeMessages.UIPlugin_Tool_CreationTool_WLabel;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		String old = this.text;
		this.text = text;
		firePropertyChange("text", old, text); //$NON-NLS-1$
	}

	
}
