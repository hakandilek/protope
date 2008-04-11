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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.protope.designer.base.model.BaseDiagram;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.base.model.commands.OrphanChildCommand;
import org.protope.designer.i18n.ProtopeMessages;

public class BaseContainerEditPolicy extends ContainerEditPolicy {

	protected Command getCreateCommand(CreateRequest request) {
		return null;
	}

	public Command getOrphanChildrenCommand(GroupRequest request) {
		@SuppressWarnings("unchecked")
		List<EditPart> parts = request.getEditParts();
		CompoundCommand result = new CompoundCommand(
				ProtopeMessages.UIContainerEditPolicy_OrphanCommandLabelText);
		for (int i = 0; i < parts.size(); i++) {
			OrphanChildCommand orphan = new OrphanChildCommand();
			EditPart part = parts.get(i);
			orphan.setChild((UIElementPart) part.getModel());
			orphan.setParent((BaseDiagram) getHost().getModel());
			orphan
					.setLabel(ProtopeMessages.UIElementEditPolicy_OrphanCommandLabelText);
			result.add(orphan);
		}
		return result.unwrap();
	}

}
