package org.protope.designer.webbuk;

import org.protope.designer.buk.Buk;
import org.protope.designer.buk.BukBag;
import org.protope.designer.i18n.ProtopeMessages;
import org.protope.designer.webbuk.edit.WHorizontalLineEditPart;
import org.protope.designer.webbuk.edit.WImageEditPart;
import org.protope.designer.webbuk.edit.WNoteEditPart;
import org.protope.designer.webbuk.edit.WVerticalLineEditPart;
import org.protope.designer.webbuk.figures.WHorizontalLineFigure;
import org.protope.designer.webbuk.figures.WImageFigure;
import org.protope.designer.webbuk.figures.WNoteFigure;
import org.protope.designer.webbuk.figures.WVerticalLineFigure;
import org.protope.designer.webbuk.model.WHorizontalLine;
import org.protope.designer.webbuk.model.WImage;
import org.protope.designer.webbuk.model.WNote;
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
		add(createWNote());
		add(createWImage());
	}

	protected Buk createWHorizontalLine() {
		Buk buk = new Buk();
		buk.setEdit(WHorizontalLineEditPart.class);
		buk.setFigure(WHorizontalLineFigure.class);
		buk.setModel(WHorizontalLine.class);
		buk.setPaletteDescription(ProtopeMessages.UIPlugin_Tool_CreationTool_HorizontalLine_Description);
		buk.setPaletteLabel(ProtopeMessages.UIPlugin_Tool_CreationTool_HorizontalLine_Label);
		buk.setSmallIconPath(WHorizontalLine.ICON_PATH);
		buk.setMediumIconPath("icons/WHorizontalLine24.gif");
		return buk;
	}

	protected Buk createWVerticalLine() {
		Buk buk = new Buk();
		buk.setEdit(WVerticalLineEditPart.class);
		buk.setFigure(WVerticalLineFigure.class);
		buk.setModel(WVerticalLine.class);
		buk.setPaletteDescription(ProtopeMessages.UIPlugin_Tool_CreationTool_VerticalLine_Description);
		buk.setPaletteLabel(ProtopeMessages.UIPlugin_Tool_CreationTool_VerticalLine_Label);
		buk.setSmallIconPath(WVerticalLine.ICON_PATH);
		buk.setMediumIconPath("icons/WVerticalLine24.gif");
		return buk;
	}

	protected Buk createWNote() {
		Buk buk = new Buk();
		buk.setEdit(WNoteEditPart.class);
		buk.setFigure(WNoteFigure.class);
		buk.setModel(WNote.class);
		buk.setPaletteDescription(ProtopeMessages.UIPlugin_Tool_CreationTool_Label_Description);
		buk.setPaletteLabel(ProtopeMessages.UIPlugin_Tool_CreationTool_Label_Label);
		buk.setSmallIconPath(WNote.ICON_PATH);
		buk.setMediumIconPath("icons/WNote24.gif");
		return buk;
	}

	protected Buk createWImage() {
		Buk buk = new Buk();
		buk.setEdit(WImageEditPart.class);
		buk.setFigure(WImageFigure.class);
		buk.setModel(WImage.class);
		buk.setPaletteDescription(ProtopeMessages.UIPlugin_Tool_CreationTool_Image_Description);
		buk.setPaletteLabel(ProtopeMessages.UIPlugin_Tool_CreationTool_Image_Label);
		buk.setSmallIconPath(WImage.ICON_PATH);
		buk.setMediumIconPath("icons/WImage24.gif");
		return buk;
	}

}
