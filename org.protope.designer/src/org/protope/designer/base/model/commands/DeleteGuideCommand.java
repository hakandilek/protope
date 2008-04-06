/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.protope.designer.base.model.commands;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.base.model.UIGuide;
import org.protope.designer.base.model.UIRuler;
import org.protope.designer.i18n.ProtopeMessages;

/**
 * @author Pratik Shah
 */
public class DeleteGuideCommand extends Command {

	private UIRuler parent;
	private UIGuide guide;
	private Map<UIElementPart, Integer> oldParts;

	public DeleteGuideCommand(UIGuide guide, UIRuler parent) {
		super(ProtopeMessages.DeleteGuideCommand_Label);
		this.guide = guide;
		this.parent = parent;
	}

	public boolean canUndo() {
		return true;
	}

	public void execute() {
		oldParts = new HashMap<UIElementPart, Integer>(guide.getMap());
		Iterator<UIElementPart> iter = oldParts.keySet().iterator();
		while (iter.hasNext()) {
			guide.detachPart((UIElementPart) iter.next());
		}
		parent.removeGuide(guide);
	}

	public void undo() {
		parent.addGuide(guide);
		Iterator<UIElementPart> iter = oldParts.keySet().iterator();
		while (iter.hasNext()) {
			UIElementPart part = (UIElementPart) iter.next();
			guide.attachPart(part, ((Integer) oldParts.get(part)).intValue());
		}
	}
}
