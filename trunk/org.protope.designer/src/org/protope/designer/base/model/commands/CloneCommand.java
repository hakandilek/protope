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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.protope.designer.base.model.BaseDiagram;
import org.protope.designer.base.model.UIElement;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.base.model.UIGuide;
import org.protope.designer.i18n.ProtopeMessages;
import org.protope.designer.webbuk.model.WButton;
import org.protope.designer.webbuk.model.WNote;

public class CloneCommand extends Command {

	private List<UIElementPart> parts, newTopLevelParts;
	private BaseDiagram parent;
	private Map<UIElementPart, Rectangle> bounds;
	private Map<UIElementPart, Integer> indices;
	private ChangeGuideCommand vGuideCommand, hGuideCommand;
	@SuppressWarnings("unused")
	private UIGuide hGuide, vGuide;
	@SuppressWarnings("unused")
	private int hAlignment, vAlignment;

	public CloneCommand() {
		super(ProtopeMessages.CloneCommand_Label);
		parts = new LinkedList<UIElementPart>();
	}

	public void addPart(UIElementPart part, Rectangle newBounds) {
		parts.add(part);
		if (bounds == null) {
			bounds = new HashMap<UIElementPart, Rectangle>();
		}
		bounds.put(part, newBounds);
	}

	public void addPart(UIElementPart part, int index) {
		parts.add(part);
		if (indices == null) {
			indices = new HashMap<UIElementPart, Integer>();
		}
		indices.put(part, new Integer(index));
	}

	protected void clonePart(UIElementPart oldPart, BaseDiagram newParent,
			Rectangle newBounds, int index) {
		UIElementPart newPart = null;

		if (oldPart instanceof WNote) {
			newPart = new WNote();
			((WNote) newPart).setLabelContents(((WNote) oldPart)
					.getLabelContents());
		}

		if (oldPart instanceof WButton) {
			newPart = new WButton();
			((WButton) newPart).setText((((WButton) oldPart)
					.getText()));
			((WButton) newPart).setSelected((((WButton) oldPart)
					.isSelected()));
		}

		if (oldPart instanceof BaseDiagram) {
			Iterator<UIElement> i = ((BaseDiagram) oldPart).getChildren().iterator();
			while (i.hasNext()) {
				// for children they will not need new bounds
				clonePart((UIElementPart) i.next(), (BaseDiagram) newPart, null, -1);
			}
		}

		if (index < 0) {
			newParent.addChild(newPart);
		} else {
			newParent.addChild(newPart, index);
		}

		newPart.setSize(oldPart.getSize());

		if (newBounds != null) {
			newPart.setLocation(newBounds.getTopLeft());
		} else {
			newPart.setLocation(oldPart.getLocation());
		}

		// keep track of the new parts so we can delete them in undo
		// keep track of the oldpart -> newpart map so that we can properly
		// attach
		// all connections.
		if (newParent == parent)
			newTopLevelParts.add(newPart);
	}

	public void execute() {
		newTopLevelParts = new LinkedList<UIElementPart>();

		Iterator<UIElementPart> i = parts.iterator();

		UIElementPart part = null;
		while (i.hasNext()) {
			part = (UIElementPart) i.next();
			if (bounds != null && bounds.containsKey(part)) {
				clonePart(part, parent, (Rectangle) bounds.get(part), -1);
			} else if (indices != null && indices.containsKey(part)) {
				clonePart(part, parent, null, ((Integer) indices.get(part))
						.intValue());
			} else {
				clonePart(part, parent, null, -1);
			}
		}

	}

	public void setParent(BaseDiagram parent) {
		this.parent = parent;
	}

	public void redo() {
		for (Iterator<UIElementPart> iter = newTopLevelParts.iterator(); iter.hasNext();)
			parent.addChild(iter.next());
		if (hGuideCommand != null)
			hGuideCommand.redo();
		if (vGuideCommand != null)
			vGuideCommand.redo();
	}

	public void setGuide(UIGuide guide, int alignment, boolean isHorizontal) {
		if (isHorizontal) {
			hGuide = guide;
			hAlignment = alignment;
		} else {
			vGuide = guide;
			vAlignment = alignment;
		}
	}

	public void undo() {
		if (hGuideCommand != null)
			hGuideCommand.undo();
		if (vGuideCommand != null)
			vGuideCommand.undo();
		for (Iterator<UIElementPart> iter = newTopLevelParts.iterator(); iter.hasNext();)
			parent.removeChild(iter.next());
	}

}
