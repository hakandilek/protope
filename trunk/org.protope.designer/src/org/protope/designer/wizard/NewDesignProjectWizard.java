package org.protope.designer.wizard;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.ide.undo.CreateProjectOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.statushandlers.StatusAdapter;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.protope.designer.i18n.ProtopeMessages;
import org.protope.designer.utils.PluginUtils;
import org.protope.designer.utils.StatusUtils;

public class NewDesignProjectWizard extends BasicNewProjectResourceWizard {

	private WizardNewProjectCreationPage newProjectPage;

	private NewDiagramWizardPage1 newDiagramPage;

	private IProject newProject;

	@Override
	public void addPages() {
		IWorkbench workbench = getWorkbench();
		IStructuredSelection selection = getSelection();

		newProjectPage = new NewDesignProjectWizardPage1();//$NON-NLS-1$
		newProjectPage.setTitle(ProtopeMessages.NewDesignProjectWizard_title);
		newProjectPage
				.setDescription(ProtopeMessages.NewDesignProjectWizard_description);
		addPage(newProjectPage);

		newDiagramPage = new NewDiagramWizardPage1(workbench, selection);
		addPage(newDiagramPage);
	}

	@Override
	public boolean performFinish() {
		return newDiagramPage.finish();
	}

	/**
	 * Creates a new project resource with the selected name.
	 * <p>
	 * In normal usage, this method is invoked after the user has pressed Finish
	 * on the wizard; the enablement of the Finish button implies that all
	 * controls on the pages currently contain valid values.
	 * </p>
	 * <p>
	 * Note that this wizard caches the new project once it has been
	 * successfully created; subsequent invocations of this method will answer
	 * the same project resource without attempting to create it again.
	 * </p>
	 * 
	 * @return the created project resource, or <code>null</code> if the
	 *         project was not created
	 */
	private IProject createNewProject() {
		if (newProject != null) {
			return newProject;
		}

		// get a project handle
		final IProject newProjectHandle = newProjectPage.getProjectHandle();

		// get a project descriptor
		URI location = null;
		if (!newProjectPage.useDefaults()) {
			location = newProjectPage.getLocationURI();
		}

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		String name = newProjectHandle.getName();
		final IProjectDescription description = workspace
				.newProjectDescription(name);
		description.setLocationURI(location);

		// create the new project operation
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				CreateProjectOperation op = new CreateProjectOperation(
						description,
						ProtopeMessages.NewDesignProjectWizard_windowTitle);
				try {
					PlatformUI.getWorkbench().getOperationSupport()
							.getOperationHistory().execute(
									op,
									monitor,
									WorkspaceUndoUtil
											.getUIInfoAdapter(getShell()));
				} catch (ExecutionException e) {
					throw new InvocationTargetException(e);
				}
			}
		};

		// run the new project creation operation
		try {
			getContainer().run(true, true, op);
		} catch (InterruptedException e) {
			return null;
		} catch (InvocationTargetException e) {
			Throwable t = e.getTargetException();
			if (t instanceof ExecutionException
					&& t.getCause() instanceof CoreException) {
				CoreException cause = (CoreException) t.getCause();
				StatusAdapter status;
				if (cause.getStatus().getCode() == IResourceStatus.CASE_VARIANT_EXISTS) {
					String msg = NLS
							.bind(
									ProtopeMessages.NewDesignProjectWizard_caseVariantExistsError,
									name);
					status = new StatusAdapter(StatusUtils.newStatus(
							IStatus.WARNING, msg, cause));
				} else {
					int severity = cause.getStatus().getSeverity();
					IStatus s = StatusUtils
							.newStatus(
									severity,
									ProtopeMessages.NewDesignProjectWizard_errorMessage,
									cause);
					status = new StatusAdapter(s);
				}
				status.setProperty(StatusAdapter.TITLE_PROPERTY,
						ProtopeMessages.NewDesignProjectWizard_errorMessage);
				StatusManager.getManager().handle(status, StatusManager.BLOCK);
			} else {
				StatusAdapter status = new StatusAdapter(
						new Status(
								IStatus.WARNING,
								PluginUtils.IDE_WORKBENCH,
								0,
								NLS
										.bind(
												ProtopeMessages.NewDesignProjectWizard_internalError,
												t.getMessage()), t));
				status.setProperty(StatusAdapter.TITLE_PROPERTY,
						ProtopeMessages.NewDesignProjectWizard_errorMessage);
				StatusManager.getManager().handle(status,
						StatusManager.LOG | StatusManager.BLOCK);
			}
			return null;
		}

		newProject = newProjectHandle;

		return newProject;
	}

	/**
	 * Returns the newly created project.
	 * 
	 * @return the created project, or <code>null</code> if project not
	 *         created
	 */
	public IProject getNewProject() {
		return newProject;
	}

	void switchNewDiagramPage() {
		if (newProject == null) {
			createNewProject();

			if (newProject == null) {
				return;
			}

			updatePerspective();
			selectAndReveal(newProject);

			newDiagramPage.setContainerFullPath(newProject.getFullPath());
		}
	}

}
