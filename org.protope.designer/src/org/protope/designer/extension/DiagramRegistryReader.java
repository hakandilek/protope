package org.protope.designer.extension;

import org.eclipse.core.runtime.IConfigurationElement;

public class DiagramRegistryReader extends AbstractRegistryReader {

	private final DiagramRegistry registry;

	public DiagramRegistryReader(DiagramRegistry diagramRegistry) {
		this.registry = diagramRegistry;
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
		if ("diagram".equals(name)) {
			PaletteRegistry pr = PaletteRegistry.getINSTANCE();
			DiagramDefinition diagram = new DiagramDefinition(element);
			IConfigurationElement[] associations = element
					.getChildren("paletteAssociation");
			
			for (IConfigurationElement paletteElement : associations) {
				String paletteID = paletteElement.getAttribute("paletteID");
				if (paletteID != null) {
					PaletteDefinition palette = pr.getPalette(paletteID);
					if (palette != null) {
						diagram.addPalette(palette);
					}
				}
			}

			registry.addDiagram(diagram);
			return true;
		}
		return false;
	}

}
