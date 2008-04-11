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

import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.protope.designer.base.model.BaseDiagram;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.i18n.ProtopeMessages;

public class CreateCommand extends org.eclipse.gef.commands.Command {

	private UIElementPart child;
	private Rectangle rect;
	private BaseDiagram parent;
	private int index = -1;

	public CreateCommand() {
		super(ProtopeMessages.CreateCommand_Label);
	}

	public boolean canExecute() {
		return child != null && parent != null;
	}

	public void execute() {
		if (rect != null) {
			Insets expansion = getInsets();
			if (!rect.isEmpty())
				rect.expand(expansion);
			else {
				rect.x -= expansion.left;
				rect.y -= expansion.top;
			}
			child.setLocation(rect.getLocation());
			if (!rect.isEmpty())
				child.setSize(rect.getSize());
		}
		redo();
	}

	private Insets getInsets() {
		return new Insets();
	}

	public void redo() {
		parent.addChild(child, index);
	}

	public void setChild(UIElementPart subpart) {
		child = subpart;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setLocation(Rectangle r) {
		rect = r;
	}

	public void setParent(BaseDiagram newParent) {
		parent = newParent;
	}

	public void undo() {
		parent.removeChild(child);
	}

}
