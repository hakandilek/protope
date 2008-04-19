package org.protope.designer.wizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.undo.CreateFileOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.operations.IWorkbenchOperationSupport;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.protope.designer.base.model.BaseDiagram;
import org.protope.designer.base.model.UIDiagram;
import org.protope.designer.i18n.ProtopeMessages;
import org.protope.designer.utils.LoadSaveUtils;
import org.protope.designer.utils.PluginUtils;

public class NewDesignProjectWizard extends BasicNewProjectResourceWizard {

	@Override
	public boolean performFinish() {
		boolean res = super.performFinish();
		if (res) {
			IProject pr = getNewProject();
			if (pr == null) {
				res = false;
			}

			IFile newFile = createNewFile();
			if (newFile == null)
				return false; // ie.- creation was unsuccessful

			// Since the file resource was created fine, open it for editing
			// if requested by the user
			try {
				IWorkbench wb = getWorkbench();
				IWorkbenchWindow dwindow = wb.getActiveWorkbenchWindow();
				IWorkbenchPage page = dwindow.getActivePage();
				if (page != null)
					IDE.openEditor(page, newFile, true);
			} catch (org.eclipse.ui.PartInitException e) {
				e.printStackTrace();
				return false;
			}
			return true;

		}
		return res;
	}

	protected IFile createNewFile() {

		// create the new file and cache it if successful
		final IPath containerPath = getNewProject().getFullPath();
		IPath newFilePath = containerPath.append("diagram1.ui");
		final IFile newFileHandle = createFileHandle(newFilePath);
		final InputStream initialContents = getInitialContents();

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) {
				CreateFileOperation op = new CreateFileOperation(newFileHandle,
						null, initialContents,
						ProtopeMessages.NewDesignProjectWizard_title);
				try {
					IWorkbench wb = getWorkbench();
					IWorkbenchOperationSupport os = wb.getOperationSupport();
					IOperationHistory his = os.getOperationHistory();
					Shell sh = getShell();
					IAdaptable info = WorkspaceUndoUtil.getUIInfoAdapter(sh);
					his.execute(op, monitor, info);
				} catch (final ExecutionException e) {
					final IWizardContainer con = getContainer();
					final Shell sh = con.getShell();
					sh.getDisplay().syncExec(new Runnable() {
						public void run() {
							final Throwable cause = e.getCause();
							if (cause instanceof CoreException) {
								ErrorDialog
										.openError(
												sh, // Was
												// Utilities.getFocusShell()
												ProtopeMessages.NewDesignProjectWizard_errorTitle,
												null, // no special
												// message
												((CoreException) cause)
														.getStatus());
							} else {
								PluginUtils.log(getClass(),
										"createNewFile()", cause); //$NON-NLS-1$
								MessageDialog
										.openError(
												sh,
												ProtopeMessages.NewDesignProjectWizard_internalErrorTitle,
												NLS
														.bind(
																ProtopeMessages.NewDesignProjectWizard_internalError,
																cause
																		.getMessage()));
							}
						}
					});
				}
			}
		};
		try {
			getContainer().run(true, true, op);
		} catch (InterruptedException e) {
			return null;
		} catch (InvocationTargetException e) {
			// Execution Exceptions are handled above but we may still get
			// unexpected runtime errors.
			PluginUtils.log(getClass(),
					"createNewFile()", e.getTargetException()); //$NON-NLS-1$
			MessageDialog
					.openError(
							getContainer().getShell(),
							ProtopeMessages.NewDesignProjectWizard_internalErrorTitle,
							NLS
									.bind(
											ProtopeMessages.NewDesignProjectWizard_internalError,
											e.getTargetException().getMessage()));

			return null;
		}

		IFile newFile = newFileHandle;
		return newFile;
	}

	/**
	 * Creates a file resource handle for the file with the given workspace
	 * path. This method does not create the file resource; this is the
	 * responsibility of <code>createFile</code>.
	 * 
	 * @param filePath
	 *            the path of the file resource to create a handle for
	 * @return the new file resource handle
	 * @see #createFile
	 */
	protected IFile createFileHandle(IPath filePath) {
		final IWorkspace workspace = PluginUtils.getWorkspace();
		final IWorkspaceRoot root = workspace.getRoot();
		return root.getFile(filePath);
	}

	protected InputStream getInitialContents() {
		BaseDiagram diagram = createDiagram();
		ByteArrayInputStream bais = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			LoadSaveUtils.writeToOutputStream(diagram, os);
			bais = new ByteArrayInputStream(os.toByteArray());
			bais.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bais;
	}

	protected BaseDiagram createDiagram() {
		return new UIDiagram();
	}

}
