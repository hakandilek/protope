package org.protope.designer.web.edit;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.protope.designer.base.edit.BaseElementEditPolicy;
import org.protope.designer.utils.NativeDropRequest;
import org.protope.designer.web.model.WLabel;
import org.protope.designer.web.model.commands.WLabelTextCommand;

public class WLabelEditPolicy extends BaseElementEditPolicy {

	public Command getCommand(Request request) {
		if (NativeDropRequest.ID.equals(request.getType()))
			return getDropTextCommand((NativeDropRequest) request);
		return super.getCommand(request);
	}

	protected Command getDropTextCommand(NativeDropRequest request) {
		WLabelTextCommand command = new WLabelTextCommand((WLabel) getHost()
				.getModel(), (String) request.getData());
		return command;
	}

	public EditPart getTargetEditPart(Request request) {
		if (NativeDropRequest.ID.equals(request.getType()))
			return getHost();
		return super.getTargetEditPart(request);
	}

}
