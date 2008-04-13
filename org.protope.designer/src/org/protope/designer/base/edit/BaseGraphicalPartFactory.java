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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.protope.designer.base.model.BaseDiagram;
import org.protope.designer.base.model.UIElement;
import org.protope.designer.tool.ToolHandler;

public class BaseGraphicalPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		ToolHandler handler = new ToolHandler();
		EditPart child = handler.getEditorFor((UIElement) model);

		if (child == null && model instanceof BaseDiagram)
			child = new BaseDiagramEditPart();

		if (child != null)
			child.setModel(model);
		return child;
	}

}
