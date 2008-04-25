package org.protope.designer.base.model.property;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

public class PropertySupport<T> {

	private final List<Property> properties = new ArrayList<Property>();

	private final T subject;

	public PropertySupport(T subject) {
		super();
		this.subject = subject;
	}

	public void addProperty(String id, String name,
			PropertySourceFactory propertySourceFactory,
			ICellEditorValidator validator) {
		Property p = new Property(id, name, propertySourceFactory, validator);
		properties.add(p);
	}

	public void addProperty(String id, String name,
			PropertySourceFactory propertySourceFactory) {
		addProperty(id, name, propertySourceFactory, null);
	}

	public void removeProperty(String id) {
		Property p = getProperty(id);
		if (p != null)
			properties.remove(p);
	}

	/**
	 * @return the descriptors
	 */
	public List<Property> getProperties() {
		return properties;
	}

	public Property getProperty(String id) {
		if (id == null)
			return null;
		for (Property d : properties) {
			if (id.equals(d.getId())) {
				return d;
			}
		}
		return null;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		List<Property> list = getProperties();
		IPropertyDescriptor[] arr = new IPropertyDescriptor[list.size()];
		for (int i = 0; i < arr.length; i++) {
			Property p = list.get(i);
			arr[i] = p.getDescriptor();
		}
		return arr;
	}

	protected void setValue(Object object, String propertyName, Object value) {
		try {
			Class<?> cls = object.getClass();
			Method method = new java.beans.PropertyDescriptor(propertyName, cls)
					.getWriteMethod();
			method.invoke(object, new Object[] { value });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Object getValue(Object object, String propertyName) {
		try {
			Class<?> cls = object.getClass();
			Method method = new java.beans.PropertyDescriptor(propertyName, cls)
					.getReadMethod();
			Object value = method.invoke(object, new Object[0]);
			Property property = getProperty(propertyName);
			if (property == null)
				return value;
			PropertySourceFactory factory = property.getPropertySourceFactory();
			if (factory == null)
				return value;
			return factory.create(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getPropertyValue(String propertyName) {
		return getValue(subject, propertyName);
	}

	public void setPropertyValue(String propertyName, Object value) {
		setValue(subject, propertyName, value);
	}

}
