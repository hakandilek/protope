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
import org.protope.designer.web.figures.WLinkFigure;
import org.protope.designer.web.i18n.WebPaletteMessages;
import org.protope.designer.web.model.WLink;

public class WLinkEditPart extends BaseEditPart {

	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getValue(AccessibleControlEvent e) {
				e.result = getLabel().getText();
			}

			public void getName(AccessibleEvent e) {
				e.result = WebPaletteMessages.WebPalette_Tool_WLink;
			}
		};
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new WLinkDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new WLinkEditPolicy());
	}

	protected IFigure createFigure() {
		WLinkFigure figure = new WLinkFigure();
		figure.setText(getLabel().getText());
		return figure;
	}

	private WLink getLabel() {
		return (WLink) getModel();
	}

	private void performDirectEdit() {
		new WLinkEditManager(this, new WLinkCellEditorLocator(
				(WLinkFigure) getFigure())).show();
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
		((WLinkFigure) getFigure()).setText(getLabel().getText());
		super.refreshVisuals();
	}

}
