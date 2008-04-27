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
					@Override
					public IPropertySource create(Object value) {
						return new IntegerPropertySource((Integer) value);
					}
				}, IntegerCellEditorValidator.instance());
		addProperty("rowCount",
				WebPaletteMessages.WebPalette_Tool_WTable_Property_rowCount,
				new PropertySourceFactory() {
					@Override
					public IPropertySource create(Object value) {
						return new IntegerPropertySource((Integer) value);
					}
				}, IntegerCellEditorValidator.instance());
		/**
		 * <pre>
		 * addProperty(&quot;text&quot;, WebPaletteMessages.WebPalette_Tool_WTable_Property_text,
		 * 		new PropertySourceFactory() {
		 * 			&#064;Override
		 * 			public IPropertySource create(Object value) {
		 * 				return new StringPropertySource((String) value);
		 * 			}
		 * 		}, StringCellEditorValidator.instance());
		 * addProperty(&quot;selected&quot;,
		 * 		WebPaletteMessages.WebPalette_Tool_WTable_Property_selected,
		 * 		new PropertySourceFactory() {
		 * 			&#064;Override
		 * 			public IPropertySource create(Object value) {
		 * 				return new BooleanPropertySource((Boolean) value);
		 * 			}
		 * 		}, BooleanCellEditorValidator.instance());
		 * </pre>
		 */
	}

}
