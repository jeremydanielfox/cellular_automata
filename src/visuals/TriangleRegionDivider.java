package visuals;

import javafx.scene.shape.Polygon;

public class TriangleRegionDivider extends CellRegionDivider {

	public TriangleRegionDivider(int cellsAcross, int cellsUpDown, int height,
			int width, int xspace, int yspace) {
		super(cellsAcross, cellsUpDown, height, width, xspace, yspace);
	}

	@Override
	protected void assignPoints(Polygon myShape, int i, int j, int count) {
//		double x = i;
		double y = j;
		double x = Math.floorDiv(i, 2);
//		double y = Math.floorDiv(j, 2);
		if (count % 2 != 1)
			myShape.getPoints().addAll(
					new Double[] { (double) getXOffset() + (x-rowShift(j)) * getCellWidth(),
							(double) getYOffset() + y * getCellHeight(),
							(double) getXOffset() + (x + 1-rowShift(j)) * getCellWidth(),
							(double) getYOffset() + y * getCellHeight(),
							(double) getXOffset() + (x + .5-rowShift(j)) * getCellWidth(),
							(double) getYOffset() + (y + 1) * getCellHeight()

					});
		else
			myShape.getPoints()
					.addAll(new Double[] {
							(double) getXOffset() + (x + 1-rowShift(j)) * getCellWidth(),
							(double) getYOffset() + y * getCellHeight(),
							(double) getXOffset() + (x + .5-rowShift(j)) * getCellWidth(),
							(double) getYOffset() + (y + 1) * getCellHeight(),
							(double) getXOffset() + (x + 1.5-rowShift(j)) * getCellWidth(),
							(double) getYOffset() + (y + 1) * getCellHeight() });
	}
	
	private double rowShift(int j) {
		if (j%2!=0)
			return .5;
		else 
			return 0;
	}
	
	
	public void calculateValues() {
		setCellWidth(getScreenWidth() / ((getNumCellsWidth()+1)/2));
		setCellHeight(getScreenHeight() / getNumCellsHeight());
	}
}
