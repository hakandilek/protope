package org.protope.designer.extension;

import org.eclipse.core.runtime.IConfigurationElement;

public class PaletteRegistryReader extends AbstractRegistryReader {

	private final PaletteRegistry registry;

	public PaletteRegistryReader(PaletteRegistry paletteRegistry) {
		this.registry = paletteRegistry;
	}

	/**
	 * Reads a single configuration element
	 * 
	 * @param element
	 *            the element
	 * @return true if element is successfully read
	 * @throws ConfigurationException
	 *             on any configuration error
	 */
	protected boolean readElement(IConfigurationElement element) {
		String name = element.getName();
		if ("palette".equals(name)) {
			PaletteDefinition palette = new PaletteDefinition(element);
			IConfigurationElement[] toolElements = element.getChildren("tool");

			for (IConfigurationElement toolElement : toolElements) {
				ToolDefinition tool = new ToolDefinition(toolElement);
				palette.addTool(tool);
			}

			registry.addPalette(palette);
			return true;
		}
		return false;
	}

}
