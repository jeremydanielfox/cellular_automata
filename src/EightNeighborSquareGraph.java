/**
 * This class is the graph class for each cell having eight neighbors, which
 * includes each adjacent neighbor and each diagonally adjacent neighbor.
 * 
 * @author Jeremy, Team 12
 *
 */
public class EightNeighborSquareGraph extends SquareGraph {

	public EightNeighborSquareGraph(int numCellsWidth, int numCellsHeight,
			int screenWidth, int screenHeight, int points) {
		super(numCellsWidth, numCellsHeight, screenWidth, screenHeight, points);
	}

	public boolean isNeighbors(Cell first, Cell second) {
		return first.getID() == second.getID() - 1
				&& first.getID() % getNumCellsAcross() != 0
				|| first.getID() == second.getID() - getNumCellsAcross()
				|| first.getID() == second.getID() - getNumCellsAcross() - 1
				&& (second.getID() - 1) % getNumCellsAcross() != 0
				|| first.getID() == second.getID() - getNumCellsAcross() + 1
				&& second.getID() % getNumCellsAcross() != 0;
	}
}
