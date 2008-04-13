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
package org.protope.designer.web.model.commands;

import org.eclipse.gef.commands.Command;
import org.protope.designer.web.model.WNote;

public class WNoteTextCommand extends Command {

	private String newText, oldText;
	private WNote note;

	public WNoteTextCommand(WNote l, String s) {
		note = l;
		if (s != null)
			newText = s;
		else
			newText = ""; //$NON-NLS-1$
	}

	public void execute() {
		oldText = note.getLabelContents();
		note.setLabelContents(newText);
	}

	public void undo() {
		note.setLabelContents(oldText);
	}

}
