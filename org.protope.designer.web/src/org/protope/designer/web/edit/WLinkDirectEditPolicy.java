package org.protope.designer.web.edit;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.protope.designer.web.figures.WLinkFigure;
import org.protope.designer.web.model.WLink;
import org.protope.designer.web.model.commands.WLinkTextCommand;

public class WLinkDirectEditPolicy extends DirectEditPolicy {

	/**
	 * @see DirectEditPolicy#getDirectEditCommand(DirectEditRequest)
	 */
	protected Command getDirectEditCommand(DirectEditRequest edit) {
		String labelText = (String) edit.getCellEditor().getValue();
		WLinkEditPart label = (WLinkEditPart) getHost();
		WLinkTextCommand command = new WLinkTextCommand((WLink) label.getModel(),
				labelText);
		return command;
	}

	/**
	 * @see DirectEditPolicy#showCurrentEditValue(DirectEditRequest)
	 */
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		((WLinkFigure) getHostFigure()).setText(value);
		// hack to prevent async layout from placing the cell editor twice.
		getHostFigure().getUpdateManager().performUpdate();
	}

}
