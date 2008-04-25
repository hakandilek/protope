package org.protope.designer.base.model.property;

import org.eclipse.ui.views.properties.IPropertySource;

public interface PropertySourceFactory {

	IPropertySource create(Object value);

}
