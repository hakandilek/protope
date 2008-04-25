package org.protope.designer.base.model.property;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

public interface PropertyHandler {

	/**
	 * Sets the value of a given property with the value supplied. Also fires a
	 * property change if necessary.
	 * 
	 * @param propertyName
	 *            Name of the parameter to be changed.
	 * @param value
	 *            Value to be set to the given parameter.
	 */
	void setPropertyValue(String propertyName, Object value);

	/**
	 * Returns an Object which represents the appropriate value for the property
	 * name supplied.
	 * 
	 * @param propertyName
	 *            Name of the property for which the the values are needed.
	 * @return Object which is the value of the property.
	 */
	Object getPropertyValue(String propertyName);

	/**
	 * Returns useful property descriptors for the use in property sheets. this
	 * supports location and size.
	 * 
	 * @return Array of property descriptors.
	 */
	IPropertyDescriptor[] getPropertyDescriptors();

}
