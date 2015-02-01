public class Shark extends AquaticCreature {
	private int energyLevel;

	public Shark(int state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	public void decreaseEnergy() {
		energyLevel -= 1;
	}

	public void increaseEnergy() {
		energyLevel += 1;
	}

	public int getEnergyLevel() {
		return energyLevel;
	}
}
