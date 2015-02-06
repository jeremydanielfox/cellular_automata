public class EdgeManagerFactory {
	public EdgeManager createSpecifiedManager(String type, BaseGraph graph) {
		switch (type) {
		case "FourNeighor":
			return new FourNeighborToroid(graph);
		default:
			return new EdgeManager(graph);
		}

	}

}
