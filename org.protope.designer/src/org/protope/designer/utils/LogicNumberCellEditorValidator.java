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
package org.protope.designer.utils;

import org.eclipse.jface.viewers.ICellEditorValidator;
import org.protope.designer.i18n.ProtopeMessages;

public class LogicNumberCellEditorValidator
	implements ICellEditorValidator {

private static LogicNumberCellEditorValidator instance;

public static LogicNumberCellEditorValidator instance() {
	if (instance == null) 
		instance = new LogicNumberCellEditorValidator();
	return instance;
}

public String isValid(Object value) {
	try {
		new Integer((String)value);
		return null;
	} catch (NumberFormatException exc) {
		return ProtopeMessages.CellEditorValidator_NotANumberMessage;
	}
}

}