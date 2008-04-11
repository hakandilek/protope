package org.protope.designer.extension;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DiagramRegistry {

	private static final DiagramRegistry INSTANCE = new DiagramRegistry();

	private Map<String, DiagramDefinition> diagrams = new HashMap<String, DiagramDefinition>();

	private DiagramRegistry() {
		super();
		initialize();
	}

	private void initialize() {
		DiagramRegistryReader reader = new DiagramRegistryReader(this);
		reader.readRegistry("org.protope.designer", "diagram");
	}

	/**
	 * @return the INSTANCE
	 */
	public static DiagramRegistry getINSTANCE() {
		return INSTANCE;
	}

	/**
	 * @return the diagrams
	 */
	public Collection<DiagramDefinition> getDiagrams() {
		return diagrams.values();
	}

	/**
	 * @param diagram
	 *            the diagram to add
	 */
	public void addDiagram(DiagramDefinition diagram) {
		if (diagram != null)
			this.diagrams.put(diagram.getDiagramID(), diagram);
	}

	/**
	 * @param diagram
	 *            the diagram to remove
	 */
	public void removeDiagram(DiagramDefinition diagram) {
		if (diagram != null)
			this.diagrams.remove(diagram.getDiagramID());
	}

	public DiagramDefinition getDiagram(String diagramID) {
		return diagrams.get(diagramID);
	}

}
