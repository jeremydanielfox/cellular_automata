public class Shark extends AquaticCreature {
	private int energyLevel;

	public Shark(int state) {
		super(state);
		energyLevel = 0;
	}

	public void decreaseEnergy() {
		energyLevel += 1;
	}

	public void increaseEnergy() {
		energyLevel -= 1;
	}

	public int getEnergyLevel() {
		return energyLevel;
	}
}
