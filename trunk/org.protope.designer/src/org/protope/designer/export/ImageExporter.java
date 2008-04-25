package org.protope.designer.export;

import java.io.OutputStream;

import org.eclipse.swt.graphics.Image;

public interface ImageExporter {

	String getName();

	String getFileExtension();

	void export(Image image, OutputStream outputStream);

}
