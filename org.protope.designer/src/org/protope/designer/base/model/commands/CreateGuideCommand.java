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

import org.eclipse.gef.commands.Command;
import org.protope.designer.base.model.UIGuide;
import org.protope.designer.base.model.UIRuler;
import org.protope.designer.i18n.ProtopeMessages;

/**
 * @author Pratik Shah
 */
public class CreateGuideCommand extends Command {

	private UIGuide guide;
	private UIRuler parent;
	private int position;

	public CreateGuideCommand(UIRuler parent, int position) {
		super(ProtopeMessages.CreateGuideCommand_Label);
		this.parent = parent;
		this.position = position;
	}

	public boolean canUndo() {
		return true;
	}

	public void execute() {
		if (guide == null)
			guide = new UIGuide(!parent.isHorizontal());
		guide.setPosition(position);
		parent.addGuide(guide);
	}

	public void undo() {
		parent.removeGuide(guide);
	}

}
