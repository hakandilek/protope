package org.protope.designer.extension;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

public class DiagramDefinition {

	private List<PaletteDefinition> palettes = new ArrayList<PaletteDefinition>();
	private final IConfigurationElement element;

	public DiagramDefinition(IConfigurationElement element) {
		this.element = element;
	}

	/**
	 * @return the diagramID
	 */
	public String getDiagramID() {
		return element.getAttribute("id");
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return element.getAttribute("name");
	}

	/**
	 * @return the palettes
	 */
	public List<PaletteDefinition> getPalettes() {
		return palettes;
	}

	/**
	 * @param palette
	 *            the palette to add
	 */
	public void addPalette(PaletteDefinition palette) {
		this.palettes.add(palette);
	}

	/**
	 * @param palette
	 *            the palette to remove
	 */
	public void removePalette(PaletteDefinition palette) {
		this.palettes.remove(palette);
	}

}
