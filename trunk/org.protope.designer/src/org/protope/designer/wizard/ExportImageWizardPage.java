package org.protope.designer.wizard;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.WizardDataTransferPage;
import org.protope.designer.export.ImageExporter;
import org.protope.designer.export.ImageExporterFactory;
import org.protope.designer.export.ImagePreview;

/**
 * Export image wizard page.
 * 
 * @see ExportImageWizard
 * 
 * <pre>
 * Original code is retrieved from Image-Export Plugin from:
 * http://janus-plugin.sourceforge.net/ which is owned by
 *   Thomas Maier
 *   University of Kassel
 *   Research Group Software Engineering
 *   Wilhelmshoeher Allee 73
 *   34121 Kassel
 *   Germany
 * </pre>
 */
public class ExportImageWizardPage extends WizardDataTransferPage
{
    /* image format combo */
    private ComboViewer imageFormatCombo;
    
    /* Output file name text field*/
    private Combo outputFileNameCombo;
    
    /* Selected Edit parts  */
    private List<GraphicalEditPart> selectedParts;

    /* image preview component */
    private ImagePreview imagePreview;

    public ExportImageWizardPage( List<GraphicalEditPart> selectedEditParts)
    {
        super("exportDiagramImageWizardPage");
        setTitle("Specify the image format.");
        setDescription("Select the format of the image.");
        this.selectedParts = selectedEditParts;
    }

    @Override
    protected boolean allowNewContainerName()
    {
        return false;
    }


