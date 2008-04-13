package org.protope.designer.web.edit;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;
import org.protope.designer.web.figures.WNoteFigure;

final public class WNoteCellEditorLocator implements CellEditorLocator {

	private WNoteFigure figure;

	public WNoteCellEditorLocator(WNoteFigure figure) {
		setLabel(figure);
	}

	public void relocate(CellEditor celleditor) {
		Text text = (Text) celleditor.getControl();
		Rectangle rect = figure.getClientArea();
		figure.translateToAbsolute(rect);
		org.eclipse.swt.graphics.Rectangle trim = text.computeTrim(0, 0, 0, 0);
		rect.translate(trim.x, trim.y);
		rect.width += trim.width;
		rect.height += trim.height;
		text.setBounds(rect.x, rect.y, rect.width, rect.height);
	}

	/**
	 * Returns the stickyNote figure.
	 */
	protected WNoteFigure getLabel() {
		return figure;
	}

	/**
	 * Sets the Sticky note figure.
	 * 
	 * @param figure
	 *            The figure to set
	 */
	protected void setLabel(WNoteFigure figure) {
		this.figure = figure;
	}

}