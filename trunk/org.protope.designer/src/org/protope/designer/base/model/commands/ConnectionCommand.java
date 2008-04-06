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
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.i18n.ProtopeMessages;

public class ConnectionCommand extends Command {

	protected UIElementPart oldSource;
	protected String oldSourceTerminal;
	protected UIElementPart oldTarget;
	protected String oldTargetTerminal;
	protected UIElementPart source;
	protected String sourceTerminal;
	protected UIElementPart target;
	protected String targetTerminal;

	public ConnectionCommand() {
		super(ProtopeMessages.ConnectionCommand_Label);
	}

	public boolean canExecute() {
		return true;
	}

	public void execute() {
	}

	public String getLabel() {
		return ProtopeMessages.ConnectionCommand_Description;
	}

	public UIElementPart getSource() {
		return source;
	}

	public java.lang.String getSourceTerminal() {
		return sourceTerminal;
	}

	public UIElementPart getTarget() {
		return target;
	}

	public String getTargetTerminal() {
		return targetTerminal;
	}

	public void redo() {
		execute();
	}

	public void setSource(UIElementPart newSource) {
		source = newSource;
	}

	public void setSourceTerminal(String newSourceTerminal) {
		sourceTerminal = newSourceTerminal;
	}

	public void setTarget(UIElementPart newTarget) {
		target = newTarget;
	}

	public void setTargetTerminal(String newTargetTerminal) {
		targetTerminal = newTargetTerminal;
	}

	public void undo() {
	}

}
