package Factories;

import CellsAndComponents.AquaticCreature;
import CellsAndComponents.Inhabitant;
import CellsAndComponents.Shark;

/**
 * This factory is used when a given model has more than one type of Inhabitant.
 * 
 * @author Jeremy
 *
 */
public class InhabitantFactory {

	public Inhabitant createSpecifiedInhabitant(String inhabitant, int state) {

		switch (inhabitant) {
		case "shark":
			return new Shark(state);
		case "fish":
			return new AquaticCreature(state);
		default:
			return new Inhabitant(state);
		}

	}
}
