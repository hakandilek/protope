package org.protope.designer.web.edit;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.protope.designer.base.edit.BaseEditPart;
import org.protope.designer.web.figures.WTableFigure;
import org.protope.designer.web.model.WTable;

public class WTableEditPart extends BaseEditPart {

	protected AccessibleEditPart createAccessible() {
		return null;
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new WTableEditPolicy());
	}

	protected IFigure createFigure() {
		WTableFigure figure = new WTableFigure();
		final WTable model = getTable();
		figure.setColumnCount(model.getColumnCount());
		figure.setRowCount(model.getRowCount());
		return figure;
	}

	private WTable getTable() {
		return (WTable) getModel();
	}

	private void performDirectEdit() {
		new WTableEditManager(this, new WTableCellEditorLocator(
				(WTableFigure) getFigure())).show();
	}

	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT)
			performDirectEdit();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("rowCount"))//$NON-NLS-1$
			refreshVisuals();
		else if (evt.getPropertyName().equalsIgnoreCase("columnCount"))//$NON-NLS-1$
			refreshVisuals();
		else
			super.propertyChange(evt);
	}

	protected void refreshVisuals() {
		final WTableFigure fig = (WTableFigure) getFigure();
		final WTable model = getTable();
		fig.setColumnCount(model.getColumnCount());
		fig.setRowCount(model.getRowCount());
		super.refreshVisuals();
	}

}
