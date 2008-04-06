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

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.protope.designer.base.model.UIDiagram;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.base.model.commands.DeleteCommand;

public class BaseElementEditPolicy extends
		org.eclipse.gef.editpolicies.ComponentEditPolicy {

	protected Command createDeleteCommand(GroupRequest request) {
		Object parent = getHost().getParent().getModel();
		DeleteCommand deleteCmd = new DeleteCommand();
		deleteCmd.setParent((UIDiagram) parent);
		deleteCmd.setChild((UIElementPart) getHost().getModel());
		return deleteCmd;
	}

}
