package org.protope.designer.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.protope.designer.base.model.BaseDiagram;

public class LoadSaveUtils {

	public static void writeToFile(BaseDiagram diagram, IFile file, IProgressMonitor monitor) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		writeToOutputStream(diagram, out);
		file.setContents(new ByteArrayInputStream(out.toByteArray()), true,
				false, monitor);
	}

	public static void writeToOutputStream(BaseDiagram diagram, OutputStream os) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(diagram);
		oos.close();
	}

	public static BaseDiagram readFromFile(IFile file) throws Exception {
		InputStream is = file.getContents(false);
		return readFromInputStream(is);
	}

	public static BaseDiagram readFromInputStream(InputStream is) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(is);
		Object o = ois.readObject();
		ois.close();
		BaseDiagram diag = (BaseDiagram) o;
		return diag;
	}

}