    public void createControl(Composite parent)
    {
        final Composite composite = new Composite(parent, SWT.NULL);
        composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        layout.verticalSpacing = 9;
        composite.setLayout(layout);


        /*
         * Preview
         */

        imagePreview = new ImagePreview(selectedParts);
        Control previewControl = imagePreview.createControl(composite);
        GridData previewGridData = new GridData(SWT.FILL, SWT.FILL, true, true, layout.numColumns, 1);
        previewControl.setLayoutData(previewGridData);


        /*
         * Image format.
         */

        Label imageFormatLabel = new Label(composite, SWT.NULL);
        imageFormatLabel.setText("&Image format:");
        imageFormatLabel.setToolTipText("Choose the format of the diagram images.");

        imageFormatCombo = new ComboViewer(composite, SWT.READ_ONLY);
        imageFormatCombo.setContentProvider(new ArrayContentProvider());
        List<ImageExporter> imageExporters = ImageExporterFactory.getINSTANCE().getImageExporters();
        imageFormatCombo.setInput(imageExporters);
        imageFormatCombo.setSelection(new StructuredSelection(ImageExporterFactory.getINSTANCE().getDefaultImageExporter()));
        imageFormatCombo.getControl().setFocus();
        imageFormatCombo.getControl().setToolTipText(imageFormatLabel.getToolTipText());
        imageFormatCombo.setLabelProvider(new LabelProvider());

        GridData imageFormatComboViewerGridData = new GridData(0, 0, false, false, layout.numColumns-1, 1);
        imageFormatCombo.getControl().setLayoutData(imageFormatComboViewerGridData);

        imageFormatCombo.addSelectionChangedListener(new ISelectionChangedListener() {
            private ImageExporter oldExporter = getImageExporter();
            @SuppressWarnings("synthetic-access")
            public void selectionChanged(@SuppressWarnings("unused") SelectionChangedEvent event)
            {
                /*
                 * Change file extension to newly selected image format's one but only if the file
                 * extension is the one from the image format selected previously. If the user has
                 * changed (e.g. removed) the file extension, we do not annoy her appending another
                 * one when changing the format.
                 */
                IPath outputPath = Path.fromOSString(getOutputFileName());
                ImageExporter newExporter = getImageExporter();

                // TODO: rethink strategy regarding changing the filename
                if (outputPath.getFileExtension()!=null && outputPath.getFileExtension().equals(oldExporter.getFileExtension()))
                {
                    IPath newOutputPath = outputPath.removeFileExtension().addFileExtension(newExporter.getFileExtension());
                    outputFileNameCombo.setText(newOutputPath.toOSString());
                }
                oldExporter = newExporter;
                updatePageComplete();
            }
        });


        /*
         * Output.
         */

        Label outputFileNameLabel = new Label(composite, SWT.NULL);
        outputFileNameLabel.setText("&To file:");
        outputFileNameLabel.setToolTipText("Choose file where the exported image will be stored.");

        outputFileNameCombo = new Combo(composite, SWT.SINGLE | SWT.BORDER);
        outputFileNameCombo.setToolTipText(outputFileNameLabel.getToolTipText());
        outputFileNameCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        outputFileNameCombo.addModifyListener(new ModifyListener() {
            public void modifyText(@SuppressWarnings("unused") ModifyEvent e)
            {
                updatePageComplete();
            }
        });

        Button outputFileNameBrowseButton = new Button(composite, SWT.PUSH);
        outputFileNameBrowseButton.setText("Browse...");
        outputFileNameBrowseButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(@SuppressWarnings("unused") SelectionEvent e)
            {
                handleBrowse();
            }
        });


        setControl(composite);
        restoreWidgetValues();
    }


    protected void handleBrowse()
    {
        FileDialog dialog = new FileDialog(getContainer().getShell(), SWT.SAVE);
        dialog.setText("Choose file for exported image");
        File file = new File(outputFileNameCombo.getText());
        if (file.getParent() != null)
        {
            dialog.setFilterPath(file.getAbsolutePath());
        }
        String selectedFileName = dialog.open();
        if (selectedFileName != null)
        {
            outputFileNameCombo.setText(selectedFileName);
        }
        updatePageComplete();
    }


    /**
     * Update page completion status, i.e. whether Next/Finish can be selected.
     */
    void updatePageComplete()
    {
        if (getOutputFileName().length() > 0)
        {
            File outputFile = new File(getOutputFileName());
            if (outputFile.isDirectory())
            {
                this.setErrorMessage("â€œ" + outputFile + "â€? is a directory.");
                setPageComplete(false);
                return;
            }
            File parentDirectory = outputFile.getParentFile();
            if (parentDirectory != null)
            {
                if (!parentDirectory.exists())
                {
                    setErrorMessage("Directory â€œ" + parentDirectory + "â€? does not exist");
                    setPageComplete(false);
                    return;
                }
                if (!parentDirectory.canWrite())
                {
                    setErrorMessage("Directory â€œ" + parentDirectory + "â€? is not writable");
                    setPageComplete(false);
                    return;
                }
            }
        }
        setErrorMessage(null);
        setPageComplete(true);
    }

    public String getOutputFileName()
    {
        return outputFileNameCombo.getText();
    }

    protected void setOutputFileName(String outputFileName)
    {
        outputFileNameCombo.setText(outputFileName);
    }

    protected void addOutputFileNameHistoryItem(String outputFileName)
    {
        outputFileNameCombo.add(outputFileName);
    }

    /**
     * Return the image exporter the user selected for the exported images. The return
     * value is an implementation of <code>ImageExporter</code> and defines the format
     * of the exported images.
     *
     * @see ImageExporter
     *
     * @return the image exporter the user selected for the exported images
     */
    public ImageExporter getImageExporter()
    {
        assert imageFormatCombo.getSelection() instanceof IStructuredSelection;
        assert ((IStructuredSelection) imageFormatCombo.getSelection()).size() == 1;
        ImageExporter exporter =
            (ImageExporter) ((IStructuredSelection) imageFormatCombo.getSelection()).getFirstElement();
        return exporter;
    }

    public static ImageExporter getImageExporter(String name)
    {
        ImageExporter exporter = null;
        for (ImageExporter d : ImageExporterFactory.getINSTANCE().getImageExporters())
        {
            if (d.getName().equals(name))
            {
                exporter = d;
                break;
            }
        }
        return exporter;
    }

    protected void selectImageExporter(String name)
    {
        ImageExporter exporter = ExportImageWizardPage.getImageExporter(name);
        /*
         * The wizard page stores the image format that the user selected the last time. When the
         * wizard starts, that format is selected. If, however, the plug in that provides that
         * format fails to load or was deleted by the user, exporter is null.
         */
        if (exporter!=null)
        {
            imageFormatCombo.setSelection(new StructuredSelection(exporter));
        }
        else
        {
            // TODO: set Selection to "default" format (which updates the filename).
        }
    }

    public List<GraphicalEditPart> getEditPartsToExport()
    {
        return imagePreview.getEditPartsToExport();
    }

    public boolean isAntialiasing()
    {
        return imagePreview.isAntialiasing();
    }

    public void handleEvent(Event event)
    {
        throw new RuntimeException("FIXME: does anything register this as a listener? event=" + event);
    }

    public void finish()
    {
        saveWidgetValues();
    }
}
