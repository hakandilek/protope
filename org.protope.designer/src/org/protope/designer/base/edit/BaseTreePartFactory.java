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

public class BaseTreePartFactory
	implements EditPartFactory
{

public EditPart createEditPart(EditPart context, Object model) {
	if (model instanceof BaseDiagram)
		return new BaseContainerTreeEditPart(model);
	return new BaseTreeEditPart(model);
}

}
