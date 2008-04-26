package org.protope.designer.web.figures;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.draw2d.ButtonModel;
import org.eclipse.draw2d.ChangeEvent;
import org.eclipse.draw2d.ChangeListener;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Toggle;
import org.eclipse.swt.graphics.Image;

public class WCheckboxFigure extends Toggle {

	/** the inner label */
	private Label label = null;

	static final Image UNCHECKED = createImage("images/checkboxenabledoff.gif"), //$NON-NLS-1$
			CHECKED = createImage("images/checkboxenabledon.gif"); //$NON-NLS-1$

	private static Image createImage(String name) {
		InputStream stream = WCheckboxFigure.class.getResourceAsStream(name);
		Image image = new Image(null, stream);
		try {
			stream.close();
		} catch (IOException ioe) {
		}
		return image;
	}

	/**
	 * Constructs a CheckBox with the passed text in its label.
	 * 
	 * @param text
	 *            The label text
	 * @since 2.0
	 */
	public WCheckboxFigure(String text) {
		setContents(label = new Label(text, UNCHECKED));
	}

	/**
	 * Adjusts CheckBox's icon depending on selection status.
	 * 
	 * @since 2.0
	 */
	protected void handleSelectionChanged() {
		if (isSelected())
			label.setIcon(CHECKED);
		else
			label.setIcon(UNCHECKED);
	}

	/**
	 * Initializes this Clickable by setting a default model and adding a
	 * clickable event handler for that model. Also adds a ChangeListener to
	 * update icon when selection status changes.
	 * 
	 * @since 2.0
	 */
	protected void init() {
		super.init();
		addChangeListener(new ChangeListener() {
			public void handleStateChanged(ChangeEvent changeEvent) {
				if (changeEvent.getPropertyName().equals(
						ButtonModel.SELECTED_PROPERTY))
					handleSelectionChanged();
			}
		});
	}

	public void setText(String s) {
		label.setText(s);
	}

	public String getText() {
		return label.getText();
	}
	
}
