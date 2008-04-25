package org.protope.designer.export;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.protope.designer.i18n.ProtopeMessages;

public class ImageExporterFactory {

	protected static ImageExporter pngExporter = new SWTImageExporter(
			ProtopeMessages.ImageExporter_PNG, SWT.IMAGE_PNG, "png");
	protected static ImageExporter jpgExporter = new SWTImageExporter(
			ProtopeMessages.ImageExporter_JPEG, SWT.IMAGE_JPEG, "jpg");
	protected static ImageExporter gifExporter = new SWTImageExporter(
			ProtopeMessages.ImageExporter_BMP, SWT.IMAGE_BMP, "bmp");

	private final ArrayList<ImageExporter> exporters;

	protected static final ImageExporterFactory INSTANCE = new ImageExporterFactory();

	public static ImageExporterFactory getINSTANCE() {
		return INSTANCE;
	}

	public ImageExporterFactory() {
		super();
		exporters = new ArrayList<ImageExporter>();
		exporters.add(pngExporter);
		exporters.add(jpgExporter);
		exporters.add(gifExporter);
	}

	public List<ImageExporter> getImageExporters() {
		return exporters;
	}

	public ImageExporter getDefaultImageExporter() {
		return pngExporter;
	}

}
