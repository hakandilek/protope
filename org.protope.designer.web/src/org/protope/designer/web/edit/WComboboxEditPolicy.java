package org.protope.designer.web.edit;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.protope.designer.base.edit.BaseElementEditPolicy;
import org.protope.designer.utils.NativeDropRequest;
import org.protope.designer.web.model.WCombobox;
import org.protope.designer.web.model.commands.WComboboxTextCommand;

public class WComboboxEditPolicy extends BaseElementEditPolicy {

	public enum CommandType {
		CHANGE_TEXT
	}

	public Command getCommand(Request request) {
		if (CommandType.CHANGE_TEXT.equals(request.getType()))
			return getDropTextCommand((NativeDropRequest) request);
		if (NativeDropRequest.ID.equals(request.getType()))
			return getDropTextCommand((NativeDropRequest) request);
		return super.getCommand(request);
	}

	protected Command getDropTextCommand(NativeDropRequest request) {
		WComboboxTextCommand command = new WComboboxTextCommand(
				(WCombobox) getHost().getModel(), (String) request.getData());
		return command;
	}

	public EditPart getTargetEditPart(Request request) {
		if (NativeDropRequest.ID.equals(request.getType()))
			return getHost();
		return super.getTargetEditPart(request);
	}

}
