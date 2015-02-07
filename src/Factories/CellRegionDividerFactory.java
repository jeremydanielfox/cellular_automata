package Factories;

import visuals.CellRegionDivider;
import visuals.SquareRegionDivider;
import visuals.TriangleRegionDivider;

public class CellRegionDividerFactory {
	public CellRegionDivider createSpecifiedDivider(int cellsAcross,
			int cellsUpDown, int height, int width, int xspace, int yspace,
			String shape) {

		switch (shape) {
		case "Square":
			return new SquareRegionDivider(cellsAcross, cellsUpDown, height,
					width, xspace, yspace);
		case "Triangle":
			return new TriangleRegionDivider(cellsAcross, cellsUpDown, height,
					width, xspace, yspace);
		default:
			return new SquareRegionDivider(cellsAcross, cellsUpDown, height,
					width, xspace, yspace);
		}

	}
}
