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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

abstract public class UIElement implements IPropertySource, Cloneable,
		Serializable {

	public static final String CHILDREN = "Children"; //$NON-NLS-1$

	transient protected PropertyChangeSupport listeners;
	
	static final long serialVersionUID = 1;

	
	public UIElement() {
		super();
	}

	private PropertyChangeSupport getListeners() {
		if (listeners == null) {
			listeners = new PropertyChangeSupport(this);
		}
		return listeners;
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		getListeners().addPropertyChangeListener(l);
	}

	protected void firePropertyChange(String prop, Object old, Object newValue) {
		getListeners().firePropertyChange(prop, old, newValue);
	}

	protected void fireChildAdded(String prop, Object child, Object index) {
		getListeners().firePropertyChange(prop, index, child);
	}

	protected void fireChildRemoved(String prop, Object child) {
		getListeners().firePropertyChange(prop, child, null);
	}

	protected void fireStructureChange(String prop, Object child) {
		getListeners().firePropertyChange(prop, null, child);
	}

	public Object getEditableValue() {
		return this;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[0];
	}

	public Object getPropertyValue(Object propName) {
		return null;
	}

	final Object getPropertyValue(String propName) {
		return null;
	}

	public boolean isPropertySet(Object propName) {
		return isPropertySet((String) propName);
	}

	final boolean isPropertySet(String propName) {
		return true;
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		getListeners().removePropertyChangeListener(l);
	}

	public void resetPropertyValue(Object propName) {
	}

	final void resetPropertyValue(String propName) {
	}

	public void setPropertyValue(Object propName, Object val) {
	}

	final void setPropertyValue(String propName, Object val) {
	}

	public void update() {
	}

	
}
