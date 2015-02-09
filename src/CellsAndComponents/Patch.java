package CellsAndComponents;

import javafx.scene.paint.Color;

/**
 * The purpose of this class is to serve as a template for objects that reside
 * in cells and need to keep track of extra information that a typical, generic
 * cell doesn't have.
 * 
 * @author Megan
 *
 */
public abstract class Patch {

	public Patch() {
	}

	/**
	 * This method returns an integer value that should be assigned to the cell
	 * that contains this patch.
	 * 
	 * @return the int that corresponds to the state of holding this patch
	 */
	public abstract int getStateForCell();

	/**
	 * Returns the color that corresponds to this patch, based on information
	 * known to the specific patch
	 * 
	 * @return the color associated with this patch
	 */
	public abstract Color getColor();

}
