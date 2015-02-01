public class AquaticCreature extends Inhabitant {
	private int reproductionCounter;

	public AquaticCreature(int state) {
		super(state);
		reproductionCounter = 0;
	}

	public void increaseReproductionCounter() {
		reproductionCounter += 1;
	}

	public void resetReproductionCounter() {
		reproductionCounter = 0;
	}

	public int getReproductionCounter() {
		return reproductionCounter;
	}
}
