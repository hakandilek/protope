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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.TreeContainerEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.protope.designer.base.model.BaseDiagram;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.base.model.commands.CreateCommand;
import org.protope.designer.base.model.commands.ReorderPartCommand;

public class BaseTreeContainerEditPolicy extends TreeContainerEditPolicy {

	protected Command createCreateCommand(UIElementPart child, Rectangle r,
			int index, String label) {
		CreateCommand cmd = new CreateCommand();
		Rectangle rect;
		if (r == null) {
			rect = new Rectangle();
			rect.setSize(new Dimension(-1, -1));
		} else {
			rect = r;
		}
		cmd.setLocation(rect);
		cmd.setParent((BaseDiagram) getHost().getModel());
		cmd.setChild(child);
		cmd.setLabel(label);
		if (index >= 0)
			cmd.setIndex(index);
		return cmd;
	}

	protected Command getAddCommand(ChangeBoundsRequest request) {
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel("Add in LogicTreeContainerEditPolicy");//$NON-NLS-1$
		@SuppressWarnings("unchecked")
		List editparts = request.getEditParts();
		int index = findIndexOfTreeItemAt(request.getLocation());

		for (int i = 0; i < editparts.size(); i++) {
			EditPart child = (EditPart) editparts.get(i);
			if (isAncestor(child, getHost()))
				command.add(UnexecutableCommand.INSTANCE);
			else {
				UIElementPart childModel = (UIElementPart) child.getModel();
				command.add(createCreateCommand(childModel, new Rectangle(
						new org.eclipse.draw2d.geometry.Point(), childModel
								.getSize()), index, "Reparent UISubpart"));//$NON-NLS-1$
			}
		}
		return command;
	}

	protected Command getCreateCommand(CreateRequest request) {
		UIElementPart child = (UIElementPart) request.getNewObject();
		int index = findIndexOfTreeItemAt(request.getLocation());
		return createCreateCommand(child, null, index, "Create UISubpart");//$NON-NLS-1$
	}

	protected Command getMoveChildrenCommand(ChangeBoundsRequest request) {
		CompoundCommand command = new CompoundCommand();
		@SuppressWarnings("unchecked")
		List editparts = request.getEditParts();
		@SuppressWarnings("unchecked")
		List children = getHost().getChildren();
		int newIndex = findIndexOfTreeItemAt(request.getLocation());

		for (int i = 0; i < editparts.size(); i++) {
			EditPart child = (EditPart) editparts.get(i);
			int tempIndex = newIndex;
			int oldIndex = children.indexOf(child);
			if (oldIndex == tempIndex || oldIndex + 1 == tempIndex) {
				command.add(UnexecutableCommand.INSTANCE);
				return command;
			} else if (oldIndex <= tempIndex) {
				tempIndex--;
			}
			command.add(new ReorderPartCommand((UIElementPart) child.getModel(),
					(BaseDiagram) getHost().getModel(), tempIndex));
		}
		return command;
	}

	protected boolean isAncestor(EditPart source, EditPart target) {
		if (source == target)
			return true;
		if (target.getParent() != null)
			return isAncestor(source, target.getParent());
		return false;
	}

}
