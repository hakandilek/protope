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

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.protope.designer.base.edit.BaseEditPart;
import org.protope.designer.i18n.ProtopeMessages;
import org.protope.designer.webbuk.figures.WNoteFigure;
import org.protope.designer.webbuk.model.WNote;

public class WNoteEditPart extends BaseEditPart {

	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getValue(AccessibleControlEvent e) {
				e.result = getLabel().getLabelContents();
			}

			public void getName(AccessibleEvent e) {
				e.result = ProtopeMessages.UIPlugin_Tool_CreationTool_Label;
			}
		};
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new WNoteDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new WNoteEditPolicy());
	}

	protected IFigure createFigure() {
		WNoteFigure label = new WNoteFigure();
		return label;
	}

	private WNote getLabel() {
		return (WNote) getModel();
	}

	private void performDirectEdit() {
		new WNoteEditManager(this, new WNoteCellEditorLocator(
				(WNoteFigure) getFigure())).show();
	}

	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT)
			performDirectEdit();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("labelContents"))//$NON-NLS-1$
			refreshVisuals();
		else
			super.propertyChange(evt);
	}

	protected void refreshVisuals() {
		((WNoteFigure) getFigure()).setText(getLabel()
				.getLabelContents());
		super.refreshVisuals();
	}

}
