package org.protope.designer.wizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;
import org.protope.designer.base.model.BaseDiagram;
import org.protope.designer.base.model.DiagramType;
import org.protope.designer.base.model.UIDiagram;
import org.protope.designer.i18n.ProtopeMessages;
import org.protope.designer.utils.LoadSaveUtils;

public class NewDiagramWizardPage1 extends WizardNewFileCreationPage implements
		SelectionListener {

	private final IWorkbench workbench;

	/** count of files created, used for naming */
	private static int fileCount = 1;

	private Button uiPrototypeButton = null;

	private DiagramType modelSelected;

	public NewDiagramWizardPage1(IWorkbench workbench,
			IStructuredSelection selection) {
		super("prototypePage1", selection); //$NON-NLS-1$
		this.setTitle(ProtopeMessages.CreatePrototypePage1_Title);
		this.setDescription(ProtopeMessages.CreatePrototypePage1_Description);
		this.setImageDescriptor(ImageDescriptor.createFromFile(getClass(),
				"icons/banner.gif")); //$NON-NLS-1$
		this.workbench = workbench;
	}

	public void createControl(Composite parent) {
		super.createControl(parent);
		setFileName("diagram" + fileCount + ".ui"); //$NON-NLS-2$//$NON-NLS-1$

		Composite composite = (Composite) getControl();

		// sample section generation group
		Group group = new Group(composite, SWT.NONE);
		group.setLayout(new GridLayout());
		group
				.setText(ProtopeMessages.CreatePrototypePage1_ModelNames_GroupName);
		group.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));

		// sample section generation checkboxes
		uiPrototypeButton = new Button(group, SWT.RADIO);
		uiPrototypeButton
				.setText(ProtopeMessages.CreatePrototypePage1_ModelNames_UIModelName);
		uiPrototypeButton.addSelectionListener(this);
		uiPrototypeButton.setSelection(true);

		new Label(composite, SWT.NONE);

		setPageComplete(validatePage());
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

	/**
	 * Empty method
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	/**
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == uiPrototypeButton) {
			modelSelected = DiagramType.UI;
			setFileName("diagram" + fileCount + ".ui"); //$NON-NLS-2$//$NON-NLS-1$
		}
	}

	public boolean finish() {
		IFile newFile = createNewFile();
		if (newFile == null)
			return false; // ie.- creation was unsuccessful

		// Since the file resource was created fine, open it for editing
		// if requested by the user
		try {
			IWorkbenchWindow dwindow = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage page = dwindow.getActivePage();
			if (page != null)
				IDE.openEditor(page, newFile, true);
		} catch (org.eclipse.ui.PartInitException e) {
			e.printStackTrace();
			return false;
		}
		fileCount++;
		return true;
	}

	public DiagramType getModelSelected() {
		return modelSelected;
	}
	
}
