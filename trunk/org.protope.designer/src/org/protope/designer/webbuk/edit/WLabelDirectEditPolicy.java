package org.protope.designer.webbuk.edit;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.protope.designer.webbuk.figures.WLabelFigure;
import org.protope.designer.webbuk.model.WLabel;
import org.protope.designer.webbuk.model.commands.WLabelTextCommand;

public class WLabelDirectEditPolicy extends DirectEditPolicy {

	/**
	 * @see DirectEditPolicy#getDirectEditCommand(DirectEditRequest)
	 */
	protected Command getDirectEditCommand(DirectEditRequest edit) {
		String labelText = (String) edit.getCellEditor().getValue();
		WLabelEditPart label = (WLabelEditPart) getHost();
		WLabelTextCommand command = new WLabelTextCommand((WLabel) label.getModel(),
				labelText);
		return command;
	}

	/**
	 * @see DirectEditPolicy#showCurrentEditValue(DirectEditRequest)
	 */
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		((WLabelFigure) getHostFigure()).setText(value);
		// hack to prevent async layout from placing the cell editor twice.
		getHostFigure().getUpdateManager().performUpdate();

	}

}
