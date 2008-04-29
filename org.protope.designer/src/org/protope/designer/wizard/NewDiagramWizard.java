package org.protope.designer.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class NewDiagramWizard extends Wizard implements INewWizard {

	private IStructuredSelection selection;
	private IWorkbench workbench;

	private NewDiagramWizardPage1 page1;

	public NewDiagramWizard() {
	}

	@Override
	public boolean performFinish() {
		return page1.finish();
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
	}

	@Override
	public void addPages() {
		page1 = new NewDiagramWizardPage1(workbench, selection);
		addPage(page1);
	}

}
