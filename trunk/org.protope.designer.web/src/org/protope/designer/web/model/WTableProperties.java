package org.protope.designer.web.model;

import org.eclipse.ui.views.properties.IPropertySource;
import org.protope.designer.base.model.property.BasePropertyHandler;
import org.protope.designer.base.model.property.IntegerCellEditorValidator;
import org.protope.designer.base.model.property.IntegerPropertySource;
import org.protope.designer.base.model.property.PropertyHandler;
import org.protope.designer.base.model.property.PropertySourceFactory;
import org.protope.designer.web.i18n.WebPaletteMessages;

public class WTableProperties extends BasePropertyHandler<WTable> implements
		PropertyHandler {

	public WTableProperties(WTable subject) {
		super(subject);
		addProperty("columnCount",
				WebPaletteMessages.WebPalette_Tool_WTable_Property_columnCount,
				new PropertySourceFactory() {
					public IPropertySource create(Object value) {
						return new IntegerPropertySource((Integer) value);
					}
				}, IntegerCellEditorValidator.instance());
		addProperty("rowCount",
				WebPaletteMessages.WebPalette_Tool_WTable_Property_rowCount,
				new PropertySourceFactory() {
					public IPropertySource create(Object value) {
						return new IntegerPropertySource((Integer) value);
					}
				}, IntegerCellEditorValidator.instance());
	}

}
