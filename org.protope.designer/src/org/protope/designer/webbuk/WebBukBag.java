package org.protope.designer.webbuk;

import org.protope.designer.buk.Buk;
import org.protope.designer.buk.BukBag;
import org.protope.designer.i18n.ProtopeMessages;
import org.protope.designer.webbuk.edit.WButtonEditPart;
import org.protope.designer.webbuk.edit.WHorizontalLineEditPart;
import org.protope.designer.webbuk.edit.WImageEditPart;
import org.protope.designer.webbuk.edit.WLabelEditPart;
import org.protope.designer.webbuk.edit.WNoteEditPart;
import org.protope.designer.webbuk.edit.WTextEditEditPart;
import org.protope.designer.webbuk.edit.WVerticalLineEditPart;
import org.protope.designer.webbuk.figures.WButtonFigure;
import org.protope.designer.webbuk.figures.WHorizontalLineFigure;
import org.protope.designer.webbuk.figures.WImageFigure;
import org.protope.designer.webbuk.figures.WLabelFigure;
import org.protope.designer.webbuk.figures.WNoteFigure;
import org.protope.designer.webbuk.figures.WTextEditFigure;
import org.protope.designer.webbuk.figures.WVerticalLineFigure;
import org.protope.designer.webbuk.model.WButton;
import org.protope.designer.webbuk.model.WHorizontalLine;
import org.protope.designer.webbuk.model.WImage;
import org.protope.designer.webbuk.model.WLabel;
import org.protope.designer.webbuk.model.WNote;
import org.protope.designer.webbuk.model.WTextEdit;
import org.protope.designer.webbuk.model.WVerticalLine;

public class WebBukBag extends BukBag {

	public static final WebBukBag INSTANCE = new WebBukBag();

	protected WebBukBag() {
		super();
		initialize();
	}

	protected void initialize() {
		addElements();
		setDrawerIconPath("model/icons/components.gif");
		setDrawerLabel(ProtopeMessages.UIPlugin_Category_Components_Label);
	}

	public static final WebBukBag getInstance() {
		return INSTANCE;
	}

	protected void addElements() {
		add(createWHorizontalLine());
		add(createWVerticalLine());
		add(createWLabel());
		add(createWNote());
		add(createWImage());
		add(createWTextEdit());
		add(createWButton());
	}

	protected Buk createWHorizontalLine() {
		Buk buk = new Buk();
		buk.setEdit(WHorizontalLineEditPart.class);
		buk.setFigure(WHorizontalLineFigure.class);
		buk.setModel(WHorizontalLine.class);
		buk.setPaletteDescription(ProtopeMessages.UIPlugin_Tool_CreationTool_WHorizontalLine_Description);
		buk.setPaletteLabel(ProtopeMessages.UIPlugin_Tool_CreationTool_WHorizontalLine_Label);
		buk.setSmallIconPath(WHorizontalLine.ICON_PATH);
		buk.setMediumIconPath("icons/WHorizontalLine24.gif");
		return buk;
	}

	protected Buk createWVerticalLine() {
		Buk buk = new Buk();
		buk.setEdit(WVerticalLineEditPart.class);
		buk.setFigure(WVerticalLineFigure.class);
		buk.setModel(WVerticalLine.class);
		buk.setPaletteDescription(ProtopeMessages.UIPlugin_Tool_CreationTool_WVerticalLine_Description);
		buk.setPaletteLabel(ProtopeMessages.UIPlugin_Tool_CreationTool_WVerticalLine_Label);
		buk.setSmallIconPath(WVerticalLine.ICON_PATH);
		buk.setMediumIconPath("icons/WVerticalLine24.gif");
		return buk;
	}

	protected Buk createWNote() {
		Buk buk = new Buk();
		buk.setEdit(WNoteEditPart.class);
		buk.setFigure(WNoteFigure.class);
		buk.setModel(WNote.class);
		buk.setPaletteDescription(ProtopeMessages.UIPlugin_Tool_CreationTool_WNote_Description);
		buk.setPaletteLabel(ProtopeMessages.UIPlugin_Tool_CreationTool_WNote_Label);
		buk.setSmallIconPath(WNote.ICON_PATH);
		buk.setMediumIconPath("icons/WNote24.gif");
		return buk;
	}

	protected Buk createWImage() {
		Buk buk = new Buk();
		buk.setEdit(WImageEditPart.class);
		buk.setFigure(WImageFigure.class);
		buk.setModel(WImage.class);
		buk.setPaletteDescription(ProtopeMessages.UIPlugin_Tool_CreationTool_WImage_Description);
		buk.setPaletteLabel(ProtopeMessages.UIPlugin_Tool_CreationTool_WImage_Label);
		buk.setSmallIconPath(WImage.ICON_PATH);
		buk.setMediumIconPath("icons/WImage24.gif");
		return buk;
	}

	protected Buk createWTextEdit() {
		Buk buk = new Buk();
		buk.setEdit(WTextEditEditPart.class);
		buk.setFigure(WTextEditFigure.class);
		buk.setModel(WTextEdit.class);
		buk.setPaletteDescription(ProtopeMessages.UIPlugin_Tool_CreationTool_WTextEdit_Description);
		buk.setPaletteLabel(ProtopeMessages.UIPlugin_Tool_CreationTool_WTextEdit_Label);
		buk.setSmallIconPath(WTextEdit.ICON_PATH);
		buk.setMediumIconPath("icons/WTextEdit24.gif");
		return buk;
	}

	protected Buk createWLabel() {
		Buk buk = new Buk();
		buk.setEdit(WLabelEditPart.class);
		buk.setFigure(WLabelFigure.class);
		buk.setModel(WLabel.class);
		buk.setPaletteDescription(ProtopeMessages.UIPlugin_Tool_CreationTool_WLabel_Description);
		buk.setPaletteLabel(ProtopeMessages.UIPlugin_Tool_CreationTool_WLabel_Label);
		buk.setSmallIconPath(WLabel.ICON_PATH);
		buk.setMediumIconPath("icons/WLabel24.gif");
		return buk;
	}

	protected Buk createWButton() {
		Buk buk = new Buk();
		buk.setEdit(WButtonEditPart.class);
		buk.setFigure(WButtonFigure.class);
		buk.setModel(WButton.class);
		buk.setPaletteDescription(ProtopeMessages.UIPlugin_Tool_CreationTool_WButton_Description);
		buk.setPaletteLabel(ProtopeMessages.UIPlugin_Tool_CreationTool_WButton_Label);
		buk.setSmallIconPath(WButton.ICON_PATH);
		buk.setMediumIconPath("icons/WButton24.gif");
		return buk;
	}

}
