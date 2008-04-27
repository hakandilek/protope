package org.protope.designer.web.model;

import org.eclipse.ui.views.properties.IPropertySource;
import org.protope.designer.base.model.property.BasePropertyHandler;
import org.protope.designer.base.model.property.PropertyHandler;
import org.protope.designer.base.model.property.PropertySourceFactory;
import org.protope.designer.base.model.property.StringCellEditorValidator;
import org.protope.designer.base.model.property.StringPropertySource;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WComboboxProperties extends BasePropertyHandler<WCombobox> implements
		PropertyHandler {

	public WComboboxProperties(WCombobox subject) {
		super(subject);
		addProperty("text",
				WebPaletteMessages.WebPalette_Tool_WCombobox_Property_text,
				new PropertySourceFactory() {
					@Override
					public IPropertySource create(Object value) {
						return new StringPropertySource((String) value);
					}
				}, StringCellEditorValidator.instance());
	}

}
