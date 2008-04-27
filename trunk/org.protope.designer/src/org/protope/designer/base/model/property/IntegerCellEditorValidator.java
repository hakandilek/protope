package org.protope.designer.base.model.property;

import org.eclipse.jface.viewers.ICellEditorValidator;
import org.protope.designer.i18n.ProtopeMessages;

public class IntegerCellEditorValidator implements ICellEditorValidator {

	private static IntegerCellEditorValidator instance;

	public static IntegerCellEditorValidator instance() {
		if (instance == null)
			instance = new IntegerCellEditorValidator();
		return instance;
	}

	public String isValid(Object value) {
		try {
			new Integer((String) value);
			return null;
		} catch (NumberFormatException exc) {
			return ProtopeMessages.CellEditorValidator_NotANumberMessage;
		}
	}

}
