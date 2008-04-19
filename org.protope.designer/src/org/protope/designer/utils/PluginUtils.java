package org.protope.designer.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Collections;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.protope.designer.i18n.ProtopeMessages;
import org.protope.designer.plugin.ProtopeDesignerPlugin;
import org.protope.designer.plugin.ProtopeStatusConstants;

public class PluginUtils {

	/** the prefix of OSGi bundle locations */
	public static final String BUNDLE_LOCATION_PREFIX = "initial@reference:";
	public static final String IDE_WORKBENCH = "org.eclipse.ui.ide";

	/**
	 * Checks if the bundle is ready for usage.
	 * 
	 * @param bundle
	 *            the bundle
	 * @return true if bundle is ready
	 */
	public static boolean isReady(Bundle bundle) {
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
	public static boolean isReady(int bundleState) {
		return (bundleState & (Bundle.RESOLVED | Bundle.STARTING
				| Bundle.ACTIVE | Bundle.STOPPING)) != 0;
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

	public static Shell getActiveWorkbenchShell() {
		IWorkbenchWindow window = getActiveWorkbenchWindow();
		if (window != null) {
			return window.getShell();
		}
		return null;
	}

	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		return getWorkbench().getActiveWorkbenchWindow();
	}

	/**
	 * Returns the Platform UI workbench.
	 * <p>
	 * This method exists as a convenience for plugin implementors. The
	 * workbench can also be accessed by invoking
	 * <code>PlatformUI.getWorkbench()</code>.
	 * </p>
	 * 
	 * @return IWorkbench the workbench for this plug-in
	 */
	public static IWorkbench getWorkbench() {
		return PlatformUI.getWorkbench();
	}

	public static void log(Throwable e) {
		log(new Status(IStatus.ERROR, getPluginId(),
				ProtopeStatusConstants.INTERNAL_ERROR,
				ProtopeMessages.Plugin_internal_error, e));
	}

	public static String getPluginId() {
		return ProtopeDesignerPlugin.PLUGIN_ID;
	}

	public static void log(IStatus status) {
		getLog().log(status);
	}

	/**
	 * Logs the given throwable to the platform log, indicating the class and
	 * method from where it is being logged (this is not necessarily where it
	 * occurred).
	 * 
	 * This convenience method is for internal use by the IDE Workbench only and
	 * must not be called outside the IDE Workbench.
	 * 
	 * @param clazz
	 *            The calling class.
	 * @param methodName
	 *            The calling method name.
	 * @param t
	 *            The throwable from where the problem actually occurred.
	 */
	public static void log(Class<?> clazz, String methodName, Throwable t) {
		String msg = MessageFormat.format("Exception in {0}.{1}: {2}", //$NON-NLS-1$
				new Object[] { clazz.getName(), methodName, t });
		log(msg, t);
	}

	/**
	 * Logs the given message and throwable to the platform log.
	 * 
	 * If you have a status object in hand call log(String, IStatus) instead.
	 * 
	 * This convenience method is for internal use by the IDE Workbench only and
	 * must not be called outside the IDE Workbench.
	 * 
	 * @param message
	 *            A high level UI message describing when the problem happened.
	 * @param t
	 *            The throwable from where the problem actually occurred.
	 */
	public static void log(String message, Throwable t) {
		IStatus status = StatusUtils.newStatus(IStatus.ERROR, message, t);
		log(message, status);
	}

	/**
	 * Logs the given message and status to the platform log.
	 * 
	 * This convenience method is for internal use by the IDE Workbench only and
	 * must not be called outside the IDE Workbench.
	 * 
	 * @param message
	 *            A high level UI message describing when the problem happened.
	 *            May be <code>null</code>.
	 * @param status
	 *            The status describing the problem. Must not be null.
	 */
	public static void log(String message, IStatus status) {

		// 1FTUHE0: ITPCORE:ALL - API - Status & logging - loss of semantic info

		if (message != null) {
			log(StatusUtils.newStatus(IStatus.ERROR, message, null));
		}

		log(status);
	}

	protected static ILog getLog() {
		return ProtopeDesignerPlugin.getDefault().getLog();
	}

	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

}
