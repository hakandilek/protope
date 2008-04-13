package org.protope.designer.web.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.protope.designer.base.edit.BaseEditPart;
import org.protope.designer.web.figures.WImageFigure;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WImageEditPart extends BaseEditPart {

	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getValue(AccessibleControlEvent e) {
				e.result = "image";
			}

			public void getName(AccessibleEvent e) {
				e.result = WebPaletteMessages.WebPalette_Tool_WImage;
			}
		};
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new WImageEditPolicy());
	}

	protected IFigure createFigure() {
		WImageFigure label = new WImageFigure();
		return label;
	}

}
