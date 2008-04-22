package org.protope.designer.web.model.commands;

import org.eclipse.gef.commands.Command;
import org.protope.designer.web.model.WLink;

public class WLinkTextCommand extends Command {

	private String newText, oldText;
	private WLink label;

	public WLinkTextCommand(WLink l, String s) {
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
