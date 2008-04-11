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

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.protope.designer.base.model.BaseDiagram;
import org.protope.designer.base.model.UIElement;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.i18n.ProtopeMessages;

public class OrphanChildCommand extends Command {

	private Point oldLocation;
	private BaseDiagram diagram;
	private UIElementPart child;
	private int index;

	public OrphanChildCommand() {
		super(ProtopeMessages.OrphanChildCommand_Label);
	}

	public void execute() {
		List<UIElement> children = diagram.getChildren();
		index = children.indexOf(child);
		oldLocation = child.getLocation();
		diagram.removeChild(child);
	}

	public void redo() {
		diagram.removeChild(child);
	}

	public void setChild(UIElementPart child) {
		this.child = child;
	}

	public void setParent(BaseDiagram parent) {
		diagram = parent;
	}

	public void undo() {
		child.setLocation(oldLocation);
		diagram.addChild(child, index);
	}

}
