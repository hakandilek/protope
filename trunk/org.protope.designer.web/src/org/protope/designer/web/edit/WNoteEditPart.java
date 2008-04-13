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
import org.protope.designer.web.figures.WNoteFigure;
import org.protope.designer.web.i18n.WebPaletteMessages;
import org.protope.designer.web.model.WNote;

public class WNoteEditPart extends BaseEditPart {

	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getValue(AccessibleControlEvent e) {
				e.result = getLabel().getLabelContents();
			}

			public void getName(AccessibleEvent e) {
				e.result = WebPaletteMessages.WebPalette_Tool_WNote;
			}
		};
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new WNoteDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new WNoteEditPolicy());
	}

	protected IFigure createFigure() {
		WNoteFigure label = new WNoteFigure();
		return label;
	}

	private WNote getLabel() {
		return (WNote) getModel();
	}

	private void performDirectEdit() {
		new WNoteEditManager(this, new WNoteCellEditorLocator(
				(WNoteFigure) getFigure())).show();
	}

	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT)
			performDirectEdit();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("labelContents"))//$NON-NLS-1$
			refreshVisuals();
		else
			super.propertyChange(evt);
	}

	protected void refreshVisuals() {
		((WNoteFigure) getFigure()).setText(getLabel()
				.getLabelContents());
		super.refreshVisuals();
	}

}
