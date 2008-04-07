package org.protope.designer.webbuk.edit;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.protope.designer.base.edit.BaseElementEditPolicy;
import org.protope.designer.utils.NativeDropRequest;
import org.protope.designer.webbuk.model.WButton;
import org.protope.designer.webbuk.model.commands.WButtonSelectCommand;
import org.protope.designer.webbuk.model.commands.WButtonTextCommand;

public class WButtonEditPolicy extends BaseElementEditPolicy {

	public enum CommandType {
		CHANGE_TEXT, SELECT, DESELECT
	}

	public Command getCommand(Request request) {
		if (CommandType.SELECT.equals(request.getType()))
			return getSelectCommand(request, true);
		if (CommandType.DESELECT.equals(request.getType()))
			return getSelectCommand(request, false);
		if (CommandType.CHANGE_TEXT.equals(request.getType()))
			return getDropTextCommand((NativeDropRequest) request);
		if (NativeDropRequest.ID.equals(request.getType()))
			return getDropTextCommand((NativeDropRequest) request);
		return super.getCommand(request);
	}

	protected Command getSelectCommand(Request request, boolean selected) {
		WButton button = (WButton) getHost().getModel();
		WButtonSelectCommand command = new WButtonSelectCommand(
				button, selected);
		return command;
	}

	protected Command getDropTextCommand(NativeDropRequest request) {
		WButtonTextCommand command = new WButtonTextCommand((WButton) getHost()
				.getModel(), (String) request.getData());
		return command;
	}

	public EditPart getTargetEditPart(Request request) {
		if (NativeDropRequest.ID.equals(request.getType()))
			return getHost();
		return super.getTargetEditPart(request);
	}

}
