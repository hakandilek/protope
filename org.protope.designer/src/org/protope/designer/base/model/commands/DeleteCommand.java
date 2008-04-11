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
import org.protope.designer.base.model.UIGuide;
import org.protope.designer.i18n.ProtopeMessages;

public class DeleteCommand extends Command {

	private UIElementPart child;
	private BaseDiagram parent;
	private UIGuide vGuide, hGuide;
	private int vAlign, hAlign;
	private int index = -1;

	public DeleteCommand() {
		super(ProtopeMessages.DeleteCommand_Label);
	}

	private void detachFromGuides(UIElementPart part) {
		if (part.getVerticalGuide() != null) {
			vGuide = part.getVerticalGuide();
			vAlign = vGuide.getAlignment(part);
			vGuide.detachPart(part);
		}
		if (part.getHorizontalGuide() != null) {
			hGuide = part.getHorizontalGuide();
			hAlign = hGuide.getAlignment(part);
			hGuide.detachPart(part);
		}

	}

	public void execute() {
		primExecute();
	}

	protected void primExecute() {
		detachFromGuides(child);
		index = parent.getChildren().indexOf(child);
		parent.removeChild(child);
	}

	private void reattachToGuides(UIElementPart part) {
		if (vGuide != null)
			vGuide.attachPart(part, vAlign);
		if (hGuide != null)
			hGuide.attachPart(part, hAlign);
	}

	public void redo() {
		primExecute();
	}

	public void setChild(UIElementPart c) {
		child = c;
	}

	public void setParent(BaseDiagram p) {
		parent = p;
	}

	public void undo() {
		parent.addChild(child, index);
		reattachToGuides(child);
	}

}
