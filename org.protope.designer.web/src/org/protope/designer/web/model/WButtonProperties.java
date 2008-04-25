package org.protope.designer.web.model;

import org.eclipse.ui.views.properties.IPropertySource;
import org.protope.designer.base.model.property.BasePropertyHandler;
import org.protope.designer.base.model.property.BooleanCellEditorValidator;
import org.protope.designer.base.model.property.BooleanPropertySource;
import org.protope.designer.base.model.property.PropertyHandler;
import org.protope.designer.base.model.property.PropertySourceFactory;
import org.protope.designer.base.model.property.StringCellEditorValidator;
import org.protope.designer.base.model.property.StringPropertySource;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WButtonProperties extends BasePropertyHandler<WButton> implements
		PropertyHandler {

	public WButtonProperties(WButton subject) {
		super(subject);
		addProperty("text",
				WebPaletteMessages.WebPalette_Tool_WButton_Property_text,
				new PropertySourceFactory() {
					@Override
					public IPropertySource create(Object value) {
						return new StringPropertySource((String) value);
					}
				}, StringCellEditorValidator.instance());
		addProperty("selected",
				WebPaletteMessages.WebPalette_Tool_WButton_Property_selected,
				new PropertySourceFactory() {
					@Override
					public IPropertySource create(Object value) {
						return new BooleanPropertySource((Boolean) value);
					}
				}, BooleanCellEditorValidator.instance());
	}

}
