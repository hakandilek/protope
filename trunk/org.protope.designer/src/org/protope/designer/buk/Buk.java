package org.protope.designer.buk;

import org.eclipse.draw2d.Figure;
import org.eclipse.gef.EditPart;
import org.protope.designer.base.model.UIElement;

public class Buk {

	private Class<? extends Figure> figure;

	private Class<? extends UIElement> model;

	private Class<? extends EditPart> edit;

	private String smallIconPath;
	private String mediumIconPath;

	private String paletteLabel;
	private String paletteDescription;

	public Class<? extends Figure> getFigure() {
		return figure;
	}

	public void setFigure(Class<? extends Figure> figure) {
		this.figure = figure;
	}

	public Class<? extends UIElement> getModel() {
		return model;
	}

	public void setModel(Class<? extends UIElement> model) {
		this.model = model;
	}

	public Class<? extends EditPart> getEdit() {
		return edit;
	}

	public void setEdit(Class<? extends EditPart> edit) {
		this.edit = edit;
	}

	public String getSmallIconPath() {
		return smallIconPath;
	}

	public void setSmallIconPath(String smallIconPath) {
		this.smallIconPath = smallIconPath;
	}

	public String getMediumIconPath() {
		return mediumIconPath;
	}

	public void setMediumIconPath(String mediumIconPath) {
		this.mediumIconPath = mediumIconPath;
	}

	public String getPaletteLabel() {
		return paletteLabel;
	}

	public void setPaletteLabel(String paletteLabel) {
		this.paletteLabel = paletteLabel;
	}

	public String getPaletteDescription() {
		return paletteDescription;
	}

	public void setPaletteDescription(String paletteDescription) {
		this.paletteDescription = paletteDescription;
	}

}
