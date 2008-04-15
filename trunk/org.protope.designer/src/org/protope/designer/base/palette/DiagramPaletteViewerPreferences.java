package org.protope.designer.base.palette;

import org.eclipse.gef.ui.palette.DefaultPaletteViewerPreferences;

public class DiagramPaletteViewerPreferences extends
		DefaultPaletteViewerPreferences {

	private static final int[] LAYOUT_MODES = new int[] { LAYOUT_DETAILS,
			LAYOUT_ICONS };

	public DiagramPaletteViewerPreferences() {
		super();
		setSupportedLayoutModes(LAYOUT_MODES);
		setAutoCollapseSetting(COLLAPSE_NEVER);
		setLayoutSetting(LAYOUT_DETAILS);
		setUseLargeIcons(LAYOUT_DETAILS, true);
	}

}
