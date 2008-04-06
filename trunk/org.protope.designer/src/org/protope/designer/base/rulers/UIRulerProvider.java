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
package org.protope.designer.base.rulers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.rulers.RulerChangeListener;
import org.eclipse.gef.rulers.RulerProvider;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.base.model.UIGuide;
import org.protope.designer.base.model.UIRuler;
import org.protope.designer.base.model.commands.CreateGuideCommand;
import org.protope.designer.base.model.commands.DeleteGuideCommand;
import org.protope.designer.base.model.commands.MoveGuideCommand;

/**
 * @author Pratik Shah
 */
public class UIRulerProvider extends RulerProvider {

	private UIRuler ruler;
	private PropertyChangeListener rulerListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(UIRuler.PROPERTY_CHILDREN)) {
				UIGuide guide = (UIGuide) evt.getNewValue();
				if (getGuides().contains(guide)) {
					guide.addPropertyChangeListener(guideListener);
				} else {
					guide.removePropertyChangeListener(guideListener);
				}
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i))
							.notifyGuideReparented(guide);
				}
			} else {
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i))
							.notifyUnitsChanged(ruler.getUnit());
				}
			}
		}
	};
	private PropertyChangeListener guideListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(UIGuide.PROPERTY_CHILDREN)) {
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i))
							.notifyPartAttachmentChanged(evt.getNewValue(), evt
									.getSource());
				}
			} else {
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i))
							.notifyGuideMoved(evt.getSource());
				}
			}
		}
	};

	public UIRulerProvider(UIRuler ruler) {
		this.ruler = ruler;
		this.ruler.addPropertyChangeListener(rulerListener);
		List<UIGuide> guides = getGuides();
		for (int i = 0; i < guides.size(); i++) {
			(guides.get(i)).addPropertyChangeListener(guideListener);
		}
	}

	public List<UIElementPart> getAttachedModelObjects(Object guide) {
		UIGuide g = (UIGuide) guide;
		return new ArrayList<UIElementPart>(g.getParts());
	}

	public Command getCreateGuideCommand(int position) {
		return new CreateGuideCommand(ruler, position);
	}

	public Command getDeleteGuideCommand(Object guide) {
		return new DeleteGuideCommand((UIGuide) guide, ruler);
	}

	public Command getMoveGuideCommand(Object guide, int pDelta) {
		return new MoveGuideCommand((UIGuide) guide, pDelta);
	}

	public int[] getGuidePositions() {
		List<UIGuide> guides = getGuides();
		int[] result = new int[guides.size()];
		for (int i = 0; i < guides.size(); i++) {
			result[i] = (guides.get(i)).getPosition();
		}
		return result;
	}

	public Object getRuler() {
		return ruler;
	}

	public int getUnit() {
		return ruler.getUnit();
	}

	public void setUnit(int newUnit) {
		ruler.setUnit(newUnit);
	}

	public int getGuidePosition(Object guide) {
		return ((UIGuide) guide).getPosition();
	}

	public List<UIGuide> getGuides() {
		return ruler.getGuides();
	}

}
