package org.protope.designer.base.model.property;

import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class Property {
	
	private PropertyDescriptor descriptor;
	private final String id;
	private final String name;
	private final PropertySourceFactory propertySourceFactory;
	private final ICellEditorValidator validator;

	public Property(String id, String name, PropertySourceFactory propertySourceFactory, ICellEditorValidator validator) {
		this.id = id;
		this.name = name;
		this.propertySourceFactory = propertySourceFactory;
		this.validator = validator;
	}

	/**
	 * @return the descriptor
	 */
	public IPropertyDescriptor getDescriptor() {
		if (descriptor == null) {
			descriptor = new PropertyDescriptor(id, name);
			descriptor.setValidator(validator);
		}
		return descriptor;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the propertySourceFactory
	 */
	public PropertySourceFactory getPropertySourceFactory() {
		return propertySourceFactory;
	}

}
