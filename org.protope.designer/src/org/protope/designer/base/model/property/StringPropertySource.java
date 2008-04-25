package org.protope.designer.base.model.property;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public class StringPropertySource implements IPropertySource {

	private final String value;

	public StringPropertySource(String value) {
		this.value = value;
	}

	@Override
	public Object getEditableValue() {
		return ""+value;
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
