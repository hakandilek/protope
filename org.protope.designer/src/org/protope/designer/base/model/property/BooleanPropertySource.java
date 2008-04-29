package org.protope.designer.base.model.property;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public class BooleanPropertySource implements IPropertySource {

	private final Boolean value;

	public BooleanPropertySource(Boolean value) {
		this.value = value;
	}

	public Object getEditableValue() {
		return new Boolean(value);
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[0];
	}

	public Object getPropertyValue(Object propertyName) {
		return null;
	}

	public boolean isPropertySet(Object propertyName) {
		return true;
	}

	public void resetPropertyValue(Object propertyName) {
	}

	public void setPropertyValue(Object propertyName, Object value) {
	}

}
