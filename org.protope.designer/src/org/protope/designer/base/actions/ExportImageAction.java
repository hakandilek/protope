package org.protope.designer.base.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.NewWizardAction;
import org.eclipse.ui.wizards.IWizardRegistry;
import org.protope.designer.utils.PluginUtils;

public class ExportImageAction extends WizardAction {

	public static final String ID = "org.protope.designer.commands.exportImage";//$NON-NLS-1$

	
	public ExportImageAction() {
		super();
		setId(ID);
	}

	@Override
	protected IAction createWizardChooserDialogAction() {
		return new NewWizardAction(PluginUtils.getActiveWorkbenchWindow());
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
