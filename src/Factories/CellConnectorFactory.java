// This entire file is part of my masterpiece
// Jeremy Fox
package Factories;

import Graphs.BaseGraph;
import Graphs.CellConnector;
import Graphs.EightNeighborConnector;
import Graphs.FourNeighborConnector;

/**
 * This class will return an appropriate Connector object to connect the cells
 * in a BaseGraph according to the correct algorithm
 * 
 * @author Jeremy
 *
 */
public class CellConnectorFactory {
	public CellConnector createSpecifiedConnector(String type, BaseGraph graph) {
		switch (type) {
		case "FourNeighborSquareGraph":
			return new FourNeighborConnector(graph);
		case "EightNeighborSquareGraph":
			return new EightNeighborConnector(graph);
		case "FourNeighborTriangleGraph":
			return new FourNeighborConnector(graph);
		case "EightNeighborTriangleGraph":
			return new EightNeighborConnector(graph);
		default:
			return new FourNeighborConnector(graph);
		}

	}
}
