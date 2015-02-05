import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

public class EdgeManager {
	private List<Cell> topRow = new ArrayList<>();
	private List<Cell> leftCol = new ArrayList<>();
	private Point2D LEFT = new Point2D(-1, 0);

	public EdgeManager(BaseGraph myGraph) {

	}

	private void makeTopRow(BaseGraph myGraph) {
		for (Cell current : myGraph.getAllCells()) {
			// unchain method by pulling point map method into graph
			if (myGraph.getCellPointMap().get(current).getY() == 0) {
				topRow.add(current);
			}
		}
	}

	private void makeLeftCol(BaseGraph myGraph) {
		for (Cell current : myGraph.getAllCells()) {
			if (myGraph.getCellPointMap().get(current).getX() == 0)
				leftCol.add(current);
		}
	}

}
