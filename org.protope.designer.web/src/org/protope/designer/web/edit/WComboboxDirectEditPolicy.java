package org.protope.designer.web.edit;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.protope.designer.web.figures.WComboboxFigure;
import org.protope.designer.web.model.WCombobox;
import org.protope.designer.web.model.commands.WComboboxTextCommand;

public class WComboboxDirectEditPolicy extends DirectEditPolicy {

	/**
	 * @see DirectEditPolicy#getDirectEditCommand(DirectEditRequest)
	 */
	protected Command getDirectEditCommand(DirectEditRequest edit) {
		String labelText = (String) edit.getCellEditor().getValue();
		WComboboxEditPart label = (WComboboxEditPart) getHost();
		WComboboxTextCommand command = new WComboboxTextCommand((WCombobox) label.getModel(),
				labelText);
		return command;
	}

	/**
	 * @see DirectEditPolicy#showCurrentEditValue(DirectEditRequest)
	 */
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		((WComboboxFigure) getHostFigure()).setText(value);
		// hack to prevent async layout from placing the cell editor twice.
		getHostFigure().getUpdateManager().performUpdate();

	}

}
