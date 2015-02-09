package CellsAndComponents;

/**
 * This is the Inhabitant class that is used by the CellWithInhabitant and
 * AdvancedCell objects. The purpose of this class is to allow the creatures
 * that move around in the cellular automata simulations to keep track of some
 * extra information that the cell doesn't need to know about or keep track of.
 * This class serves as the parent to multiple other classes that each add on
 * unique information in addition to the state provided here in the inhabitant
 * class.
 * 
 * @author Jeremy, Sierra, Megan
 *
 */
public class Inhabitant {
	private int myState;

	public Inhabitant(int state) {
		myState = state;
	}

	public int getState() {
		return myState;
	}

}
