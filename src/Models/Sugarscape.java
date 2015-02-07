package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import CellsAndComponents.AdvancedCell;
import CellsAndComponents.Agent;
import CellsAndComponents.Cell;
import CellsAndComponents.Inhabitant;
import CellsAndComponents.Patch;
import CellsAndComponents.Sugar;
import Graphs.BaseGraph;
import Graphs.ConfigCellInfo;

/**
 * This class implements the SugarScape model and extends BaseModel.
 * 
 * @author Megan Gutter and Sierra Smith
 *
 */

public class Sugarscape extends BaseModel {
	private static final int SUGAR_GROW_BACK_RATE = 1;
	private static final int SUGAR_GROW_BACK_INTERVAL = 1;
	private static final int WITH_AGENT = -1;
	private static final Color WITH_AGENT_COLOR = Color.RED;
	private static final double MIN_NUM_AGENTS = 0;
	private static final double MAX_NUM_AGENTS = 100;
	private static final int DEFAULT_MAX_SUGAR = 10;
	private int sugarGrowCounter;

	public Sugarscape(Map<String, Double> parameters) {
		super(parameters, 2);
		List<String> myStates = new ArrayList<String>(Arrays.asList("agent"));
		List<Color> myColors = new ArrayList<>(Arrays.asList(WITH_AGENT_COLOR));
		List<Integer> myInts = new ArrayList<>(Arrays.asList(WITH_AGENT));
		initializeMaps(myStates, myInts, myColors);
		sugarGrowCounter = 0;
		try {
			getParameterValuesMap().put("numAgents",
					parameters.get("numAgents"));
		} catch (NullPointerException e) {
			getParameterValuesMap().put("numAgents",
					(MIN_NUM_AGENTS + MAX_NUM_AGENTS) / 2);
		}
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		return null;
	}

	public Collection<Cell> updateFutureStates(Iterable<Cell> cellsToUpdate,
			BaseGraph graph) {
		ArrayList<Cell> shuffledCells = new ArrayList<Cell>();
		for (Cell c : cellsToUpdate) {
			shuffledCells.add(c);
		}
		// (ArrayList<Cell>) cellsToUpdate);
		Collections.shuffle(shuffledCells);
		for (Cell c : shuffledCells) {
			AdvancedCell curCell = (AdvancedCell) c;
			if (curCell.getNumInhabitants() > 0) {
				ArrayList<AdvancedCell> possibleCells = (ArrayList<AdvancedCell>) getVacantPatchesInSight(
						curCell, graph);
				ArrayList<AdvancedCell> possibleMaxSugar = (ArrayList<AdvancedCell>) findMaxSugarCells(possibleCells);
				AdvancedCell toMoveTo = getClosest(possibleMaxSugar, curCell,
						graph);
				if (toMoveTo != null) {
					moveAgent(toMoveTo, curCell);
					((Agent) toMoveTo.getInhabitants().get(0))
							.addSugar(((Sugar) toMoveTo.getPatch())
									.getSugarAmount());
					((Sugar) toMoveTo.getPatch()).takeAllSugar();
					if (((Agent) toMoveTo.getInhabitants().get(0)).checkDead()) {
						toMoveTo.getInhabitants().remove(0);
					} else {
						changeFutureState(toMoveTo, WITH_AGENT,
								WITH_AGENT_COLOR);
					}
				}
			}
		}
		sugarGrowCounter += 1;
		for (Cell c : shuffledCells) {
			AdvancedCell curCell = (AdvancedCell) c;
			if (sugarGrowCounter >= SUGAR_GROW_BACK_INTERVAL) {
				((Sugar) curCell.getPatch())
						.sugarGrowBack(SUGAR_GROW_BACK_RATE);
			}
			if (curCell.getNumInhabitants() == 0) {
				// change Orange later to be based off of amount
				changeFutureState(curCell,
						((Sugar) curCell.getPatch()).getSugarAmount(),
						Color.ORANGE);
			}
		}
		return shuffledCells;
	}

