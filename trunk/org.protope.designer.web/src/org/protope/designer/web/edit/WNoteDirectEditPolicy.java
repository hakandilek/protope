package org.protope.designer.web.edit;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.protope.designer.web.figures.WNoteFigure;
import org.protope.designer.web.model.WNote;
import org.protope.designer.web.model.commands.WNoteTextCommand;

public class WNoteDirectEditPolicy extends DirectEditPolicy {

	/**
	 * @see DirectEditPolicy#getDirectEditCommand(DirectEditRequest)
	 */
	protected Command getDirectEditCommand(DirectEditRequest edit) {
		String labelText = (String) edit.getCellEditor().getValue();
		WNoteEditPart label = (WNoteEditPart) getHost();
		WNoteTextCommand command = new WNoteTextCommand((WNote) label.getModel(),
				labelText);
		return command;
	}

	/**
	 * @see DirectEditPolicy#showCurrentEditValue(DirectEditRequest)
	 */
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		((WNoteFigure) getHostFigure()).setText(value);
		// hack to prevent async layout from placing the cell editor twice.
		getHostFigure().getUpdateManager().performUpdate();

	}

}
