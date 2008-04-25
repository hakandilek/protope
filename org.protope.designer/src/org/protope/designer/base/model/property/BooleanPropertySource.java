package org.protope.designer.base.model.property;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public class BooleanPropertySource implements IPropertySource {

	private final Boolean value;

	public BooleanPropertySource(Boolean value) {
		this.value = value;
	}

	@Override
	public Object getEditableValue() {
		return new Boolean(value);
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[0];
	}

	@Override
	public Object getPropertyValue(Object propertyName) {
		return null;
	}

	@Override
	public boolean isPropertySet(Object propertyName) {
		return true;
	}

	@Override
	public void resetPropertyValue(Object propertyName) {
	}

	@Override
	public void setPropertyValue(Object propertyName, Object value) {
	}

}
