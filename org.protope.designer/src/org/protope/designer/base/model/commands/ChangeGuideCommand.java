/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
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
import org.protope.designer.base.model.UIGuide;

/**
 * @author Pratik Shah
 */
public class ChangeGuideCommand 
	extends Command 
{

private UIElementPart part;
private UIGuide oldGuide, newGuide;
private int oldAlign, newAlign;
private boolean horizontal;

public ChangeGuideCommand(UIElementPart part, boolean horizontalGuide) {
	super();
	this.part = part;
	horizontal = horizontalGuide;
}

protected void changeGuide(UIGuide oldGuide, UIGuide newGuide, int newAlignment) {
	if (oldGuide != null && oldGuide != newGuide) {
		oldGuide.detachPart(part);
	}
	// You need to re-attach the part even if the oldGuide and the newGuide are the same
	// because the alignment could have changed
	if (newGuide != null) {
		newGuide.attachPart(part, newAlignment);
	}
}

public void execute() {
	// Cache the old values
	oldGuide = horizontal ? part.getHorizontalGuide() : part.getVerticalGuide();		
	if (oldGuide != null)
		oldAlign = oldGuide.getAlignment(part);
	
	redo();
}

public void redo() {
	changeGuide(oldGuide, newGuide, newAlign);
}

public void setNewGuide(UIGuide guide, int alignment) {
	newGuide = guide;
	newAlign = alignment;
}

public void undo() {
	changeGuide(newGuide, oldGuide, oldAlign);
}

}
