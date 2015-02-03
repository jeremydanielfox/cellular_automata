import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;

/**
 * This class implements the WaTorWorld model and extends BaseModel.
 * 
 * @author Megan Gutter
 *
 */

public class WaTorWorld extends BaseModel {
	private static final int WATER = 0;
	private static final int FISH = 1;
	private static final int SHARK = 2;
	private static final int DEFAULT_STATE = WATER;
	private static final Color WATER_COLOR = Color.AQUAMARINE;
	private static final Color FISH_COLOR = Color.YELLOW;
	private static final Color SHARK_COLOR = Color.PURPLE;
	private static final Color DEFAULT_COLOR = WATER_COLOR;
	private int sharkEnergy;
	private int timeTillReproduce;

	public WaTorWorld(Map<String, Double> parameters) {
		super(parameters, 2);
		getStateToIntMap().put("water", WATER);
		getStateToIntMap().put("fish", FISH);
		getStateToIntMap().put("shark", SHARK);
		getStateToColorMap().put("water", WATER_COLOR);
		getStateToColorMap().put("fish", FISH_COLOR);
		getStateToColorMap().put("shark", SHARK_COLOR);

		sharkEnergy = parameters.get("energyLevel").intValue();
		timeTillReproduce = parameters.get("timeTillReproduce").intValue();
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		return null;
	}

	public Collection<Cell> updateFutureStates(Iterable<Cell> cellsToUpdate,
			BaseGraph graph) {
		for (Cell c : cellsToUpdate) {
			if (c.getCurrentState() == SHARK) {
				Shark currentShark = ((Shark) ((CellWithInhabitant) c)
						.getInhabitant());
				if (currentShark.getEnergyLevel() < sharkEnergy
						&& countNeighbors(FISH, graph.getNeighbors(c)) > 0) {
					moveShark(graph, c, currentShark, FISH);
				} else if (currentShark.getEnergyLevel() >= sharkEnergy) {
					changeStateAndInhabitant(c, new Inhabitant(WATER), WATER,
							WATER_COLOR);
				} else {
					moveShark(graph, c, currentShark, WATER);
				}
			}
		}
		for (Cell c : cellsToUpdate) {
			if (c.getCurrentState() == FISH) {
				AquaticCreature currentFish = ((AquaticCreature) ((CellWithInhabitant) c)
						.getInhabitant());
				if (countNeighbors(0, graph.getNeighbors(c)) > 0
						&& currentFish.getReproductionCounter() == timeTillReproduce) {
					currentFish.resetReproductionCounter();
					Cell cellToMoveTo = getCellToMoveTo(graph, c, WATER);
					if (cellToMoveTo != null) {
						changeStateAndInhabitant(cellToMoveTo, currentFish,
								FISH, FISH_COLOR);
						changeStateAndInhabitant(c, new AquaticCreature(FISH),
								FISH, FISH_COLOR);
					}
				} else {
					Cell cellToMoveTo = getCellToMoveTo(graph, c, WATER);
					if (cellToMoveTo != null) {
						currentFish.increaseReproductionCounter();
						changeStateAndInhabitant(cellToMoveTo, currentFish,
								FISH, FISH_COLOR);
						changeStateAndInhabitant(c, new Inhabitant(WATER),
								WATER, WATER_COLOR);
					} else {
						changeFutureState(c, FISH, FISH_COLOR);
					}
				}
			}
		}
		return (Collection<Cell>) cellsToUpdate;
	}

	private void moveShark(BaseGraph graph, Cell c, Shark currentShark,
			int stateToCheckAgainst) {
		Cell cellToMoveTo = getCellToMoveTo(graph, c, stateToCheckAgainst);
		if (cellToMoveTo != null) {
			changeSharkCounters(currentShark, stateToCheckAgainst);
			cellToMoveTo.setCurrentState(DEFAULT_STATE);
			changeStateAndInhabitant(cellToMoveTo, currentShark, SHARK,
					SHARK_COLOR);
			if (currentShark.getReproductionCounter() == timeTillReproduce) {
				currentShark.resetReproductionCounter();
				changeStateAndInhabitant(c, new Shark(SHARK), SHARK,
						SHARK_COLOR);
			} else {
				changeStateAndInhabitant(c, new Inhabitant(WATER), WATER,
						WATER_COLOR);
			}
		} else {
			changeFutureState(c, SHARK, SHARK_COLOR);
		}
	}

	private void changeSharkCounters(Shark currentShark, int nextCellState) {
		if (nextCellState == FISH)
			currentShark.increaseEnergy();
		else
			currentShark.decreaseEnergy();
		currentShark.increaseReproductionCounter();
	}

	private void changeStateAndInhabitant(Cell c, Inhabitant i, int state,
			Color stateColor) {
		((CellWithInhabitant) c).setInhabitant(i);
		changeFutureState(c, state, stateColor);
	}

	private Cell getCellToMoveTo(BaseGraph graph, Cell c, int state) {
		Cell toMove = null;
		List<Cell> myCells = new ArrayList<Cell>(graph.getNeighbors(c));
		Collections.shuffle(myCells);
		for (Cell cell : myCells) {
			if (cell.getCurrentState() == state
					&& (c.getCurrentState() != FISH || (c.getCurrentState() == FISH && cell
							.getFutureState() == state))) {
				toMove = cell;
				break;
			}
		}
		return toMove;
	}

	@Override
	public Color getDefaultColor() {
		return DEFAULT_COLOR;
	}

	@Override
	public int getDefaultState() {
		return DEFAULT_STATE;
	}

}
