package Factories;

import Graphs.BaseGraph;
import Graphs.EdgeManager;
import Graphs.EightNeighborToroid;
import Graphs.FourNeighborToroid;

/**
 * This class creates appropriate EdgeManagers. The graph classes use this class
 * to wrap edges appropriately
 * 
 * @author Jeremy
 *
 */
public class EdgeManagerFactory {
	public EdgeManager createSpecifiedManager(String type, BaseGraph graph) {
		switch (type) {
		case "FourNeighborSquareGraphToroidal":
			return new FourNeighborToroid(graph);
		case "EightNeighborSquareGraphToroidal":
			return new EightNeighborToroid(graph);
		case "FourNeighborTriangleGraphToroidal":
			return new FourNeighborToroid(graph);
		case "EightNeighborTriangleGraphToroidal":
			return new EightNeighborToroid(graph);
		default:
			return new EdgeManager(graph);
		}

	}

}
