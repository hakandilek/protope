package org.protope.designer.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class PluginUtils {

    /** the prefix of OSGi bundle locations */
    public static final String BUNDLE_LOCATION_PREFIX = "initial@reference:";

	/**
     * Checks if the bundle is ready for usage.
     * 
     * @param bundle
     *            the bundle
     * @return true if bundle is ready
     */
    public static boolean isReady(Bundle bundle)
    {
        return bundle != null && isReady(bundle.getState());
    }

    /**
     * Checks if the bundle is ready for usage.
     * 
     * @param bundleState
     *            the state of the bundle
     * @see Bundle#RESOLVED
     * @see Bundle#STARTING
     * @see Bundle#ACTIVE
     * @see Bundle#STOPPING
     * 
     * @return true if bundle is ready
     */
    public static boolean isReady(int bundleState)
    {
        return (bundleState & (Bundle.RESOLVED | Bundle.STARTING | Bundle.ACTIVE | Bundle.STOPPING)) != 0;
    }

	public static URL getURL(String pluginID, String urlPath) {
		if (pluginID == null) {
			throw new IllegalArgumentException("pluginID not specified");
		}
		if (urlPath == null) {
			throw new IllegalArgumentException("urlPath not specified");
		}

		// if the bundle is not ready then there is no image
		Bundle bundle = Platform.getBundle(pluginID);

		// check if bundle is loaded
		if (!PluginUtils.isReady(bundle)) {
			// logger.info("Can't load image. Bundle is not read to access: " +
			// pluginID);
			return null;
		}

		// get url path
		URL platformUrl = null;

		Path path = new Path(urlPath);
		platformUrl = FileLocator.find(bundle, path, Collections.emptyMap());

		if (platformUrl == null) {
			platformUrl = bundle.getResource(urlPath);
		}

		URL url = null;
		// if (logger.isDebugEnabled())
		// logger.debug("platformUrl: " + platformUrl);

		if (platformUrl != null) {
			try {
				url = FileLocator.resolve(platformUrl);
			} catch (Exception e) {
				// logger.error("Error resolving platform url: " +
				// e.toString());
			}
		} else if (Platform.inDevelopmentMode()) {
			// if (logger.isDebugEnabled())
			// logger.debug("Platform is in development mode; create filesystem
			// image url");

			String bundleLocation = bundle.getLocation();
			// if (logger.isDebugEnabled())
			// logger.debug("Framework Bundle location: " + bundleLocation);

			// build url
			bundleLocation = bundleLocation.substring(
					PluginUtils.BUNDLE_LOCATION_PREFIX.length(), bundleLocation
							.length());
			bundleLocation = bundleLocation + "class" + urlPath;

			// if (logger.isDebugEnabled())
			// logger.debug("Bundle location: " + bundleLocation);
			try {
				url = new URL(bundleLocation);
			} catch (Exception e) {
				// logger.error("Error creating inage url: " + e.toString());
			}
		}

		// if (logger.isDebugEnabled())
		// logger.debug("Image url: " + url);

		if (url == null) {
			try {
				url = new URL(urlPath);
				// if (logger.isDebugEnabled())
				// logger.debug("Image url (workaraound): " + url);
			} catch (MalformedURLException e) {
				return null;
			}
		}

		return url;
		
	}

}
