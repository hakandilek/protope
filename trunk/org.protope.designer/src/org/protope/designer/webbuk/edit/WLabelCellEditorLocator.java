package org.protope.designer.webbuk.edit;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;
import org.protope.designer.webbuk.figures.WLabelFigure;

final public class WLabelCellEditorLocator implements CellEditorLocator {

	private WLabelFigure figure;

	public WLabelCellEditorLocator(WLabelFigure figure) {
		setFigure(figure);
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
	 * Returns the button figure.
	 */
	protected WLabelFigure getFigure() {
		return figure;
	}

	/**
	 * Sets the button figure.
	 * 
	 * @param figure
	 *            The figure to set
	 */
	protected void setFigure(WLabelFigure figure) {
		this.figure = figure;
	}

}