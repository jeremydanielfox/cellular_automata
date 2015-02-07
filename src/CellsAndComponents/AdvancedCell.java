package CellsAndComponents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * This is an extension of the Cell class to create more complex Cells with
 * Inhabitants and Patches.
 * 
 * @author Megan
 *
 */

public class AdvancedCell extends Cell {
	private Collection<Inhabitant> myInhabitants;
	private Patch myPatch;

	public AdvancedCell(int id, Polygon shape,
			List<Point2D> verticies, int defaultState, Color defaultColor) {
		super(id, shape, verticies, defaultState, defaultColor);
		// TODO Auto-generated constructor stub
		myInhabitants = new ArrayList<Inhabitant>();
	}

	public void addInhabitant(Inhabitant inhabitant) {
		myInhabitants.add(inhabitant);
	}

	public List<Inhabitant> getInhabitants() {
		return (List<Inhabitant>) myInhabitants;
	}
	
	public int getNumInhabitants() {
		return myInhabitants.size();
	}
	
	public void setPatch(Patch patch) {
		myPatch = patch;
	}
	
	public Patch getPatch() {
		return myPatch;
	}
}
