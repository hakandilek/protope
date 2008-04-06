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
import org.protope.designer.webbuk.model.WNote;

public class UILabelCommand extends Command {

	private String newName, oldName;
	private WNote label;

	public UILabelCommand(WNote l, String s) {
		label = l;
		if (s != null)
			newName = s;
		else
			newName = ""; //$NON-NLS-1$
	}

	public void execute() {
		oldName = label.getLabelContents();
		label.setLabelContents(newName);
	}

	public void undo() {
		label.setLabelContents(oldName);
	}

}
