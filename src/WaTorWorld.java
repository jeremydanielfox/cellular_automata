import java.util.Collection;
import java.util.Map;
import javafx.scene.paint.Color;

public class WaTorWorld extends BaseModel {
	private static final int WATER = 0;
	private static final int FISH = 1;
	private static final int SHARK = 2;
	private static final int DEFAULTSTATE = WATER;
	private static final Color WATERCOLOR = Color.AQUAMARINE;
	private static final Color FISHCOLOR = Color.YELLOW;
	private static final Color SHARKCOLOR = Color.LAVENDER;
	private static final Color DEFAULTCOLOR = WATERCOLOR;
	private int sharkEnergy;
	private int timeTillReproduce;

	public WaTorWorld(Map<String, Double> parameters) {
		super(parameters, 2);
		// TODO Auto-generated constructor stub
		getStateToIntMap().put("water", WATER);
		getStateToIntMap().put("fish", FISH);
		getStateToIntMap().put("shark", SHARK);
		getStateToColorMap().put("water", WATERCOLOR);
		getStateToColorMap().put("fish", FISHCOLOR);
		getStateToColorMap().put("shark", SHARKCOLOR);

		sharkEnergy = parameters.get("energyLevel").intValue();
		timeTillReproduce = parameters.get("timeTillReproduce").intValue();
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Cell> updateFutureStates(Iterable<Cell> cellsToUpdate,
			BaseGraph graph) {
		for (Cell c : cellsToUpdate) {
			if (c.getCurrentState() == SHARK) {
				Shark currentShark = ((Shark) ((CellWithInhabitant) c).getInhabitant());
				if (currentShark.getEnergyLevel() < sharkEnergy
						&& countNeighbors(FISH, graph.getNeighbors(c)) > 0) {
					moveShark(graph, c, currentShark, FISH);
				} else if (currentShark.getEnergyLevel() == sharkEnergy) {
					changeStateAndInhabitant(c, new AquaticCreature(WATER),
							WATER, WATERCOLOR);
				} else {
					moveShark(graph, c, currentShark, WATER);
				}
			}
		}
		for (Cell c: cellsToUpdate) {
			if (c.getCurrentState() == FISH) {
				AquaticCreature currentFish = ((AquaticCreature) ((CellWithInhabitant) c).getInhabitant());
				if (countNeighbors(0, graph.getNeighbors(c)) > 0 && currentFish.getReproductionCounter() == timeTillReproduce) {
					Cell cellToMoveTo = getCellToMoveTo(graph, c, WATER);
					changeStateAndInhabitant(cellToMoveTo, currentFish, FISH, FISHCOLOR);
					changeStateAndInhabitant(c, new AquaticCreature(FISH), FISH, FISHCOLOR);
				}
				else {
					Cell cellToMoveTo = getCellToMoveTo(graph, c, WATER);
					//can I say !cellToMoveTo or is that not a thing? lookup later
					if (cellToMoveTo != null) {
						currentFish.increaseReproductionCounter();
						changeStateAndInhabitant(cellToMoveTo, currentFish, FISH, FISHCOLOR);
						changeStateAndInhabitant(c, new AquaticCreature(WATER), WATER, WATERCOLOR);
					}
				}
			}
		}
		return (Collection<Cell>) cellsToUpdate;
	}

	private void moveShark(BaseGraph graph, Cell c, Shark currentShark,
			int stateToCheckAgainst) {
		Cell cellToMoveTo = getCellToMoveTo(graph, c, stateToCheckAgainst);
		increaseSharkCounters(currentShark);
		changeStateAndInhabitant(cellToMoveTo, currentShark, SHARK, SHARKCOLOR);
		if (currentShark.getReproductionCounter() == timeTillReproduce) {
			currentShark.resetReproductionCounter();
			changeStateAndInhabitant(c, new Shark(SHARK), SHARK, SHARKCOLOR);
		} else {
			changeStateAndInhabitant(c, new AquaticCreature(WATER), WATER,
					WATERCOLOR);
		}
	}

	private void increaseSharkCounters(Shark currentShark) {
		currentShark.increaseEnergy();
		currentShark.increaseReproductionCounter();
	}

	private void changeStateAndInhabitant(Cell c, Inhabitant i, int state,
			Color stateColor) {
		((CellWithInhabitant) c).setInhabitant(i);
		changeFutureState(c, state, stateColor);
	}

	private Cell getCellToMoveTo(BaseGraph graph, Cell c, int state) {
		Cell fishCell = null;
		for (Cell toMove : graph.getNeighbors(c)) {
			if (toMove.getCurrentState() == state) {
				fishCell = toMove;
			}
		}
		return fishCell;
	}

	@Override
	public Color getDefaultColor() {
		// TODO Auto-generated method stub
		return DEFAULTCOLOR;
	}

	@Override
	public int getDefaultState() {
		// TODO Auto-generated method stub
		return DEFAULTSTATE;
	}

}
