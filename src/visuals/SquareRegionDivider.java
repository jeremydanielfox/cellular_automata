package visuals;
import javafx.scene.shape.Polygon;

public class SquareRegionDivider extends CellRegionDivider {

	public SquareRegionDivider(int cellsAcross, int cellsUpDown, int height,
			int width, int xspace, int yspace) {
		super(cellsAcross, cellsUpDown, height, width, xspace, yspace);
	}

	public void assignPoints(Polygon myShape, int i, int j) {
		myShape.getPoints().addAll(
				new Double[] {
						(double) getXOffset() + (i - 1) * getCellWidth(),
						(double) getYOffset() + (j - 1) * getCellHeight(),
						(double) getXOffset() + (i - 1) * getCellWidth(),
						(double) getYOffset() + j * getCellHeight(),
						(double) getXOffset() + i * getCellWidth(),
						(double) getYOffset() + j * getCellHeight(),
						(double) getXOffset() + i * getCellWidth(),
						(double) getYOffset() + (j - 1) * getCellHeight()

				});
	}

}
