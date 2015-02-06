import javafx.geometry.Point2D;
import javafx.scene.paint.Color;


public class EightNeighborSquareGraph extends SquareGraph {
	private Point2D RIGHT;
	private Point2D LEFT;
	private Point2D UP;
	private Point2D DOWN;
	private Point2D UP_LEFT;
	private Point2D UP_RIGHT;
	private Point2D DOWN_LEFT;
	private Point2D DOWN_RIGHT;
	

	public EightNeighborSquareGraph(int numCellsWidth, int numCellsHeight,
			int screenWidth, int screenHeight, int xOffset, int yOffset,
			int points, int defaultState, Color defaultColor, String model) {
		super(numCellsWidth, numCellsHeight, screenWidth, screenHeight, xOffset,
				yOffset, points, defaultState, defaultColor, model);
		// TODO Auto-generated constructor stub
	}
	
	public void initializeConstants() {
		RIGHT = new Point2D(1, 0);
		LEFT = new Point2D(-1, 0);
		UP = new Point2D(0, -1);
		DOWN = new Point2D(0, 1);
		UP_LEFT = new Point2D(-1,-1);
		UP_RIGHT = new Point2D(1,-1);
		DOWN_LEFT = new Point2D(-1,1);
		DOWN_RIGHT = new Point2D(1,1);
	}
	
	@Override
	public void connectCells() {
		for (Cell current : this.getAllCells()) {
			checkConnect(current,RIGHT);
			checkConnect(current,LEFT);
			checkConnect(current,DOWN);
			checkConnect(current,UP);
			checkConnect(current,UP_LEFT);
			checkConnect(current,UP_RIGHT);
			checkConnect(current,DOWN_LEFT);
			checkConnect(current,DOWN_RIGHT);
		}

	}

}
