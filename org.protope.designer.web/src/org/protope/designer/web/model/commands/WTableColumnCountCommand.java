package org.protope.designer.web.model.commands;

import org.eclipse.gef.commands.Command;
import org.protope.designer.web.model.WTable;

public class WTableColumnCountCommand extends Command {

	private Integer newCount, oldCount;
	private WTable table;

	public WTableColumnCountCommand(WTable table, Integer count) {
		this.table = table;
		if (count != null)
			newCount = count;
		else
			newCount = 1;
	}

	public void execute() {
		oldCount = table.getColumnCount();
		table.setColumnCount(newCount);
	}

	public void undo() {
		table.setColumnCount(oldCount);
	}

}
