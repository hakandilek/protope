/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.protope.designer.webbuk.model;

import java.io.IOException;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.i18n.ProtopeMessages;

public class WNote extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WNote16.gif"; //$NON-NLS-1$

	private String text = ProtopeMessages.UIPlugin_Tool_CreationTool_Label;

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
		return ProtopeMessages.UIPlugin_Tool_CreationTool_Label
				+ " #" + getID() + " " //$NON-NLS-1$ //$NON-NLS-2$
				+ ProtopeMessages.PropertyDescriptor_Label_Text
				+ "=" + getLabelContents(); //$NON-NLS-1$ 
	}

}
