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

public class WVerticalLine extends UIElementPart {
	static final long serialVersionUID = 1;

	public static final String ICON_PATH = "icons/WVerticalLine16.gif";//$NON-NLS-1$

	public static Image ICON = createImage(WVerticalLine.class, ICON_PATH);

	private static int count;

	public WVerticalLine() {
		super();
		size.height = 50;
	}

	public Dimension getSize(){
		return new Dimension(1, size.height);
	}

	public void setSize(Dimension d) {
		d.width = 1;
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
		return ProtopeMessages.UIPlugin_Tool_CreationTool_Image;
	}

}
