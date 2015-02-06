package Factories;
import Graphs.BaseGraph;
import Graphs.EdgeManager;
import Graphs.EightNeighborToroid;
import Graphs.FourNeighborToroid;

public class EdgeManagerFactory {
	public EdgeManager createSpecifiedManager(String type, BaseGraph graph) {
		switch (type) {
		case "FourNeighor":
			return new FourNeighborToroid(graph);
		case "EightNeighbor":
			return new EightNeighborToroid(graph);
		default:
			return new EdgeManager(graph);
		}

	}

}
