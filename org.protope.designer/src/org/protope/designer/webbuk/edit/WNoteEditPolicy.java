package org.protope.designer.webbuk.edit;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.protope.designer.base.edit.BaseElementEditPolicy;
import org.protope.designer.utils.NativeDropRequest;
import org.protope.designer.webbuk.model.WNote;
import org.protope.designer.webbuk.model.commands.WNoteTextCommand;

public class WNoteEditPolicy extends BaseElementEditPolicy {

	public Command getCommand(Request request) {
		if (NativeDropRequest.ID.equals(request.getType()))
			return getDropTextCommand((NativeDropRequest) request);
		return super.getCommand(request);
	}

	protected Command getDropTextCommand(NativeDropRequest request) {
		WNoteTextCommand command = new WNoteTextCommand(
				(WNote) getHost().getModel(), (String) request.getData());
		return command;
	}

	public EditPart getTargetEditPart(Request request) {
		if (NativeDropRequest.ID.equals(request.getType()))
			return getHost();
		return super.getTargetEditPart(request);
	}

}
