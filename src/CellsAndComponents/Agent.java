package CellsAndComponents;

public class Agent extends Inhabitant {
	private int sugar;
	private int sugarMetabolism;
	private int vision;
	
	public Agent(int state) {
		super(state);
		// TODO Auto-generated constructor stub
	}
	
	public void addSugar(int sugarFromPatch) {
		sugar += sugarFromPatch;
	}
	
	public int getSugarLevel() {
		return sugar;
	}
	
	public void subtractMetabolism() {
		sugar -= sugarMetabolism;
	}

}
