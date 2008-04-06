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
package org.protope.designer.plugin;


public class ProtopeDesignerPlugin extends
		org.eclipse.ui.plugin.AbstractUIPlugin {

	private static ProtopeDesignerPlugin singleton;

	public static ProtopeDesignerPlugin getDefault() {
		return singleton;
	}

	public ProtopeDesignerPlugin() {
		if (singleton == null) {
			singleton = this;
		}
	}

}
