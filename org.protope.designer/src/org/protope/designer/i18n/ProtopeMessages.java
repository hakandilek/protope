/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.protope.designer.i18n;

import org.eclipse.osgi.util.NLS;

public class ProtopeMessages extends NLS {

	
	public static String CreatePrototypePage1_Title;
	public static String CreatePrototypePage1_Description;
	public static String CreatePrototypePage1_ModelNames_UIModelName;
	public static String CreatePrototypePage1_ModelNames_GroupName;

	public static String PrintDialog_Height;
	public static String PrintDialog_Page;
	public static String PrintDialog_Tile;
	public static String PrintDialog_Title;
	public static String PrintDialog_Width;

	public static String DimensionPropertySource_Property_Height_Label;
	public static String DimensionPropertySource_Property_Width_Label;
	public static String LocationPropertySource_Property_X_Label;
	public static String LocationPropertySource_Property_Y_Label;

	public static String CellEditorValidator_NotANumberMessage;

	public static String PropertyDescriptor_UISubPart_Location;
	public static String PropertyDescriptor_UISubPart_Size;

	public static String OrphanChildCommand_Label;
	public static String DeleteCommand_Label;
	public static String AddCommand_Label;
	public static String SetLocationCommand_Label_Location;
	public static String SetLocationCommand_Label_Resize;

	public static String UIElementEditPolicy_OrphanCommandLabelText;
	public static String UIContainerEditPolicy_OrphanCommandLabelText;
	public static String UIDiagram_LabelText;
	public static String UIXYLayoutEditPolicy_AddCommandLabelText;
	public static String UIXYLayoutEditPolicy_CreateCommandLabelText;

	// View menu actions
	public static String ToggleRulerVisibility_Label;
	public static String ToggleSnapToGeometry_Label;
	public static String ToggleGrid_Label;

	public static String UIPlugin_Tool_CreationTool_HorizontalLine_Description;
	public static String UIPlugin_Tool_CreationTool_HorizontalLine_Label;
	public static String UIPlugin_Tool_CreationTool_VerticalLine_Description;
	public static String UIPlugin_Tool_CreationTool_VerticalLine_Label;
	public static String UIPlugin_Tool_CreationTool_Label_Description;
	public static String UIPlugin_Tool_CreationTool_Label_Label;
	public static String UIPlugin_Tool_CreationTool_Label;
	public static String UIPlugin_Tool_CreationTool_Image_Description;
	public static String UIPlugin_Tool_CreationTool_Image_Label;
	public static String UIPlugin_Tool_CreationTool_Image;

	public static String AlignmentAction_AlignSubmenu_ActionLabelText;
	public static String CloneCommand_Label;
	public static String ConnectionCommand_Description;
	public static String ConnectionCommand_Label;
	public static String CreateCommand_Label;
	public static String CreateGuideCommand_Label;
	public static String DeleteGuideCommand_Label;
	public static String GraphicalEditor_CLOSE_BUTTON_UI;
	public static String GraphicalEditor_FILE_DELETED_TITLE_UI;
	public static String GraphicalEditor_FILE_DELETED_WITHOUT_SAVE_INFO;
	public static String GraphicalEditor_SAVE_BUTTON_UI;
	public static String Marquee_Stack;
	
	public static String UIPlugin_Category_Components_Label;
	public static String UIPlugin_Category_ControlGroup_Label;

	public static String MoveGuideCommand_Label;
	public static String PaletteCustomizer_InvalidCharMessage;
	public static String PasteAction_ActionLabelText;
	public static String PropertyDescriptor_Label_Text;
	public static String ReorderPartCommand_Label;
	public static String ViewMenu_LabelText;

	static {
		NLS
				.initializeMessages(
						"org.protope.designer.i18n.ProtopeMessages", ProtopeMessages.class); //$NON-NLS-1$
	}

}