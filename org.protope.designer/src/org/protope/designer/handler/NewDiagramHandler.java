package org.protope.designer.handler;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.NewWizardAction;
import org.eclipse.ui.wizards.IWizardRegistry;

public class NewDiagramHandler extends WizardHandler {

	@Override
	protected IAction createWizardChooserDialogAction(IWorkbenchWindow window) {
		return new NewWizardAction(window);
	}

	@Override
	protected String getWizardIdParameterId() {
		return "org.protope.designer.wizard.new.diagram"; //$NON-NLS-1$
	}

	@Override
	protected IWizardRegistry getWizardRegistry() {
		return PlatformUI.getWorkbench().getNewWizardRegistry();
	}

}
