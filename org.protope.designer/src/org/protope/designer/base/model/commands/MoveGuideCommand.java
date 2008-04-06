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

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.base.model.UIGuide;
import org.protope.designer.i18n.ProtopeMessages;

/**
 * @author Pratik Shah
 */
public class MoveGuideCommand extends Command {

	private int pDelta;
	private UIGuide guide;

	public MoveGuideCommand(UIGuide guide, int positionDelta) {
		super(ProtopeMessages.MoveGuideCommand_Label);
		this.guide = guide;
		pDelta = positionDelta;
	}

	public void execute() {
		guide.setPosition(guide.getPosition() + pDelta);
		Iterator<UIElementPart> iter = guide.getParts().iterator();
		while (iter.hasNext()) {
			UIElementPart part = iter.next();
			Point location = part.getLocation().getCopy();
			if (guide.isHorizontal()) {
				location.y += pDelta;
			} else {
				location.x += pDelta;
			}
			part.setLocation(location);
		}
	}

	public void undo() {
		guide.setPosition(guide.getPosition() - pDelta);
		Iterator<UIElementPart> iter = guide.getParts().iterator();
		while (iter.hasNext()) {
			UIElementPart part = iter.next();
			Point location = part.getLocation().getCopy();
			if (guide.isHorizontal()) {
				location.y -= pDelta;
			} else {
				location.x -= pDelta;
			}
			part.setLocation(location);
		}
	}

}
