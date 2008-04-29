package org.protope.designer.base.model.property;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public class IntegerPropertySource implements IPropertySource {

	private final Integer value;

	public IntegerPropertySource(Integer value) {
		this.value = value;
	}

	public Object getEditableValue() {
		return new Integer(value);
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
