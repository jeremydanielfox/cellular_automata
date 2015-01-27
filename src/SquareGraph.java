import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class SquareGraph extends BaseGraph {
	private int myCellsWidth;
	private int myCellsHeight;
	private int myScreenWidth;
	private int myScreenHeight;
	private Shape myShape;

	public SquareGraph(int numCellsWidth, int numCellsHeight, int screenWidth,
			int screenHeight) {
		myCellsWidth = numCellsWidth;
		myCellsHeight = numCellsHeight;
		myScreenWidth = screenWidth;
		myScreenHeight = screenHeight;
		myShape = new Polygon();
	}

	public void initializeCells() {
		for (int i = 1; i <= myCellsWidth * myCellsHeight; i++) {
			Cell temp = new Cell(i, myShape);
		}
	}
	
	public void connectCells() {
		
	}
}
