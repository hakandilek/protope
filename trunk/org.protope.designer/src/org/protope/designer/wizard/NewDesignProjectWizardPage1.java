package org.protope.designer.wizard;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class NewDesignProjectWizardPage1 extends WizardNewProjectCreationPage {

	public NewDesignProjectWizardPage1() {
		super("basicNewProjectPage");
	}

	@Override
	public IWizardPage getNextPage() {
		IWizard w = getWizard();
		if (w != null && w instanceof NewDesignProjectWizard) {
			NewDesignProjectWizard wiz = (NewDesignProjectWizard) w;
			wiz.switchNewDiagramPage();
		}
		return super.getNextPage();
	}

	/**
	 * The <code>WizardPage</code> implementation of this
	 * <code>IWizardPage</code> method returns <code>true</code> if this
	 * page is complete (<code>isPageComplete</code>).
	 * 
	 * @see #getNextPage
	 */
	public boolean canFlipToNextPage() {
		return isPageComplete();
	}

}
