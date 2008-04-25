/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.protope.designer.base.model;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.protope.designer.base.model.property.PropertyHandler;

abstract public class UIElementPart extends UIElement implements Cloneable {

	private String id;
	private UIGuide verticalGuide, horizontalGuide;
	protected Point location = new Point(0, 0);
	static final long serialVersionUID = 1;
	protected Dimension size = new Dimension(-1, -1);

	public static String ID_LOCATION = "location"; //$NON-NLS-1$

	protected static Image createImage(Class<?> rsrcClass, String name) {
		InputStream stream = rsrcClass.getResourceAsStream(name);
		Image image = new Image(null, stream);
		try {
			stream.close();
		} catch (IOException ioe) {
		}
		return image;
	}

	public UIElementPart() {
		setID(getNewID());
	}

	public UIGuide getHorizontalGuide() {
		return horizontalGuide;
	}

	public Image getIcon() {
		return getIconImage();
	}

	abstract public Image getIconImage();

	public String getID() {
		return id;
	}

	public Point getLocation() {
		return location;
	}

	abstract protected String getNewID();

	/**
	 * Returns useful property descriptors for the use in property sheets. this
	 * supports location and size.
	 * 
	 * @return Array of property descriptors.
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		PropertyHandler propertyHandler = getPropertyHandler();
		if (propertyHandler != null)
			return propertyHandler.getPropertyDescriptors();
		return new PropertyDescriptor[0];
	}

	/**
	 * Returns an Object which represents the appropriate value for the property
	 * name supplied.
	 * 
	 * @param propName
	 *            Name of the property for which the the values are needed.
	 * @return Object which is the value of the property.
	 */
	public Object getPropertyValue(Object propName) {
		PropertyHandler propertyHandler = getPropertyHandler();
		if (propertyHandler != null)
			return propertyHandler.getPropertyValue(propName+"");
		return null;
	}

	/**
	 * Sets the value of a given property with the value supplied. Also fires a
	 * property change if necessary.
	 * 
	 * @param id
	 *            Name of the parameter to be changed.
	 * @param value
	 *            Value to be set to the given parameter.
	 */
	public void setPropertyValue(Object id, Object value) {
		PropertyHandler propertyHandler = getPropertyHandler();
		if (propertyHandler != null)
			propertyHandler.setPropertyValue(id+"", value);
	}

	public Dimension getSize() {
		return size;
	}

	public UIGuide getVerticalGuide() {
		return verticalGuide;
	}

	/**
	 * 
	 */
	public boolean isPropertySet() {
		return true;
	}

	public void setHorizontalGuide(UIGuide hGuide) {
		horizontalGuide = hGuide;
		/*
		 * @TODO:Pratik firePropertyChanged?
		 */
	}

	/*
	 * Does nothing for the present, but could be used to reset the properties
	 * of this to whatever values are desired.
	 * 
	 * @param id Parameter which is to be reset.
	 * 
	 * public void resetPropertyValue(Object id){ if(ID_SIZE.equals(id)){;}
	 * if(ID_LOCATION.equals(id)){;} }
	 */
	public void setID(String s) {
		id = s;
	}

	public void setLocation(Point p) {
		if (location.equals(p))
			return;
		location = p;
		firePropertyChange("location", null, p); //$NON-NLS-1$
	}

	public void setSize(Dimension d) {
		if (size.equals(d))
			return;
		size = d;
		firePropertyChange("size", null, size); //$NON-NLS-1$
	}

	public void setVerticalGuide(UIGuide vGuide) {
		verticalGuide = vGuide;
	}

	public abstract Object clone();

	public abstract PropertyHandler getPropertyHandler();
}
