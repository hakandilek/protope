package org.protope.designer.web.model.commands;

import org.eclipse.gef.commands.Command;
import org.protope.designer.web.model.WCombobox;

public class WComboboxTextCommand extends Command {

	private String newText, oldText;
	private WCombobox label;

	public WComboboxTextCommand(WCombobox l, String s) {
		label = l;
		if (s != null)
			newText = s;
		else
			newText = ""; //$NON-NLS-1$
	}

	public void execute() {
		oldText = label.getText();
		label.setText(newText);
	}

	public void undo() {
		label.setText(oldText);
	}

}
