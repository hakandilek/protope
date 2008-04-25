package org.protope.designer.wizard;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.protope.designer.export.ImageExporter;
import org.protope.designer.plugin.ProtopeDesignerPlugin;

/**
 * Image export wizard
 * 
 * <pre>
 * Original code is retrieved from Image-Export Plugin from:
 * http://janus-plugin.sourceforge.net/ which is owned by
 *   Thomas Maier
 *   University of Kassel
 *   Research Group Software Engineering
 *   Wilhelmshoeher Allee 73
 *   34121 Kassel
 *   Germany
 * </pre>
 */
public class ExportImageWizard extends Wizard implements IExportWizard {
	private IWorkbench workbench;
	private ExportImageWizardPage page;
	/** Edit parts selected by the user. */
	private List<GraphicalEditPart> editParts;
	/**
	 * Saved selection states of selectedEditParts (restored when wizard
	 * closes).
	 */
	private List<Integer> selectionStates;

	/**
	 * Create a new wizard to export images from GEF diagrams.
	 */
	public ExportImageWizard() {
		super();
		setNeedsProgressMonitor(true);
		setWindowTitle("Export Current Diagram as Image");
		ImageDescriptor pageImageDescriptor = AbstractUIPlugin
				.imageDescriptorFromPlugin(ProtopeDesignerPlugin.PLUGIN_ID,
						"src/org.protope.designer.wizard.icons/export_image_banner.gif");
		setDefaultPageImageDescriptor(pageImageDescriptor);

	}

	public void init(IWorkbench iworkbench, IStructuredSelection s) {
		workbench = iworkbench;
		editParts = new ArrayList<GraphicalEditPart>();

		final IWorkbenchWindow workbenchWindow = workbench
				.getActiveWorkbenchWindow();
		final IWorkbenchPage activePage = workbenchWindow.getActivePage();
		IEditorPart currentEditor = activePage.getActiveEditor();

		/*
		 * The wizard may be activated with a GEF editor active or inactive. It
		 * is common to be inactive if e.g. the user selects a file in the
		 * package explorer and then choses File > Export. If a GEF editor is
		 * active, the current selection is either the "diagram edit part" or
		 * several edit parts. If a GEF editor is inactive, the current
		 * selection is empty because in plugin.xml I requested that the
		 * selection consists of GraphicalEditParts only.
		 */
		if (!s.isEmpty()) {
			for (Object selectedObject : s.toList()) {
				editParts.add((GraphicalEditPart) selectedObject);
			}
		} else {
			GraphicalEditPart rootEditPart = (GraphicalEditPart) currentEditor
					.getAdapter(EditPart.class);
			editParts.add((GraphicalEditPart) rootEditPart.getViewer()
					.getContents());
		}
		unselectEditParts();
	}

	public void addPages() {
		page = new ExportImageWizardPage(editParts);
		addPage(page);
	}

	private void unselectEditParts() {
		selectionStates = new ArrayList<Integer>(editParts.size());
		for (GraphicalEditPart editPart : editParts) {
			selectionStates.add(editPart.getSelected());
			editPart.setSelected(EditPart.SELECTED_NONE);
		}
	}

	private void reselectEditParts() {
		for (int i = 0; i < editParts.size(); ++i) {
			editParts.get(i).setSelected(selectionStates.get(i));
		}
	}

	@Override
	public boolean performCancel() {
		reselectEditParts();
		return super.performCancel();
	}

	public boolean performFinish() {
		boolean finished = true;
		try {
			List<GraphicalEditPart> editPartsToExport = page
					.getEditPartsToExport();
			page.finish();
			final ImageExporter exporter = page.getImageExporter();
			final String ext = "." + exporter.getFileExtension();
			String filename = page.getOutputFileName();
			if (!filename.endsWith(ext))
				filename += ext;
			final File file = new File(filename);
			final ExportImageOperation op = new ExportImageOperation(
					editPartsToExport, exporter, file, page.isAntialiasing(),
					page);
			finished = executeExportOperation(op);
		} catch (InterruptedException e) {
			finished = false;
		} catch (InvocationTargetException e) {
			MessageDialog.openError(getShell(), "Error", e.toString() + "("
					+ e.getCause().getMessage() + ")");
			finished = false;
		} finally {
			reselectEditParts();
		}
		return finished;
	}

	protected boolean executeExportOperation(ExportImageOperation operation)
			throws InvocationTargetException, InterruptedException {
		try {
			getContainer().run(false, true, operation);
			return true;
		} catch (RuntimeException e) {
			return false;
		}
	}
}
