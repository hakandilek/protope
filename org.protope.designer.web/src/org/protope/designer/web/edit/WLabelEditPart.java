package org.protope.designer.web.edit;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.protope.designer.base.edit.BaseEditPart;
import org.protope.designer.web.figures.WLabelFigure;
import org.protope.designer.web.i18n.WebPaletteMessages;
import org.protope.designer.web.model.WLabel;

public class WLabelEditPart extends BaseEditPart {

	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getValue(AccessibleControlEvent e) {
				e.result = getLabel().getText();
			}

			public void getName(AccessibleEvent e) {
				e.result = WebPaletteMessages.WebPalette_Tool_WLabel;
			}
		};
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new WLabelDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new WLabelEditPolicy());
	}

	protected IFigure createFigure() {
		WLabelFigure figure = new WLabelFigure();
		figure.setText(getLabel().getText());
		return figure;
	}

	private WLabel getLabel() {
		return (WLabel) getModel();
	}

	private void performDirectEdit() {
		new WLabelEditManager(this, new WLabelCellEditorLocator(
				(WLabelFigure) getFigure())).show();
	}

	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT)
			performDirectEdit();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("text"))//$NON-NLS-1$
			refreshVisuals();
		else
			super.propertyChange(evt);
	}

	protected void refreshVisuals() {
		((WLabelFigure) getFigure()).setText(getLabel().getText());
		super.refreshVisuals();
	}

}
