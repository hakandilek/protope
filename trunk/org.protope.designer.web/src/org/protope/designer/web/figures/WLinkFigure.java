package org.protope.designer.web.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;

public class WLinkFigure extends Figure {

	/** The inner TextFlow * */
	private TextFlow textFlow;

	public WLinkFigure() {
		super();

		setBackgroundColor(ColorConstants.tooltipBackground);
		setForegroundColor(ColorConstants.blue);

		FlowLayout centerLayout = new FlowLayout();
		centerLayout.setMajorAlignment(FlowLayout.ALIGN_CENTER);
		FlowPage flowPage = new FlowPage();
		flowPage.getInsets().top = 2;
		flowPage.getInsets().bottom = 3;
		flowPage.getInsets().left = 3;
		flowPage.getInsets().right = 3;

		textFlow = new TextFlow();
		textFlow.setForegroundColor(ColorConstants.blue);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);

		// draw underline
		graphics.translate(getLocation());
		setForegroundColor(ColorConstants.blue);
		Rectangle c = textFlow.getBounds();

		Insets i = textFlow.getInsets();
		int x = i.left + c.x + 1;
		int y = i.top + c.y + c.height + 1;
		graphics.drawLine(x + 1, y, x + c.width, y);
		graphics.translate(getLocation().getNegated());
	}

}
