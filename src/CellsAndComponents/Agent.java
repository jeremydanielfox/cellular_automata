package CellsAndComponents;

import java.util.Random;

public class Agent extends Inhabitant {
	private int sugar;
	private int sugarMetabolism;
	private int vision;
	private Random myRandomGenerator;
	
	public Agent(int state) {
		super(state);
		myRandomGenerator = new Random();
		sugar = myRandomGenerator.nextInt(20) + 5;
		sugarMetabolism = myRandomGenerator.nextInt(3) + 1;
		vision = myRandomGenerator.nextInt(5) + 1;
	}
	
	public void addSugar(int sugarFromPatch) {
		sugar += sugarFromPatch;
	}
	
	public int getSugarLevel() {
		return sugar;
	}
	
	private void subtractMetabolism() {
		sugar -= sugarMetabolism;
	}
	
	public boolean checkDead() {
		subtractMetabolism();
		return sugar < 0;
	}
	
	public int getVision() {
		return vision;
	}
}
