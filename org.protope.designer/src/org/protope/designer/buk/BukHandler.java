package org.protope.designer.buk;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.protope.designer.base.model.UIElement;
import org.protope.designer.i18n.ProtopeMessages;

public class BukHandler {

	public PaletteRoot createPalette(BukBag bag) {
		PaletteRoot logicPalette = new PaletteRoot();
		logicPalette.addAll(createCategories(bag, logicPalette));
		return logicPalette;
	}

	private List<PaletteContainer> createCategories(BukBag bag, PaletteRoot root) {
		List<PaletteContainer> categories = new ArrayList<PaletteContainer>();
		categories.add(createControlGroup(root));
		categories.add(createComponentsDrawer(bag));
		return categories;
	}

	private PaletteContainer createControlGroup(PaletteRoot root) {
		PaletteGroup controlGroup = new PaletteGroup(
				ProtopeMessages.UIPlugin_Category_ControlGroup_Label);

		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		ToolEntry tool = new PanningSelectionToolEntry();
		entries.add(tool);
		root.setDefaultEntry(tool);

		PaletteStack marqueeStack = new PaletteStack(
				ProtopeMessages.Marquee_Stack, "", null); //$NON-NLS-1$
		marqueeStack.add(new MarqueeToolEntry());
		MarqueeToolEntry marquee = new MarqueeToolEntry();
		marquee.setToolProperty(MarqueeSelectionTool.PROPERTY_MARQUEE_BEHAVIOR,
				new Integer(MarqueeSelectionTool.BEHAVIOR_CONNECTIONS_TOUCHED));
		marqueeStack.add(marquee);
		marquee = new MarqueeToolEntry();
		marquee.setToolProperty(MarqueeSelectionTool.PROPERTY_MARQUEE_BEHAVIOR,
				new Integer(MarqueeSelectionTool.BEHAVIOR_CONNECTIONS_TOUCHED
						| MarqueeSelectionTool.BEHAVIOR_NODES_CONTAINED));
		marqueeStack.add(marquee);
		marqueeStack
				.setUserModificationPermission(PaletteEntry.PERMISSION_NO_MODIFICATION);
		entries.add(marqueeStack);

		controlGroup.addAll(entries);
		return controlGroup;
	}

	private PaletteContainer createComponentsDrawer(BukBag bag) {

		PaletteDrawer drawer = new PaletteDrawer(
				bag.getDrawerLabel(),
				ImageDescriptor.createFromFile(bag.getClass(), bag.getDrawerIconPath()));

		List<CombinedTemplateCreationEntry> entries = new ArrayList<CombinedTemplateCreationEntry>();

		List<Buk> items = bag.getAll();
		ImageDescriptor smallIcon = null;
		ImageDescriptor mediumIcon = null;
		CombinedTemplateCreationEntry combined = null;

		for (Buk item : items) {
			Class<? extends UIElement> model = item.getModel();
			String label = item.getPaletteLabel();
			String description = item.getPaletteDescription();
			String smallIconPath = item.getSmallIconPath();
			String mediumIconPath = item.getMediumIconPath();

			smallIcon = ImageDescriptor.createFromFile(model, smallIconPath);
			mediumIcon = ImageDescriptor.createFromFile(model, mediumIconPath);
			
			combined = new CombinedTemplateCreationEntry(label, description,
					new SimpleFactory(model), smallIcon, mediumIcon);
			entries.add(combined);
		}

		drawer.addAll(entries);
		return drawer;
	}

	
	public EditPart getEditorFor(BukBag bag, Object model) {
		Buk item = bag.getItemFor(model);
		if (item == null) return null;
		Class<? extends EditPart> editClass = item.getEdit();
		EditPart instance = null;
		try {
			instance = editClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return instance;
	}

}
