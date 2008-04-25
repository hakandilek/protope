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
package org.protope.designer.base.model.property;

import org.eclipse.jface.viewers.ICellEditorValidator;
import org.protope.designer.i18n.ProtopeMessages;

public class StringCellEditorValidator implements ICellEditorValidator {

	private static StringCellEditorValidator instance;

	public static StringCellEditorValidator instance() {
		if (instance == null)
			instance = new StringCellEditorValidator();
		return instance;
	}

	public String isValid(Object value) {
		try {
			@SuppressWarnings("unused")
			String s = (String) value;
			return null;
		} catch (Exception exc) {
			return ProtopeMessages.CellEditorValidator_NotAStringMessage;
		}
	}

}
