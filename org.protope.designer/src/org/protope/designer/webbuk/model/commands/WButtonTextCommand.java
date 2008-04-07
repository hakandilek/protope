package org.protope.designer.webbuk.model.commands;

import org.eclipse.gef.commands.Command;
import org.protope.designer.webbuk.model.WButton;

public class WButtonTextCommand extends Command {

	private String newText, oldText;
	private WButton button;

	public WButtonTextCommand(WButton l, String s) {
		button = l;
		if (s != null)
			newText = s;
		else
			newText = ""; //$NON-NLS-1$
	}

	public void execute() {
		oldText = button.getText();
		button.setText(newText);
	}

	public void undo() {
		button.setText(oldText);
	}

}
