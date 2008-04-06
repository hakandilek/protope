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
package org.protope.designer.webbuk.edit;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;
import org.protope.designer.webbuk.figures.WNoteFigure;

final public class WNoteCellEditorLocator implements CellEditorLocator {

	private WNoteFigure figure;

	public WNoteCellEditorLocator(WNoteFigure figure) {
		setLabel(figure);
	}

	public void relocate(CellEditor celleditor) {
		Text text = (Text) celleditor.getControl();
		Rectangle rect = figure.getClientArea();
		figure.translateToAbsolute(rect);
		org.eclipse.swt.graphics.Rectangle trim = text.computeTrim(0, 0, 0, 0);
		rect.translate(trim.x, trim.y);
		rect.width += trim.width;
		rect.height += trim.height;
		text.setBounds(rect.x, rect.y, rect.width, rect.height);
	}

	/**
	 * Returns the stickyNote figure.
	 */
	protected WNoteFigure getLabel() {
		return figure;
	}

	/**
	 * Sets the Sticky note figure.
	 * 
	 * @param figure
	 *            The figure to set
	 */
	protected void setLabel(WNoteFigure figure) {
		this.figure = figure;
	}

}