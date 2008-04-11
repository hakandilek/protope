package org.protope.designer.utils;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;

public class ImageUtils {

	public static ImageDescriptor getImageDescriptor(String pluginID,
			String imagePath) {

		URL imageURL = PluginUtils.getURL(pluginID, imagePath);
		ImageDescriptor image = ImageDescriptor.createFromURL(imageURL);
		return image;
	}

}
