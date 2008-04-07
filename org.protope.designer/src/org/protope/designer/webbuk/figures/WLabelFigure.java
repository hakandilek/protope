package org.protope.designer.webbuk.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;

public class WLabelFigure extends Figure {

	/** The inner TextFlow * */
	private TextFlow textFlow;

	public WLabelFigure() {
		super();
		setBackgroundColor(ColorConstants.tooltipBackground);
		setForegroundColor(ColorConstants.tooltipForeground);

		FlowLayout centerLayout = new FlowLayout();
		centerLayout.setMajorAlignment(FlowLayout.ALIGN_CENTER);
		FlowPage flowPage = new FlowPage();
		flowPage.getInsets().top = 2;
		flowPage.getInsets().bottom = 3;
		flowPage.getInsets().left = 3;
		flowPage.getInsets().right = 3;

		textFlow = new TextFlow();

		textFlow.setLayoutManager(new ParagraphTextLayout(textFlow,
				ParagraphTextLayout.WORD_WRAP_SOFT));

		flowPage.add(textFlow);
		setLayoutManager(centerLayout);
		add(flowPage);
	}

	/**
	 * Returns the text inside the TextFlow.
	 * 
	 * @return the text flow inside the text.
	 */
	public String getText() {
		return textFlow.getText();
	}

	/**
	 * Sets the text of the TextFlow to the given value.
	 * 
	 * @param newText
	 *            the new text value.
	 */
	public void setText(String newText) {
		textFlow.setText(newText);
	}


}
