package org.protope.designer.tool;

import java.util.ArrayList;
import java.util.Collection;
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
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.protope.designer.base.model.UIElement;
import org.protope.designer.extension.DiagramDefinition;
import org.protope.designer.extension.PaletteDefinition;
import org.protope.designer.extension.PaletteRegistry;
import org.protope.designer.extension.ToolDefinition;
import org.protope.designer.i18n.ProtopeMessages;
import org.protope.designer.utils.ImageUtils;

public class ToolHandler {

	public PaletteRoot createPalette(DiagramDefinition diagram) {
		PaletteRoot logicPalette = new PaletteRoot();
		logicPalette.addAll(createCategories(diagram, logicPalette));
		return logicPalette;
	}

	private List<PaletteContainer> createCategories(DiagramDefinition diagram,
			PaletteRoot root) {
		List<PaletteContainer> categories = new ArrayList<PaletteContainer>();
		categories.add(createControlGroup(root));
		List<PaletteDefinition> palettes = diagram.getPalettes();
		for (PaletteDefinition palette : palettes) {
			categories.add(createComponentsDrawer(palette));
		}
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

	private PaletteContainer createComponentsDrawer(PaletteDefinition palette) {
		String pluginID = palette.getDeclaringPluginID();
		ImageDescriptor image = ImageUtils.getImageDescriptor(pluginID, palette
				.getDrawerIconPath());
		String drawerLabel = palette.getDrawerLabel();
		PaletteDrawer drawer = new PaletteDrawer(drawerLabel, image);
		drawer.setInitialState(PaletteDrawer.INITIAL_STATE_PINNED_OPEN);
		List<CombinedTemplateCreationEntry> entries = new ArrayList<CombinedTemplateCreationEntry>();

		List<ToolDefinition> tools = palette.getTools();
		ImageDescriptor smallIcon = null;
		ImageDescriptor mediumIcon = null;
		CombinedTemplateCreationEntry combined = null;

		for (ToolDefinition tool : tools) {
			String label = tool.getLabel();
			String description = tool.getDescription();
			String smallIconPath = tool.getSmallIconPath();
			String mediumIconPath = tool.getMediumIconPath();

			smallIcon = ImageUtils.getImageDescriptor(pluginID, smallIconPath);
			mediumIcon = ImageUtils
					.getImageDescriptor(pluginID, mediumIconPath);

			combined = new CombinedTemplateCreationEntry(label, description,
					new ToolDefinitionFactory(tool), smallIcon, mediumIcon);
			entries.add(combined);
		}

		drawer.addAll(entries);
		return drawer;
	}

	public EditPart getEditorFor(UIElement model) {
		Collection<PaletteDefinition> palettes = PaletteRegistry.getINSTANCE()
				.getPalettes();
		EditPart editor = null;
		for (PaletteDefinition palette : palettes) {
			ToolDefinition tool = palette.getItemFor(model);
			if (tool == null)
				continue;
			editor = tool.getEditor();
		}
		return editor;
	}

	public ToolDefinition getToolFor(UIElement model) {
		Collection<PaletteDefinition> palettes = PaletteRegistry.getINSTANCE()
				.getPalettes();
		for (PaletteDefinition palette : palettes) {
			ToolDefinition tool = palette.getItemFor(model);
			if (tool != null)
				return tool;
		}
		return null;
	}

	public ToolDefinition getToolFor(EditPart editor) {
		Collection<PaletteDefinition> palettes = PaletteRegistry.getINSTANCE()
				.getPalettes();
		for (PaletteDefinition palette : palettes) {
			ToolDefinition tool = palette.getItemFor(editor);
			if (tool != null)
				return tool;
		}
		return null;
	}

}
