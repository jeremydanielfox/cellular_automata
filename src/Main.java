public class Main {
	public static void main(String[] args) {
		launch(args);
	}

	private static void launch(String[] args) {
		// TODO Auto-generated method stub
		BaseGraph myGraph = new ToroidSquareGraph(3, 3, 300, 300, 0, 0, 1, 0, null, "");
		for (Cell current: myGraph.getAllCells()) {
			System.out.println(current.getID());
			printNeighbors(myGraph,current);
			System.out.println();
		}
		
		
	} 
	
	public static void printNeighbors (BaseGraph myGraph, Cell current) {
		for (Cell me: myGraph.getNeighbors(current)) {
			System.out.println(me.getID());
		}
	}
}
