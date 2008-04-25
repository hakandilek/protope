package org.protope.designer.wizard;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.protope.designer.export.ImageExporter;
import org.protope.designer.utils.GEFUtils;
import org.protope.designer.utils.PluginUtils;
import org.protope.designer.utils.StatusUtils;


public class ExportImageOperation implements IRunnableWithProgress
{
    /** Edit parts to export. */
    private List<GraphicalEditPart> editParts;
    /** Image exporter descriptor for all selected diagrams. */
    private ImageExporter exporter;
    /** The image file to create. */
    private File imageFile;
    /** Whether to turn on anti-aliasing. */
    private boolean antialiasing;
    /** Object used to ask the user what to do when an image file already exists. */
    private IOverwriteQuery overwriteCallback;


    public ExportImageOperation(List<GraphicalEditPart> editParts,
            ImageExporter exporter, File imageFile, boolean antialiasing, IOverwriteQuery overwriteImplementor)
    {
        super();
        this.editParts = editParts;
        this.exporter = exporter;
        this.imageFile = imageFile;
        this.antialiasing = antialiasing;
        overwriteCallback = overwriteImplementor;
    }


    public void run(IProgressMonitor monitor) throws InterruptedException
    {
        try
        {
            monitor.beginTask("Exporting image...", editParts.size());
            exportDiagram(monitor);
        }
        finally
        {
            monitor.done();
        }
    }

    protected void exportDiagram(IProgressMonitor monitor) throws InterruptedException
    {
        monitor.beginTask("Exporting diagrams...", editParts.size());

        if (imageFile.exists())
        {
            if (!imageFile.canWrite())
            {
            	PluginUtils.log(StatusUtils.newStatus(IStatus.ERROR, "Image file \"" + imageFile.getAbsolutePath() + "\" is not writable", null));
                return;
            }

            String overwriteAnswer = overwriteCallback.queryOverwrite(imageFile.getAbsolutePath());

            if (overwriteAnswer.equals(IOverwriteQuery.CANCEL))
            {
                throw new InterruptedException();
            }
            else if (overwriteAnswer.equals(IOverwriteQuery.NO))
            {
                return;
            }
            else if (overwriteAnswer.equals(IOverwriteQuery.NO_ALL))
            {
                return;
            }
        }
        exportImage();
        try
        {
            IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
            for (IContainer container : workspaceRoot.findContainersForLocation(new Path(imageFile.getParent())))
            {
                container.refreshLocal(IResource.DEPTH_ONE, monitor);
            }
        }
        catch (Exception e)
        {
            // ignore
        	PluginUtils.log(StatusUtils.newStatus(IStatus.WARNING, "Error while refreshing.", e));
        }
    }


    private void exportImage()
    {
        try
        {	
        	final Image image = GEFUtils.createImage(editParts, null, true, antialiasing);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            exporter.export(image, outputStream);
            outputStream.close();
        }
        catch (Throwable e)
        {
        	PluginUtils.log(StatusUtils.newStatus(IStatus.ERROR, "Error while exporting.", e));
        }
    }

}



