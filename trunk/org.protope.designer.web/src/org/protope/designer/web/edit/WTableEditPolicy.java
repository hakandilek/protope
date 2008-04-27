package org.protope.designer.web.edit;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.protope.designer.base.edit.BaseElementEditPolicy;
import org.protope.designer.utils.NativeDropRequest;
import org.protope.designer.web.model.WTable;
import org.protope.designer.web.model.commands.WTableColumnCountCommand;

public class WTableEditPolicy extends BaseElementEditPolicy {

	public enum CommandType {
		COLUMNT_COUNT, ROW_COUNT, COLUMN_NAME
	}

	public Command getCommand(Request request) {
		if (CommandType.COLUMNT_COUNT.equals(request.getType()))
			return getColumnCountCommand(request);
		return super.getCommand(request);
	}

	protected Command getColumnCountCommand(Request request) {
		final Integer count = (Integer) request.getExtendedData().get("count");
		final WTable model = (WTable) getHost().getModel();
		WTableColumnCountCommand command = new WTableColumnCountCommand(model,
				count);
		return command;
	}

	public EditPart getTargetEditPart(Request request) {
		if (NativeDropRequest.ID.equals(request.getType()))
			return getHost();
		return super.getTargetEditPart(request);
	}

}
