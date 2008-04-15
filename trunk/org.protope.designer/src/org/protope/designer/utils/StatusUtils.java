package org.protope.designer.utils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class StatusUtils {

	public static IStatus createError(int code, Throwable throwable) {
		String message= throwable.getMessage();
		if (message == null) {
			message= throwable.getClass().getName();
		}
		return new Status(IStatus.ERROR, PluginUtils.getPluginId(), code, message, throwable);
	}

	public static IStatus createError(int code, String message, Throwable throwable) {
		return new Status(IStatus.ERROR, PluginUtils.getPluginId(), code, message, throwable);
	}
	
	public static IStatus createWarning(int code, String message, Throwable throwable) {
		return new Status(IStatus.WARNING, PluginUtils.getPluginId(), code, message, throwable);
	}

    /**
     * This method must not be called outside the workbench.
     *
     * Utility method for creating status.
     */
    public static IStatus newStatus(int severity, String message,
            Throwable exception) {

        String statusMessage = message;
        if (message == null || message.trim().length() == 0) {
            if (exception == null) {
                throw new IllegalArgumentException();
            } else if (exception.getMessage() == null) {
				statusMessage = exception.toString();
			} else {
				statusMessage = exception.getMessage();
			}
        }

        return new Status(severity, PluginUtils.getPluginId(), severity,
                statusMessage, exception);
    }


}
