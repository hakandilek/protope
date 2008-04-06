package org.protope.designer.base.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.swt.graphics.Image;

public class UIDiagram extends UIElementPart {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;

	private static Image UI_ICON = createImage(UIDiagram.class,
			"icons/ui16.gif"); //$NON-NLS-1$

	public static String ID_ROUTER = "router";	//$NON-NLS-1$

	private static int count;

	protected List<UIElement> children = new ArrayList<UIElement>();

	protected UIRuler leftRuler, topRuler;

	private boolean rulerVisibility = false;
	private boolean snapToGeometry = false;
	private boolean gridEnabled = false;
	private double zoom = 1.0;

	public void addChild(UIElementPart child) {
		addChild(child, -1);
	}

	public void addChild(UIElement child, int index) {
		if (index >= 0)
			children.add(index, child);
		else
			children.add(child);
		fireChildAdded(CHILDREN, child, new Integer(index));
	}

	public void removeChild(UIElement child){
		children.remove(child);
		fireChildRemoved(CHILDREN, child);
	}

	public List<UIElement> getChildren() {
		return children;
	}

	@Override
	public Image getIconImage() {
		return UI_ICON;
	}

	@Override
	public String getNewID() {
		return Integer.toString(count++);
	}

	public UIRuler getRuler(int orientation) {
		UIRuler result = null;
		switch (orientation) {
			case PositionConstants.NORTH :
				result = topRuler;
				break;
			case PositionConstants.WEST :
				result = leftRuler;
				break;
		}
		return result;
	}

	public boolean isSnapToGeometryEnabled() {
		return snapToGeometry;
	}

	public void setSnapToGeometry(boolean snapToGeometry) {
		this.snapToGeometry = snapToGeometry;
	}

	public boolean getRulerVisibility() {
		return rulerVisibility;
	}

	public void setRulerVisibility(boolean rulerVisibility) {
		this.rulerVisibility = rulerVisibility;
	}

	public boolean isGridEnabled() {
		return gridEnabled;
	}

	public void setGridEnabled(boolean gridEnabled) {
		this.gridEnabled = gridEnabled;
	}

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

}
