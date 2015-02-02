import javafx.scene.paint.Color;

/**
 * This creates a Toroid made of squares, otherwise known as a wraparound graph.
 * This is done by simply modifying the requirements for neighbors to link to
 * each other
 * 
 * @author Jeremy, Team 12
 *
 */
public class ToroidSquareGraph extends SquareGraph {

	public ToroidSquareGraph(int numCellsWidth, int numCellsHeight,
			int screenWidth, int screenHeight, int xOffset, int yOffset,
			int points, int defaultState, Color defaultColor, String model) {
		super(numCellsWidth, numCellsHeight, screenWidth, screenHeight,
				xOffset, yOffset, points, defaultState, defaultColor, model);
		// TODO Auto-generated constructor stub
	}

	public boolean additionalNeighborCondition(Cell first, Cell second) {
		return second.getID() - first.getID() == getNumCellsAcross() - 1
				&& second.getID() % getNumCellsAcross() == 0
				|| second.getID() - first.getID() == getNumCellsAcross()
						* (getNumCellsUpDown() - 1);
	}

}