	@Override
	public void setUpCellContents(BaseGraph graph,
			Iterable<ConfigCellInfo> cellsToConfig) {

		for (Cell c : graph.getAllCells()) {
			AdvancedCell cell = (AdvancedCell) c;
			cell.setPatch(new Sugar(DEFAULT_MAX_SUGAR));
		}

		for (ConfigCellInfo c : cellsToConfig) {
			try {
				c.setIntState(Integer.parseInt(c.getStringState()));
			} catch (NumberFormatException e) {
				c.setIntState(DEFAULT_MAX_SUGAR);
			}
			updateStateOfCell(graph, c,
					calculateColorForSugarLevel(c.getIntState()));
		}

		ArrayList<Cell> shuffledCells = new ArrayList<Cell>();
		for (Cell c : graph.getAllCells()) {
			shuffledCells.add(c);
		}
		Collections.shuffle(shuffledCells);
		for (int i = 0; i < getParameterValuesMap().get("numAgents"); i++) {
			AdvancedCell toAddto = (AdvancedCell) shuffledCells.get(i);
			toAddto.addInhabitant(new Agent(WITH_AGENT));
			toAddto.setCurrentState(getIntForState("agent"));
		}
	}

	@Override
	public void addAdditionalCellInfo(Cell c, ConfigCellInfo myBabyCell) {
		AdvancedCell curCell = (AdvancedCell) c;
		Patch newPatch = new Sugar(myBabyCell.getIntState());
		curCell.setPatch(newPatch);
	}

	private Color calculateColorForSugarLevel(int sugarLevel) {
		return Color.ORANGE;
	}

	private List<AdvancedCell> getVacantPatchesInSight(AdvancedCell curCell,
			BaseGraph graph) {
		int vision = ((Agent) curCell.getInhabitants().get(0)).getVision();
		List<AdvancedCell> possibleCells = new ArrayList<AdvancedCell>();
		for (int i = 1; i <= vision; i++) {
			if (checkIfVacant(curCell, graph, i, 0) != null) {
				possibleCells.add(checkIfVacant(curCell, graph, i, 0));
			}
			if (checkIfVacant(curCell, graph, -i, 0) != null) {
				possibleCells.add(checkIfVacant(curCell, graph, -i, 0));
			}
			if (checkIfVacant(curCell, graph, 0, i) != null) {
				possibleCells.add(checkIfVacant(curCell, graph, 0, i));
			}
			if (checkIfVacant(curCell, graph, 0, -i) != null) {
				possibleCells.add(checkIfVacant(curCell, graph, 0, -i));
			}
		}
		return possibleCells;
	}

	private AdvancedCell checkIfVacant(AdvancedCell curCell, BaseGraph graph,
			int x, int y) {
		Point2D change = new Point2D(x, y);
		AdvancedCell possible = (AdvancedCell) graph.getTranslatedCell(curCell,
				change);
		if (possible != null && possible.getNumInhabitants() == 0) {
			return possible;
		}
		return null;
	}

	private List<AdvancedCell> findMaxSugarCells(
			ArrayList<AdvancedCell> possibleCells) {
		int maxSugar = 0;
		for (AdvancedCell curCell : possibleCells) {
			if (((Sugar) curCell.getPatch()).getSugarAmount() > maxSugar) {
				maxSugar = ((Sugar) curCell.getPatch()).getSugarAmount();
			}
		}
		ArrayList<AdvancedCell> possibleMaxSugar = new ArrayList<AdvancedCell>();
		for (AdvancedCell curCell : possibleCells) {
			if (((Sugar) curCell.getPatch()).getSugarAmount() == maxSugar) {
				possibleMaxSugar.add(curCell);
			}
		}
		return possibleMaxSugar;
	}

	private AdvancedCell getClosest(List<AdvancedCell> possibleCells,
			AdvancedCell curCell, BaseGraph graph) {
		Point2D curPoint = graph.getPointFromCell(curCell);
		double maxDistance = Double.MAX_VALUE;
		AdvancedCell toReturn = null;
		for (AdvancedCell c : possibleCells) {
			double cellDistance = curPoint.distance(graph.getPointFromCell(c));
			if (cellDistance < maxDistance) {
				toReturn = c;
				maxDistance = cellDistance;
			}
		}
		return toReturn;
	}

	private void moveAgent(AdvancedCell newCell, AdvancedCell oldCell) {
		Inhabitant toMove = oldCell.getInhabitants().get(0);
		newCell.addInhabitant(toMove);
		oldCell.getInhabitants().remove(0);
	}

	@Override
	public Color getDefaultColor() {
		return null;
	}

	@Override
	public int getDefaultState() {
		return 0;
	}

	@Override
	// error check?
	public int getIntForState(String state) {
		if (state.equals("agent")) {
			return getStateToIntMap().get(state);
		}
		return Integer.parseInt(state);
	}

}
