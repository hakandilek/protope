package org.protope.designer.base.palette;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;

public class DiagramFlyoutPreferences implements FlyoutPreferences {

	private int location;
	private int state;
	private int width;


	public DiagramFlyoutPreferences() {
		super();
		setDockLocation(PositionConstants.WEST);
		setPaletteState(FlyoutPaletteComposite.STATE_PINNED_OPEN);
		setPaletteWidth(160);
	}

	public void setDockLocation(int location) {
		this.location = location;
	}

	public void setPaletteState(int state) {
		this.state = state;
	}

	public void setPaletteWidth(int width) {
		this.width = width;
	}

	public int getDockLocation() {
		return location;
	}

	public int getPaletteState() {
		return state;
	}

	public int getPaletteWidth() {
		return width;
	}

}
