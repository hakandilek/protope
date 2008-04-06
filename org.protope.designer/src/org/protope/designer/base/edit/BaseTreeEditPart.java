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
package org.protope.designer.base.edit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.protope.designer.base.model.UIDiagram;
import org.protope.designer.base.model.UIElementPart;

/**
 * EditPart for Logic components in the Tree.
 */
public class BaseTreeEditPart extends
		org.eclipse.gef.editparts.AbstractTreeEditPart implements
		PropertyChangeListener {

	/**
	 * Constructor initializes this with the given model.
	 * 
	 * @param model
	 *            Model for this.
	 */
	public BaseTreeEditPart(Object model) {
		super(model);
	}

	public void activate() {
		super.activate();
		getUISubpart().addPropertyChangeListener(this);
	}

	/**
	 * Creates and installs pertinent EditPolicies for this.
	 */
	protected void createEditPolicies() {
		EditPolicy component;
		component = new BaseElementEditPolicy();
		installEditPolicy(EditPolicy.COMPONENT_ROLE, component);
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
				new BaseTreeEditPolicy());
	}

	public void deactivate() {
		getUISubpart().removePropertyChangeListener(this);
		super.deactivate();
	}

	/**
	 * Returns the model of this as a UISubpart.
	 * 
	 * @return Model of this.
	 */
	protected UIElementPart getUISubpart() {
		return (UIElementPart) getModel();
	}

	/**
	 * Returns <code>null</code> as a Tree EditPart holds no children under
	 * it.
	 * 
	 * @return <code>null</code>
	 */
	protected List<?> getModelChildren() {
		return Collections.EMPTY_LIST;
	}

	public void propertyChange(PropertyChangeEvent change) {
		if (change.getPropertyName().equals(UIDiagram.CHILDREN)) {
			if (change.getOldValue() instanceof Integer)
				// new child
				addChild(createChild(change.getNewValue()), ((Integer) change
						.getOldValue()).intValue());
			else
				// remove child
				removeChild((EditPart) getViewer().getEditPartRegistry().get(
						change.getOldValue()));
		} else
			refreshVisuals();
	}

	/**
	 * Refreshes the visual properties of the TreeItem for this part.
	 */
	protected void refreshVisuals() {
		if (getWidget() instanceof Tree)
			return;
		Image image = getUISubpart().getIcon();
		TreeItem item = (TreeItem) getWidget();
		if (image != null)
			image.setBackground(item.getParent().getBackground());
		setWidgetImage(image);
		setWidgetText(getUISubpart().toString());
	}

}
