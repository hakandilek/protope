package org.protope.designer.webbuk.action;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;
import org.protope.designer.i18n.ProtopeMessages;
import org.protope.designer.webbuk.edit.WButtonEditPolicy;
import org.protope.designer.webbuk.model.WButton;

public class WButtonSelectDeselectAction extends SelectionAction {

	private Request request;

	public WButtonSelectDeselectAction(IWorkbenchPart part, boolean select) {
		super(part);
		if (select) {
			request = new Request(WButtonEditPolicy.CommandType.SELECT);
			setText(ProtopeMessages.UIPlugin_Tool_CreationTool_WButton_Select_ActionLabel);
			setId(WButtonEditPolicy.CommandType.SELECT.name());
			setToolTipText(ProtopeMessages.UIPlugin_Tool_CreationTool_WButton_Select_ActionTooltip);
		} else {
			request = new Request(WButtonEditPolicy.CommandType.DESELECT);
			setText(ProtopeMessages.UIPlugin_Tool_CreationTool_WButton_Unselect_ActionLabel);
			setId(WButtonEditPolicy.CommandType.DESELECT.name());
			setToolTipText(ProtopeMessages.UIPlugin_Tool_CreationTool_WButton_Unselect_ActionTooltip);
		}
	}

	@Override
	protected boolean calculateEnabled() {
		return canPerformAction();
	}

	private boolean canPerformAction() {
		if (getSelectedObjects().isEmpty())
			return false;
		List<?> parts = getSelectedObjects();
		for (int i = 0; i < parts.size(); i++) {
			Object o = parts.get(i);
			if (!(o instanceof EditPart))
				return false;
			EditPart part = (EditPart) o;
			if (!(part.getModel() instanceof WButton))
				return false;
		}
		return true;
	}

	private Command getCommand() {
		List<?> editparts = getSelectedObjects();
		CompoundCommand cc = new CompoundCommand();
		cc.setDebugLabel("Select/Unselect Buttons");//$NON-NLS-1$
		for (int i = 0; i < editparts.size(); i++) {
			EditPart part = (EditPart) editparts.get(i);
			cc.add(part.getCommand(request));
		}
		return cc;
	}

	public void run() {
		execute(getCommand());
	}

}
