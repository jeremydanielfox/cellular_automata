package Factories;

import visuals.CellRegionDivider;
import visuals.SquareRegionDivider;
import visuals.TriangleRegionDivider;

/**
 * This class allows different Cell Dividers to be created so that the screen
 * can be divided up into the appropriate shapes.
 * 
 * @author Jeremy
 *
 */
public class CellRegionDividerFactory {
	public CellRegionDivider createSpecifiedDivider(int cellsAcross,
			int cellsUpDown, int height, int width, int xspace, int yspace,
			String shape) {

		switch (shape) {
		case "FourNeighborSquareGraph":
			return new SquareRegionDivider(cellsAcross, cellsUpDown, height,
					width, xspace, yspace);
		case "EightNeighborSquareGraph":
			return new SquareRegionDivider(cellsAcross, cellsUpDown, height,
					width, xspace, yspace);
		case "FourNeighborTriangleGraph":
			return new TriangleRegionDivider(cellsAcross, cellsUpDown, height,
					width, xspace, yspace);
		case "EightNeighborTriangleGraph":
			return new TriangleRegionDivider(cellsAcross, cellsUpDown, height,
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
