package org.protope.designer.web.edit;

import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WTextAreaEditPart extends WTextEditEditPart {

	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getValue(AccessibleControlEvent e) {
				e.result = "text";
			}

			public void getName(AccessibleEvent e) {
				e.result = WebPaletteMessages.WebPalette_Tool_WTextArea;
			}
		};
	}

}
