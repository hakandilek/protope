package org.protope.designer.webbuk.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.protope.designer.base.edit.BaseEditPart;
import org.protope.designer.i18n.ProtopeMessages;
import org.protope.designer.webbuk.figures.WTextEditFigure;

public class WTextEditEditPart extends BaseEditPart {

	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getValue(AccessibleControlEvent e) {
				e.result = "text";
			}

			public void getName(AccessibleEvent e) {
				e.result = ProtopeMessages.UIPlugin_Tool_CreationTool_WTextEdit;
			}
		};
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new WImageEditPolicy());
	}

	protected IFigure createFigure() {
		WTextEditFigure figure = new WTextEditFigure();
		return figure;
	}

}
