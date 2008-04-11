package org.protope.designer.extension;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gef.EditPart;
import org.protope.designer.base.model.UIElement;

public class ToolDefinition {

	private final IConfigurationElement element;

	public ToolDefinition(IConfigurationElement element) {
		this.element = element;
	}

	public String getLabel() {
		return element.getAttribute("label");
	}

	public String getDescription() {
		String d = element.getAttribute("description");
		if (d == null) d = getLabel();
		return d;
	}

	public String getSmallIconPath() {
		return element.getAttribute("smallIcon");
	}

	public String getMediumIconPath() {
		return element.getAttribute("mediumIcon");
	}

	public UIElement getModel() {
		UIElement model = null; 
		try {
			model = (UIElement) element.createExecutableExtension("model");
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return model;
	}

	public EditPart getEditor() {
		EditPart editor = null; 
		try {
			editor = (EditPart) element.createExecutableExtension("editor");
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return editor;
	}

}
