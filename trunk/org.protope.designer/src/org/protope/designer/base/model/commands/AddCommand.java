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
package org.protope.designer.base.model.commands;

import org.eclipse.gef.commands.Command;
import org.protope.designer.base.model.UIDiagram;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.i18n.ProtopeMessages;

public class AddCommand extends Command {

	private UIElementPart child;
	private UIDiagram parent;
	private int index = -1;

	public AddCommand() {
		super(ProtopeMessages.AddCommand_Label);
	}

	public void execute() {
		if (index < 0)
			parent.addChild(child);
		else
			parent.addChild(child, index);
	}

	public UIDiagram getParent() {
		return parent;
	}

	public void redo() {
		if (index < 0)
			parent.addChild(child);
		else
			parent.addChild(child, index);
	}

	public void setChild(UIElementPart subpart) {
		child = subpart;
	}

	public void setIndex(int i) {
		index = i;
	}

	public void setParent(UIDiagram newParent) {
		parent = newParent;
	}

	public void undo() {
		parent.removeChild(child);
	}

}
