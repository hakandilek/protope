package org.protope.designer.base.editor;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.AlignmentRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.MatchHeightRetargetAction;
import org.eclipse.gef.ui.actions.MatchWidthRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;
import org.protope.designer.base.actions.ExportImageAction;
import org.protope.designer.i18n.ProtopeMessages;

public class BaseActionBarContributor extends ActionBarContributor {

	/**
	 * @see org.eclipse.gef.ui.actions.ActionBarContributor#buildActions()
	 */
	protected void buildActions() {
		addRetargetAction(new UndoRetargetAction());
		addRetargetAction(new RedoRetargetAction());

		addRetargetAction(new AlignmentRetargetAction(PositionConstants.LEFT));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.CENTER));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.RIGHT));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.TOP));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.MIDDLE));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.BOTTOM));

		addRetargetAction(new ZoomInRetargetAction());
		addRetargetAction(new ZoomOutRetargetAction());

		addRetargetAction(new MatchWidthRetargetAction());
		addRetargetAction(new MatchHeightRetargetAction());

		addRetargetAction(new RetargetAction(
				GEFActionConstants.TOGGLE_RULER_VISIBILITY,
				ProtopeMessages.ToggleRulerVisibility_Label,
				IAction.AS_CHECK_BOX));

		addRetargetAction(new RetargetAction(
				GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY,
				ProtopeMessages.ToggleSnapToGeometry_Label,
				IAction.AS_CHECK_BOX));

		addRetargetAction(new RetargetAction(
				GEFActionConstants.TOGGLE_GRID_VISIBILITY,
				ProtopeMessages.ToggleGrid_Label, IAction.AS_CHECK_BOX));

		addAction(new ExportImageAction());
	}

	/**
	 * @see org.eclipse.gef.ui.actions.ActionBarContributor#declareGlobalActionKeys()
	 */
	protected void declareGlobalActionKeys() {
		addGlobalActionKey(ActionFactory.PRINT.getId());
		addGlobalActionKey(ActionFactory.SELECT_ALL.getId());
		addGlobalActionKey(ActionFactory.PASTE.getId());
		addGlobalActionKey(ActionFactory.DELETE.getId());
	}

	/**
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToToolBar(IToolBarManager)
	 */
	public void contributeToToolBar(IToolBarManager tbm) {
		tbm.add(getAction(ActionFactory.UNDO.getId()));
		tbm.add(getAction(ActionFactory.REDO.getId()));

		tbm.add(new Separator());

		tbm.add(new Separator());
		tbm.add(getAction(GEFActionConstants.ALIGN_LEFT));
		tbm.add(getAction(GEFActionConstants.ALIGN_CENTER));
		tbm.add(getAction(GEFActionConstants.ALIGN_RIGHT));
		tbm.add(new Separator());
		tbm.add(getAction(GEFActionConstants.ALIGN_TOP));
		tbm.add(getAction(GEFActionConstants.ALIGN_MIDDLE));
		tbm.add(getAction(GEFActionConstants.ALIGN_BOTTOM));

		tbm.add(new Separator());
		tbm.add(getAction(GEFActionConstants.MATCH_WIDTH));
		tbm.add(getAction(GEFActionConstants.MATCH_HEIGHT));

		tbm.add(new Separator());
		String[] zoomStrings = new String[] { ZoomManager.FIT_ALL,
				ZoomManager.FIT_HEIGHT, ZoomManager.FIT_WIDTH };
		tbm.add(new ZoomComboContributionItem(getPage(), zoomStrings));

		tbm.add(new Separator());
		tbm.add(getAction(ExportImageAction.ID));
	}

	/**
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToMenu(IMenuManager)
	 */
	public void contributeToMenu(IMenuManager menubar) {
		super.contributeToMenu(menubar);
		MenuManager viewMenu = new MenuManager(
				ProtopeMessages.ViewMenu_LabelText);
		viewMenu.add(getAction(GEFActionConstants.ZOOM_IN));
		viewMenu.add(getAction(GEFActionConstants.ZOOM_OUT));
		viewMenu.add(new Separator());
		viewMenu.add(getAction(GEFActionConstants.TOGGLE_RULER_VISIBILITY));
		viewMenu.add(getAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY));
		viewMenu.add(getAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY));
		viewMenu.add(new Separator());
		viewMenu.add(getAction(GEFActionConstants.MATCH_WIDTH));
		viewMenu.add(getAction(GEFActionConstants.MATCH_HEIGHT));
		menubar.insertAfter(IWorkbenchActionConstants.M_EDIT, viewMenu);
	}

}
