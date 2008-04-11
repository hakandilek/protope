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
import org.protope.designer.base.model.BaseDiagram;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.i18n.ProtopeMessages;

public class ReorderPartCommand extends Command {

	private int oldIndex, newIndex;
	private UIElementPart child;
	private BaseDiagram parent;

	public ReorderPartCommand(UIElementPart child, BaseDiagram parent, int newIndex) {
		super(ProtopeMessages.ReorderPartCommand_Label);
		this.child = child;
		this.parent = parent;
		this.newIndex = newIndex;
	}

	public void execute() {
		oldIndex = parent.getChildren().indexOf(child);
		parent.removeChild(child);
		parent.addChild(child, newIndex);
	}

	public void undo() {
		parent.removeChild(child);
		parent.addChild(child, oldIndex);
	}

}
