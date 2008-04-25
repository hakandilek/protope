package org.protope.designer.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class GEFUtils {

	/**
	 * Helper method to decide whether <code>selectedEditParts</code>
	 * represent the whole diagram or not. Note: "whole diagram" means here that
	 * the diagram edit part is selected. When all edit parts in the diagram
	 * (except the diagram edit part) are selected, this returns false.
	 * 
	 * @param selectedEditParts
	 *            list of selected edit parts
	 * @return whether <code>selectedEditParts</code> represent the whole
	 *         diagram or not
	 */
	public static boolean isWholeDiagramSelected(
			List<GraphicalEditPart> selectedEditParts) {
		boolean wholeDiagramIsSelected = false;
		if (selectedEditParts.size() == 1) {
			GraphicalEditPart editPart = selectedEditParts.get(0);
			if (editPart == editPart.getRoot().getViewer().getContents()) {
				wholeDiagramIsSelected = true;
			}
		}
		return wholeDiagramIsSelected;
	}

	public static Rectangle getBounds(IFigure figure) {
		Rectangle bounds;
		if (figure instanceof FreeformFigure) {
			FreeformFigure freeformDiagramFigure = (FreeformFigure) figure;
			bounds = freeformDiagramFigure.getFreeformExtent();
		} else {
			bounds = figure.getBounds();
		}
		return bounds;
	}

	public static Rectangle getBounds(Collection<IFigure> figures) {
		Rectangle bounds = null;
		if (figures.size() == 1) {
			final IFigure nextFigure = figures.iterator().next();
			bounds = getBounds(nextFigure);
		} else {
			FreeformFigure freeformHelperFigure = new FreeformLayer();
			for (IFigure figure : figures) {
				IFigure newFigure = new Figure();
				final Rectangle b = getBounds(figure);
				newFigure.setBounds(b);
				freeformHelperFigure.add(newFigure);
			}
			final Rectangle b = getBounds(freeformHelperFigure);
			bounds = b;
		}
		return bounds;
	}

	/**
	 * Helper method to return the figure of the diagram that is viewed by
	 * <code>viewer</code> and that should be exported.
	 * 
	 * @param viewer
	 *            the viewer showing the diagram to be exported
	 * @return the figure of the diagram that is viewed by <code>viewer</code>
	 *         and that should be exported
	 */
	public static IFigure getExportFigure(GraphicalViewer viewer) {
		@SuppressWarnings("unchecked")
		final Map registry = viewer.getEditPartRegistry();
		LayerManager layers = (LayerManager) registry.get(LayerManager.ID);
		IFigure diagramFigure = layers
				.getLayer(LayerConstants.PRINTABLE_LAYERS);
		return diagramFigure;
	}

	public static Image createImage(List<GraphicalEditPart> selectedEditParts,
			Dimension maxSize, boolean selectionOnly, boolean useAntialiasing) {
		List<IFigure> previewedFigures = new ArrayList<IFigure>();
		Rectangle selectionBounds = null;

		if (!selectionOnly) {
			GraphicalEditPart diagramEditPart = selectedEditParts.get(0);
			IFigure rootFigure = GEFUtils
					.getExportFigure((GraphicalViewer) diagramEditPart
							.getRoot().getViewer());
			previewedFigures.add(rootFigure);
			selectionBounds = GEFUtils.getBounds(rootFigure);
		} else {
			for (GraphicalEditPart editPart : selectedEditParts) {
				previewedFigures.add(editPart.getFigure());
			}
			selectionBounds = GEFUtils.getBounds(previewedFigures);
		}

		Dimension imageSize = selectionBounds.getSize();
		if (maxSize != null)
			if (selectionBounds.width > maxSize.width
					|| selectionBounds.height > maxSize.height) {
				imageSize = maxSize;
				System.err
						.println("Downscaling turned off due to Cairo crashes."
								+ " That means the preview image does not show the whole image that will be exported.");
			}

		// FIXME: there is an error further up that allows the wizard to be
		// called with an empty selection
		Image image = new Image(Display.getDefault(), imageSize.width,
				imageSize.height);

		for (IFigure figure : previewedFigures) {
			GC gc = new GC(image);
			gc.setAntialias(useAntialiasing ? SWT.ON : SWT.OFF);
			gc.setTextAntialias(useAntialiasing ? SWT.ON : SWT.OFF);
			Graphics graphics = new SWTGraphics(gc);
			graphics.translate(selectionBounds.getLocation().negate());
			// graphics.scale(scale); Cairo crashes
			figure.paint(graphics);
			graphics.dispose();
			gc.dispose();
		}

		return image;
	}

}
