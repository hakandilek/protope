package org.protope.designer.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class PrototypeCreationWizard extends Wizard implements INewWizard {

	private IStructuredSelection selection;
	private IWorkbench workbench;

	private PrototypeWizardPage1 page1;

	public PrototypeCreationWizard() {
	}

	@Override
	public boolean performFinish() {
		return page1.finish();
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
	}

	@Override
	public void addPages() {
		page1 = new PrototypeWizardPage1(workbench, selection);
		addPage(page1);
	}

}
