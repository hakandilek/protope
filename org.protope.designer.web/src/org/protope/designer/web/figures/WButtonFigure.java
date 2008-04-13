package org.protope.designer.web.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.graphics.Color;

public class WButtonFigure extends Figure {

	final static Color COLOR_TOP_LEFT = new Color(
			ColorConstants.tooltipBackground.getDevice(), 255, 255, 255);
	final static Color COLOR_BOTTOM_RIGHT = new Color(
			ColorConstants.tooltipBackground.getDevice(), 64, 64, 64);
	final static Color COLOR_BOTTOM_RIGHT_INNER = new Color(
			ColorConstants.tooltipBackground.getDevice(), 128, 128, 128);
	final static Color COLOR_FONT = new Color(ColorConstants.tooltipBackground
			.getDevice(), 0, 0, 0);
	final static Color COLOR_BACKGROUND = new Color(
			ColorConstants.tooltipBackground.getDevice(), 212, 208, 200);

	/** The inner TextFlow * */
	private TextFlow textFlow;

	private boolean selected;

	public WButtonFigure() {
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

	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle rect = getBounds().getCopy();

		graphics.translate(getLocation());

		graphics.setForegroundColor(COLOR_FONT);
		graphics.setBackgroundColor(COLOR_BACKGROUND);
		graphics.fillRectangle(1, 1, rect.width - 2, rect.height - 2);

		graphics.setForegroundColor(COLOR_TOP_LEFT);
		graphics.drawLine(1, 1, 1, rect.height - 3);
		graphics.drawLine(1, 1, rect.width - 3, 1);

		graphics.setForegroundColor(COLOR_BOTTOM_RIGHT);
		graphics.drawLine(1, rect.height - 2, rect.width - 2, rect.height - 2);
		graphics.drawLine(rect.width - 2, rect.height - 2, rect.width - 2, 1);

		graphics.setForegroundColor(COLOR_BOTTOM_RIGHT_INNER);
		graphics.drawLine(2, rect.height - 3, rect.width - 3, rect.height - 3);
		graphics.drawLine(rect.width - 3, rect.height - 3, rect.width - 3, 2);

		graphics.translate(getLocation().getNegated());
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

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
