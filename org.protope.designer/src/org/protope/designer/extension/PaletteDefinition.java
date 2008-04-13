package org.protope.designer.extension;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.gef.EditPart;
import org.protope.designer.base.model.UIElement;

public class PaletteDefinition {

	private final IConfigurationElement element;

	private List<ToolDefinition> tools = new ArrayList<ToolDefinition>();

	public PaletteDefinition(IConfigurationElement element) {
		this.element = element;
	}

	/**
	 * @return the tools
	 */
	public List<ToolDefinition> getTools() {
		return tools;
	}

	/**
	 * @param tool
	 *            the tool to add
	 */
	public void addTool(ToolDefinition tool) {
		this.tools.add(tool);
	}

	/**
	 * @param tool
	 *            the tool to remove
	 */
	public void removeTool(ToolDefinition tool) {
		this.tools.remove(tool);
	}

	/**
	 * @return the paletteID
	 */
	public String getPaletteID() {
		return element.getAttribute("id");
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return element.getAttribute("name");
	}

	public String getDrawerIconPath() {
		return element.getAttribute("drawerIconPath");
	}

	public String getDrawerLabel() {
		return element.getAttribute("drawerLabel");
	}

	public ToolDefinition getItemFor(UIElement model) {
		// TODO:use a cache
		if (model == null)
			return null;
		Class<? extends UIElement> class1 = model.getClass();
		for (ToolDefinition tool : tools) {
			UIElement toolModel = tool.getModel();
			if (class1.equals(toolModel.getClass())) {
				return tool;
			}
		}
		return null;
	}

	public String getDeclaringPluginID() {
		IExtension extension = element.getDeclaringExtension();
		return extension.getNamespaceIdentifier();
	}

	public ToolDefinition getItemFor(EditPart editor) {
		// TODO:use a cache
		if (editor == null)
			return null;
		Class<? extends EditPart> class1 = editor.getClass();
		for (ToolDefinition tool : tools) {
			EditPart toolEditor = tool.getEditor();
			if (class1.equals(toolEditor.getClass())) {
				return tool;
			}
		}
		return null;
	}

}
