package org.protope.designer.export;

import java.io.OutputStream;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

public class SWTImageExporter implements ImageExporter {

	private final String name;
	private final int swtFormat;
	private final String extension;

	public SWTImageExporter(String name, int swtFormat, String extension) {
		super();
		this.name = name;
		this.swtFormat = swtFormat;
		this.extension = extension;
	}

	@Override
	public String getFileExtension() {
		return extension;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void export(Image image, OutputStream outputStream) {
		final ImageLoader imageLoader = new ImageLoader();
		final int format = getSWTFormat();

		imageLoader.data = new ImageData[] { image.getImageData() };
		imageLoader.save(outputStream, format);
		image.dispose();
	}

	protected int getSWTFormat() {
		return swtFormat;
	}

	@Override
	public String toString() {
		return getName();
	}

}
