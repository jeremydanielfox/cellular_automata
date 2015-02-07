package Factories;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import Graphs.BaseGraph;
import Graphs.EightNeighborSquareGraph;
import Graphs.SquareGraph;

/**
 * This is the factory to make new graphs
 * 
 * @author Jeremy, Team 12
 *
 */
public class GraphFactory {
	private String myType;

	public GraphFactory() {

	}

	public GraphFactory(String type) {
		myType = type;
	}

	public BaseGraph createSpecifiedGraph(Polygon[][] myShapes,
			int numCellsWidth, int numCellsHeight, int defaultState,
			Color defaultColor, String model) {

		switch (model) {
		case "FourNeighborSquareGraph":
			return new SquareGraph(myShapes, numCellsWidth,
					numCellsHeight, defaultState, defaultColor, model);
		case "FourNeighborTriangleGraph":
			return new SquareGraph(myShapes, numCellsWidth,
					numCellsHeight, defaultState, defaultColor, model);
		case "EightNeighborTriangleGraph":
			return new EightNeighborSquareGraph(myShapes, numCellsWidth,
					numCellsHeight, defaultState, defaultColor, model);
		case "EightNeighor":
			return new EightNeighborSquareGraph(myShapes, numCellsWidth,
					numCellsHeight, defaultState, defaultColor, model);

		case "GameOfLife":
			return new EightNeighborSquareGraph(myShapes, numCellsWidth,
					numCellsHeight, defaultState, defaultColor, model);
		default:
			return new SquareGraph(myShapes, numCellsWidth, numCellsHeight,
					defaultState, defaultColor, model);
		}

	}
}
