package org.protope.designer.tool;

import org.eclipse.gef.requests.CreationFactory;
import org.protope.designer.extension.ToolDefinition;

public class ToolDefinitionFactory implements CreationFactory {

	private final ToolDefinition toolDefinition;

	public ToolDefinitionFactory(ToolDefinition toolDefinition) {
		this.toolDefinition = toolDefinition;
	}

	@Override
	public Object getNewObject() {
		return toolDefinition.getModel();
	}

	@Override
	public Object getObjectType() {
		return toolDefinition.getModel().getClass();
	}

}
