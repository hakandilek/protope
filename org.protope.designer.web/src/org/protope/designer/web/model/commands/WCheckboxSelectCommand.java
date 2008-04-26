package org.protope.designer.web.model.commands;

import org.eclipse.gef.commands.Command;
import org.protope.designer.web.model.WCheckbox;

public class WCheckboxSelectCommand extends Command {

	private boolean newSelected, oldSelected;
	private WCheckbox button;

	public WCheckboxSelectCommand(WCheckbox l, boolean selected) {
		button = l;
		newSelected = selected;
	}

	public void execute() {
		oldSelected = button.isSelected();
		button.setSelected(newSelected);
	}

	public void undo() {
		button.setSelected(oldSelected);
	}

}
