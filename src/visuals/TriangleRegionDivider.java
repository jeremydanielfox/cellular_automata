package visuals;

import javafx.scene.shape.Polygon;

public class TriangleRegionDivider extends CellRegionDivider {

	public TriangleRegionDivider(int cellsAcross, int cellsUpDown, int height,
			int width, int xspace, int yspace) {
		super(cellsAcross, cellsUpDown, height, width, xspace, yspace);
	}

	@Override
	protected void assignPoints(Polygon myShape, int i, int j) {
		double x = Math.floorDiv(i, 2);
		double y = Math.floorDiv(j, 2);
		if ((i + 1) * (j + 1) % 2 == 1)
			myShape.getPoints().addAll(
					new Double[] { (double) getXOffset() + x * getCellWidth(),
							(double) getYOffset() + y * getCellHeight(),
							(double) getXOffset() + (x + 1) * getCellWidth(),
							(double) getYOffset() + y * getCellHeight(),
							(double) getXOffset() + (x + .5) * getCellWidth(),
							(double) getYOffset() + (y + 1) * getCellHeight()

					});
		else
			myShape.getPoints()
					.addAll(new Double[] {
							(double) getXOffset() + (x + 1) * getCellWidth(),
							(double) getYOffset() + y * getCellHeight(),
							(double) getXOffset() + (x + .5) * getCellWidth(),
							(double) getYOffset() + (y + 1) * getCellHeight(),
							(double) getXOffset() + (x + 1.5) * getCellWidth(),
							(double) getYOffset() + (y + 1) * getCellHeight() });
	}
}
