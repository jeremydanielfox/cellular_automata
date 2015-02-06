import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

/**
 * This manager class will allow us to implement new edge conditions separate
 * from the graph shape. The graph will create a new edge manager and pass in
 * the type of edge condition it wants.
 * 
 * @author Jeremy, Team 12
 *
 */
public class EdgeManager {
	private List<Cell> topRow = new ArrayList<>();
	private List<Cell> leftCol = new ArrayList<>();
	private Point2D RIGHT = new Point2D(1,0);
	private Point2D LEFT = new Point2D(-1,0);
	private Point2D UP = new Point2D(0,-1);
	private Point2D DOWN = new Point2D(0,1);
	private Point2D UP_RIGHT = new Point2D(1,-1);
	private Point2D UP_LEFT = new Point2D(-1,-1);
	private Point2D DOWN_RIGHT = new Point2D(1,1);
	private Point2D DOWN_LEFT = new Point2D(-1,1);


	public EdgeManager(BaseGraph myGraph) {

	}

	private void makeRowAndCol(BaseGraph myGraph) {
		for (Cell current : myGraph.getAllCells()) {
			// unchain method by pulling point map method into graph
			if (myGraph.getCellPointMap().get(current).getY() == 0)
				topRow.add(current);
			if (myGraph.getCellPointMap().get(current).getX() == 0)
				leftCol.add(current);
		}
	}

}
