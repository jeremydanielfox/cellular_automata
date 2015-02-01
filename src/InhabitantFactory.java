
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
