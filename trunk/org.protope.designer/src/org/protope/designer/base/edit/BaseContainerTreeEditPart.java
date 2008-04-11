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

import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.protope.designer.base.model.BaseDiagram;
import org.protope.designer.base.model.UIElement;

/**
 * Tree EditPart for the Container.
 */
public class BaseContainerTreeEditPart extends BaseTreeEditPart {

	/**
	 * Constructor, which initializes this using the model given as input.
	 */
	public BaseContainerTreeEditPart(Object model) {
		super(model);
	}

	/**
	 * Creates and installs pertinent EditPolicies.
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new BaseContainerEditPolicy());
		installEditPolicy(EditPolicy.TREE_CONTAINER_ROLE,
				new BaseTreeContainerEditPolicy());
		// If this editpart is the contents of the viewer, then it is not
		// deletable!
		if (getParent() instanceof RootEditPart)
			installEditPolicy(EditPolicy.COMPONENT_ROLE,
					new RootComponentEditPolicy());
	}

	/**
	 * Returns the model of this as a LogicDiagram.
	 * 
	 * @return Model of this.
	 */
	protected BaseDiagram getDiagram() {
		return (BaseDiagram) getModel();
	}

	/**
	 * Returns the children of this from the model, as this is capable enough of
	 * holding EditParts.
	 * 
	 * @return List of children.
	 */
	protected List<UIElement> getModelChildren() {
		return getDiagram().getChildren();
	}

}
