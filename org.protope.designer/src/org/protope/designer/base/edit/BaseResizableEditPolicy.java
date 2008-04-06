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
package org.protope.designer.base.edit;

import java.util.Iterator;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.protope.designer.base.figures.ProtopeColorConstants;

/**
 * 
 */
public class BaseResizableEditPolicy extends ResizableEditPolicy {

	/**
	 * Creates the figure used for feedback.
	 * 
	 * @return the new feedback figure
	 */
	protected IFigure createDragSourceFeedbackFigure() {
		IFigure figure = createFigure((GraphicalEditPart) getHost(), null);

		figure.setBounds(getInitialFeedbackBounds());
		addFeedback(figure);
		return figure;
	}

	protected IFigure createFigure(GraphicalEditPart part, IFigure parent) {
		IFigure child = getCustomFeedbackFigure(part.getModel());

		if (parent != null)
			parent.add(child);

		Rectangle childBounds = part.getFigure().getBounds().getCopy();

		IFigure walker = part.getFigure().getParent();

		while (walker != ((GraphicalEditPart) part.getParent()).getFigure()) {
			walker.translateToParent(childBounds);
			walker = walker.getParent();
		}

		child.setBounds(childBounds);

		@SuppressWarnings("unchecked")
		Iterator i = part.getChildren().iterator();

		while (i.hasNext())
			createFigure((GraphicalEditPart) i.next(), child);

		return child;
	}

	protected IFigure getCustomFeedbackFigure(Object modelPart) {
		IFigure figure;

		figure = new RectangleFigure();
		((RectangleFigure) figure).setXOR(true);
		((RectangleFigure) figure).setFill(true);
		figure.setBackgroundColor(ProtopeColorConstants.GHOST_FILL_COLOR);
		figure.setForegroundColor(ColorConstants.white);

		return figure;
	}

	/**
	 * Returns the layer used for displaying feedback.
	 * 
	 * @return the feedback layer
	 */
	protected IFigure getFeedbackLayer() {
		return getLayer(LayerConstants.SCALED_FEEDBACK_LAYER);
	}

	/**
	 * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#getInitialFeedbackBounds()
	 */
	protected Rectangle getInitialFeedbackBounds() {
		return getHostFigure().getBounds();
	}

}
