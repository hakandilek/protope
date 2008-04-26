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
import org.protope.designer.web.figures.WCheckboxFigure;
import org.protope.designer.web.i18n.WebPaletteMessages;
import org.protope.designer.web.model.WCheckbox;

public class WCheckboxEditPart extends BaseEditPart {

	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getValue(AccessibleControlEvent e) {
				e.result = getCheckbox().getText();
			}

			public void getName(AccessibleEvent e) {
				e.result = WebPaletteMessages.WebPalette_Tool_WCheckbox;
			}
		};
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new WCheckboxDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new WCheckboxEditPolicy());
	}

	protected IFigure createFigure() {
		WCheckboxFigure figure = new WCheckboxFigure(getCheckbox().getText());
		return figure;
	}

	private WCheckbox getCheckbox() {
		return (WCheckbox) getModel();
	}

	private void performDirectEdit() {
		new WCheckboxEditManager(this, new WCheckboxCellEditorLocator(
				(WCheckboxFigure) getFigure())).show();
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
		((WCheckboxFigure) getFigure()).setText(getCheckbox().getText());
		super.refreshVisuals();
	}

}
