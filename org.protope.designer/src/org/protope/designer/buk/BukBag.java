package org.protope.designer.buk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.protope.designer.base.model.UIElement;

public class BukBag {

	private List<Buk> items = new ArrayList<Buk>();
	private Map<Class<?>, Buk> modelMapping = new HashMap<Class<?>, Buk>();

	private String drawerLabel;

	private String drawerIconPath;

	public void add(Buk item) {
		if (!items.contains(item)) {
			items.add(item);
			Class<? extends UIElement> model = item.getModel();
			modelMapping.put(model, item);
		}
	}

	public void remove(Buk item) {
		items.remove(item);
		modelMapping.remove(item.getModel());
	}

	public List<Buk> getAll() {
		return items;
	}

	public String getDrawerLabel() {
		return drawerLabel;
	}

	public void setDrawerLabel(String drawerLabel) {
		this.drawerLabel = drawerLabel;
	}

	public String getDrawerIconPath() {
		return drawerIconPath;
	}

	public void setDrawerIconPath(String drawerIconPath) {
		this.drawerIconPath = drawerIconPath;
	}

	public Buk getItemFor(Object model) {
		Class<?> type = model.getClass();
		Buk item = modelMapping.get(type);
		return item;
	}

}
