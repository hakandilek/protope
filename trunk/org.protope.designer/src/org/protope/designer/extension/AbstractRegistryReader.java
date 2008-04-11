package org.protope.designer.extension;

import javax.naming.ConfigurationException;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public abstract class AbstractRegistryReader {

	public void readRegistry(String pluginID, String extensionPoint) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry != null) {
			IExtensionPoint point = registry.getExtensionPoint(pluginID,
					extensionPoint);
			if (point != null) {
				IExtension[] extensions = point.getExtensions();
				// extensions = orderExtensions(extensions);
				for (int i = 0; i < extensions.length; i++) {
					IExtension extension = extensions[i];
					readExtension(extension);
				}
			}
		}
	}

	private void readExtension(IExtension extension) {
        IConfigurationElement[] elements = extension.getConfigurationElements();
        for (IConfigurationElement element : elements) {
            if (!readElement(element))
                logUnknownElement(element);
		}
	}

    /**
     * Reads a single configuration element
     * 
     * @param element
     *            the element
     * @return true if element is successfully read
     * @throws ConfigurationException on any configuration error
     */
    protected abstract boolean readElement(IConfigurationElement element);

    /**
     * Logs the error in the workbench log using the provided text and the
     * information in the configuration element.
     * 
     * @param element
     *            a single element
     * @param text
     *            error message
     * 
     */
    protected void logError(IConfigurationElement element, String text)
    {
        IExtension extension = element.getDeclaringExtension();
        StringBuffer buf = new StringBuffer();
        buf.append("Plugin ");
        buf.append(extension.getNamespaceIdentifier());
        buf.append(", extension ");
        buf.append(extension.getExtensionPointUniqueIdentifier());
        buf.append("\n");
        buf.append(text);
        System.err.println(buf);
    }

    /**
     * Logs a very common registry error when a required attribute is missing.
     * 
     * @param element
     *            the configuration element
     * @param attributeName
     *            the name of the attribute
     */
    protected void logMissingAttribute(IConfigurationElement element, String attributeName)
    {
        logError(element, "Required attribute '" + attributeName + "' not defined");
    }

    /**
     * Logs a very common registry error when a required child is missing.
     * 
     * @param element
     *            the configuration element
     * @param elementName
     *            the name of the attribute
     */
    protected void logMissingElement(IConfigurationElement element, String elementName)
    {
        logError(element, "Required sub element '" + elementName + "' not defined"); //$NON-NLS-2$//$NON-NLS-1$
    }

    /**
     * Logs a registry error when the configuration element is unknown.
     * 
     * @param element
     *            the configuration element
     */
    protected void logUnknownElement(IConfigurationElement element)
    {
        logError(element, "Unknown extension tag found: " + element.getName()); //$NON-NLS-1$
    }

}
