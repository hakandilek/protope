package org.protope.designer.base.model.property;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertySource;
import org.protope.designer.base.model.UIElementPart;
import org.protope.designer.base.model.property.PropertyHandler;
import org.protope.designer.base.model.property.PropertySourceFactory;
import org.protope.designer.base.model.property.PropertySupport;
import org.protope.designer.i18n.ProtopeMessages;

public class BasePropertyHandler<T extends UIElementPart> extends PropertySupport<T>
		implements PropertyHandler {

	public BasePropertyHandler(T subject) {
		super(subject);
		addProperty("size", ProtopeMessages.PropertyDescriptor_UISubPart_Size,
				new PropertySourceFactory() {
					@Override
					public IPropertySource create(Object value) {
						return new DimensionPropertySource((Dimension) value);
					}
				});
		addProperty("location",
				ProtopeMessages.PropertyDescriptor_UISubPart_Location,
				new PropertySourceFactory() {
					@Override
					public IPropertySource create(Object value) {
						return new LocationPropertySource((Point) value);
					}
				});
	}

}
