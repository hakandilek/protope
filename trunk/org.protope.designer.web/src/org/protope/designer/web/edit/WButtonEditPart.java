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
import org.protope.designer.web.figures.WButtonFigure;
import org.protope.designer.web.i18n.WebPaletteMessages;
import org.protope.designer.web.model.WButton;

public class WButtonEditPart extends BaseEditPart {

	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getValue(AccessibleControlEvent e) {
				e.result = getButton().getText();
			}

			public void getName(AccessibleEvent e) {
				e.result = WebPaletteMessages.WebPalette_Tool_WButton;
			}
		};
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new WButtonDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new WButtonEditPolicy());
	}

	protected IFigure createFigure() {
		WButtonFigure figure = new WButtonFigure();
		figure.setText(getButton().getText());
		figure.setSelected(getButton().isSelected());
		return figure;
	}

	private WButton getButton() {
		return (WButton) getModel();
	}

	private void performDirectEdit() {
		new WButtonEditManager(this, new WButtonCellEditorLocator(
				(WButtonFigure) getFigure())).show();
	}

	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT)
			performDirectEdit();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("selected"))//$NON-NLS-1$
			refreshVisuals();
		else if (evt.getPropertyName().equalsIgnoreCase("text"))//$NON-NLS-1$
			refreshVisuals();
		else
			super.propertyChange(evt);
	}

	protected void refreshVisuals() {
		((WButtonFigure) getFigure()).setText(getButton().getText());
		((WButtonFigure) getFigure()).setSelected((getButton().isSelected()));
		super.refreshVisuals();
	}

}
