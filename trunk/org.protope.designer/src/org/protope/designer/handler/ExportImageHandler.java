package org.protope.designer.handler;

import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.NewWizardAction;
import org.eclipse.ui.wizards.IWizardRegistry;

public class ExportImageHandler extends WizardHandler implements IHandler {

	@Override
	protected IAction createWizardChooserDialogAction(IWorkbenchWindow window) {
		return new NewWizardAction(window);
	}

	@Override
	protected String getWizardIdParameterId() {
		return "org.protope.designer.wizard.exportImage"; //$NON-NLS-1$
	}

	@Override
	protected IWizardRegistry getWizardRegistry() {
		return PlatformUI.getWorkbench().getExportWizardRegistry();
	}

}
