package org.protope.designer.base.actions;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.ui.wizards.IWizardRegistry;
import org.protope.designer.base.editor.DiagramEditor;
import org.protope.designer.utils.PluginUtils;

/**
 * Abstract handler for commands that launch the import, export and new wizards.
 * <p>
 * This class is only intended to be extended by the three inner classes (<code>Export</code>,
 * <code>Import</code> and <code>New</code>) defined here.
 * </p>
 * 
 * @since 3.2
 */
public abstract class WizardAction extends Action {

	/**
	 * Returns an <code>IAction</code> that opens a dialog to allow the user
	 * to choose a wizard.
	 * 
	 * @param window
	 *            The workbench window to use when constructing the action.
	 * @return An <code>IAction</code> that opens a dialog to allow the user
	 *         to choose a wizard.
	 */
	protected abstract IAction createWizardChooserDialogAction();

	@Override
	public void run() {
		String wizardId = getWizardIdParameterId();

		try {
			if (wizardId == null) {
				IAction wizardAction = createWizardChooserDialogAction();
				wizardAction.run();
			} else {
				IWizardRegistry wizardRegistry = getWizardRegistry();
				IWizardDescriptor wizardDescriptor = wizardRegistry
						.findWizard(wizardId);
				if (wizardDescriptor == null) {
					throw new ExecutionException("unknown wizard: " + wizardId); //$NON-NLS-1$
				}

				try {
					IWorkbenchWizard wizard = wizardDescriptor.createWizard();
					final IWorkbenchWindow wbWindow = PluginUtils
							.getActiveWorkbenchWindow();
					final IWorkbenchPage activePage = wbWindow.getActivePage();
					DiagramEditor editor = (DiagramEditor) activePage
							.getActiveEditor();
					final ISelection sel = editor.getSelection();
					IStructuredSelection selection = null;
					if (sel instanceof StructuredSelection) {
						selection = (StructuredSelection) sel;
					}
					if (selection == null)
						selection = StructuredSelection.EMPTY;
					wizard.init(PlatformUI.getWorkbench(), selection);

					if (wizardDescriptor.canFinishEarly()
							&& !wizardDescriptor.hasPages()) {
						wizard.performFinish();
						return;
					}

					Shell parent = PluginUtils.getActiveWorkbenchShell();
					WizardDialog dialog = new WizardDialog(parent, wizard);
					dialog.create();
					dialog.open();

				} catch (CoreException ex) {
					throw new ExecutionException("error creating wizard", ex); //$NON-NLS-1$
				}
			}
		} catch (ExecutionException e) {
			PluginUtils.log(e);
		}
	}

	/**
	 * Returns the id of the parameter used to indicate which wizard this
	 * command should launch.
	 * 
	 * @return The id of the parameter used to indicate which wizard this
	 *         command should launch.
	 */
	protected abstract String getWizardIdParameterId();

	/**
	 * Returns the wizard registry for the concrete <code>WizardHandler</code>
	 * implementation class.
	 * 
	 * @return The wizard registry for the concrete <code>WizardHandler</code>
	 *         implementation class.
	 */
	protected abstract IWizardRegistry getWizardRegistry();

}
