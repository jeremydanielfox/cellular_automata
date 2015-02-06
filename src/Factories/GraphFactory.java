package Factories;
import Graphs.BaseGraph;
import Graphs.EightNeighborSquareGraph;
import Graphs.SquareGraph;
import javafx.scene.paint.Color;

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

	public BaseGraph createSpecifiedGraph(int numCellsWidth,
			int numCellsHeight, int screenWidth, int screenHeight, int xOffset,
			int yOffset, int points, int defaultState, Color defaultColor,
			String model) {

		switch (model) {
		case "EightNeighor":
			return new EightNeighborSquareGraph(numCellsWidth,numCellsHeight,screenWidth,screenHeight,
					xOffset,yOffset,points,defaultState,defaultColor,model);

		case "GameOfLife":
			return new EightNeighborSquareGraph(numCellsWidth, numCellsHeight,
					screenWidth, screenHeight, xOffset, yOffset, points,
					defaultState, defaultColor, model);
		default:
			return new SquareGraph(numCellsWidth, numCellsHeight, screenWidth,
					screenHeight, xOffset, yOffset, points, defaultState,
					defaultColor, model);
		}

	}
}
