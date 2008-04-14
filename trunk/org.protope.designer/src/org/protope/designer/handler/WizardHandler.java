package org.protope.designer.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.ui.wizards.IWizardRegistry;

/**
 * Abstract handler for commands that launch the import, export and new wizards.
 * <p>
 * This class is only intended to be extended by the three inner classes (<code>Export</code>,
 * <code>Import</code> and <code>New</code>) defined here.
 * </p>
 * 
 * @since 3.2
 */
public abstract class WizardHandler extends AbstractHandler {

	/**
	 * Returns an <code>IAction</code> that opens a dialog to allow the user
	 * to choose a wizard.
	 * 
	 * @param window
	 *            The workbench window to use when constructing the action.
	 * @return An <code>IAction</code> that opens a dialog to allow the user
	 *         to choose a wizard.
	 */
	protected abstract IAction createWizardChooserDialogAction(
			IWorkbenchWindow window);

	public Object execute(ExecutionEvent event) throws ExecutionException {

		String wizardId = getWizardIdParameterId();

		IWorkbenchWindow activeWindow = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);

		if (wizardId == null) {
			IAction wizardAction = createWizardChooserDialogAction(activeWindow);
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
				wizard.init(PlatformUI.getWorkbench(),
						StructuredSelection.EMPTY);

				if (wizardDescriptor.canFinishEarly()
						&& !wizardDescriptor.hasPages()) {
					wizard.performFinish();
					return null;
				}

				Shell parent = activeWindow.getShell();
				WizardDialog dialog = new WizardDialog(parent, wizard);
				dialog.create();
				dialog.open();

			} catch (CoreException ex) {
				throw new ExecutionException("error creating wizard", ex); //$NON-NLS-1$
			}

		}

		return null;
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
