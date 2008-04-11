package org.protope.designer.extension;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PaletteRegistry {

	private static final PaletteRegistry INSTANCE = new PaletteRegistry();

	private Map<String, PaletteDefinition> palettes = new HashMap<String, PaletteDefinition>();

	private PaletteRegistry() {
		super();
		initialize();
	}

	private void initialize() {
		PaletteRegistryReader reader = new PaletteRegistryReader(this);
		reader.readRegistry("org.protope.designer", "palette");
	}

	/**
	 * @return the INSTANCE
	 */
	public static PaletteRegistry getINSTANCE() {
		return INSTANCE;
	}

	/**
	 * @return the palettes
	 */
	public Collection<PaletteDefinition> getPalettes() {
		return palettes.values();
	}

	/**
	 * @param palette
	 *            the palette to add
	 */
	public void addPalette(PaletteDefinition palette) {
		if (palette != null)
			this.palettes.put(palette.getPaletteID(), palette);
	}

	/**
	 * @param palette
	 *            the palette to remove
	 */
	public void removePalette(PaletteDefinition palette) {
		if (palette != null)
			this.palettes.remove(palette.getPaletteID());
	}

	public PaletteDefinition getPalette(String paletteID) {
		return palettes.get(paletteID);
	}

}
