package org.protope.designer.export;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.protope.designer.utils.GEFUtils;


/**
 * Show a preview of a diagram or only the selected edit parts.
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
 * 
 */
public class ImagePreview implements PaintListener
{
    private Canvas previewCanvas;
    private Image previewImage;
    private List<GraphicalEditPart> selectedEditParts;
    private Dimension maxImageSize;
    private Button selectionOnlyCheckButton;
    /** Whether to turn on antialiasing. */
    private Button antialiasingCheckbox;

    public ImagePreview(List<GraphicalEditPart> selectedEditParts)
    {
        this.selectedEditParts = selectedEditParts;
    }

    public boolean isShowSelectionOnly()
    {
        return selectionOnlyCheckButton.getSelection();
    }

    public boolean isAntialiasing()
    {
        return antialiasingCheckbox.getSelection();
    }

    public void setAntialiasing(boolean antialiasing)
    {
        antialiasingCheckbox.setSelection(antialiasing);
    }


    public Control createControl(Composite parent)
    {
        Composite composite = new Composite(parent, SWT.NULL);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.verticalSpacing = 4;
        composite.setLayout(layout);

        Label previewLabel = new Label(composite, SWT.NULL);
        previewLabel.setText("Preview");
        previewLabel.setToolTipText("Preview of the generated image.");

        maxImageSize = new Dimension(
                        composite.getDisplay().getClientArea().width, composite.getDisplay().getClientArea().height);
        previewCanvas = new Canvas(composite, SWT.BORDER);
        previewCanvas.setToolTipText("Preview of the generated image.");
        previewCanvas.addPaintListener(this);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, layout.numColumns, 1);
        gridData.minimumWidth = 100;
        gridData.minimumHeight = 100;
        previewCanvas.setLayoutData(gridData);


        selectionOnlyCheckButton = new Button(composite, SWT.CHECK);
        selectionOnlyCheckButton.setText("&Selection only");
        selectionOnlyCheckButton.setToolTipText("Export only the current selection.");
        selectionOnlyCheckButton.setSelection(!GEFUtils.isWholeDiagramSelected(selectedEditParts));
        selectionOnlyCheckButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(@SuppressWarnings("unused") SelectionEvent e)
            {
                update();
            }
        });
        selectionOnlyCheckButton.setEnabled(!GEFUtils.isWholeDiagramSelected(selectedEditParts));

        antialiasingCheckbox = new Button(composite, SWT.CHECK | SWT.LEFT);
        antialiasingCheckbox.setText("&Antialiasing");
        antialiasingCheckbox.setToolTipText("When checked, use antialiasing when creating the output.");
        antialiasingCheckbox.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(@SuppressWarnings("unused") SelectionEvent e)
            {
                update();
            }
        });

        update();

        return composite;
    }

    public List<GraphicalEditPart> getEditPartsToExport()
    {
        if (isShowSelectionOnly())
        {
            return selectedEditParts;
        }
        else
        {
            return Collections.singletonList((GraphicalEditPart) selectedEditParts.get(0).getRoot().getContents());
        }
    }

    public void update()
    {
        previewImage = generatePreviewImage(maxImageSize);
        previewCanvas.redraw();
    }

    public void paintControl (PaintEvent e)
    {
        Dimension imageSize = new Rectangle(previewImage.getBounds()).getSize();
        Dimension canvasSize = new Dimension(previewCanvas.getSize());
        Dimension size;
        if (canvasSize.contains(imageSize))
        {
            size = imageSize;
        }
        else
        {
            size = createLargestFittingSize(imageSize, canvasSize);
        }
        int x = (canvasSize.width - size.width) / 2;
        int y = (canvasSize.height - size.height) / 2;
        e.gc.drawImage(previewImage, 0, 0, previewImage.getBounds().width, previewImage.getBounds().height,
                x, y, size.width,  size.height);
    }

    /**
     * Creates a copy of <code>sourceSize</code> that is scaled so that it is the largest size
     * with the same aspect ratio as <code>sourceSize</code> that still fits into
     * <code>targetSize</code>.
     *
     * @param sourceSize
     * @param targetSize
     * @return the largest size with the same aspect ratio as <code>sourceSize</code> that still
     *         fits into <code>targetSize</code>
     */
    protected Dimension createLargestFittingSize(Dimension sourceSize, Dimension targetSize)
    {
        double scale = Math.min((1.0d * targetSize.width)/sourceSize.width, (1.0d * targetSize.height)/sourceSize.height);
        Dimension size = sourceSize.getScaled(scale);
        return size;
    }

    private Image generatePreviewImage(Dimension maxSize)
    {
    	final Image image = GEFUtils.createImage(selectedEditParts, maxSize, isShowSelectionOnly(), isAntialiasing());
        return image;
    }
}