package Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import CellsAndComponents.Inhabitant;
import CellsAndComponents.Sugar;
import CellsAndComponents.Agent;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import CellsAndComponents.Cell;
import CellsAndComponents.AdvancedCell;
import Graphs.BaseGraph;

/**
 * This class implements the SugarScape model and extends BaseModel.
 * 
 * @author Megan Gutter and Sierra Smith
 *
 */

public class Sugarscape extends BaseModel {
	private static final int SUGAR_GROW_BACK_RATE = 1;
	private static final int SUGAR_GROW_BACK_INTERVAL = 1;
	private int sugarGrowCounter;

	public Sugarscape(Map<String, Double> parameters, int points) {
		super(parameters, 2);
		sugarGrowCounter = 0;
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		return null;
	}

	public Collection<Cell> updateFutureStates(Iterable<Cell> cellsToUpdate,
			BaseGraph graph) {
		ArrayList<Cell> shuffledCells = new ArrayList<Cell>((Collection) cellsToUpdate);
		Collections.shuffle(shuffledCells);
		for (Cell c : shuffledCells) {
			AdvancedCell curCell = (AdvancedCell) c;
			if (curCell.getNumInhabitants() > 0) {
				ArrayList<AdvancedCell> possibleCells = (ArrayList<AdvancedCell>) getVacantPatchesInSight(
						curCell, graph);
				ArrayList<AdvancedCell> possibleMaxSugar = (ArrayList<AdvancedCell>) findMaxSugarCells(possibleCells);
				AdvancedCell toMoveTo = getClosest(
						possibleMaxSugar, curCell, graph);
				moveAgent(toMoveTo, curCell);
				((Agent) toMoveTo.getInhabitants().get(0))
						.addSugar(((Sugar) toMoveTo.getPatch())
								.getSugarAmount());
				((Sugar) toMoveTo.getPatch()).takeAllSugar();
				if (((Agent) toMoveTo.getInhabitants().get(0)).checkDead()) {
					toMoveTo.getInhabitants().remove(0);
				}
			}
		}
		sugarGrowCounter += 1;
		if (sugarGrowCounter == SUGAR_GROW_BACK_INTERVAL) {
			sugarGrowCounter = 0;
			for (Cell c : shuffledCells) {
				AdvancedCell curCell = (AdvancedCell) c;
				((Sugar)curCell.getPatch()).sugarGrowBack(SUGAR_GROW_BACK_RATE);
			}
		}
		return shuffledCells;
	}

	private Collection<AdvancedCell> getVacantPatchesInSight(
			AdvancedCell curCell, BaseGraph graph) {
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

	private AdvancedCell checkIfVacant(
			AdvancedCell curCell, BaseGraph graph, int x, int y) {
		Point2D change = new Point2D(x, y);
		AdvancedCell possible = (AdvancedCell) graph
				.getTranslatedCell(curCell, change);
		if (possible != null && possible.getNumInhabitants() == 0) {
			return possible;
		}
		return null;
	}

	private Collection<AdvancedCell> findMaxSugarCells(
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

	private AdvancedCell getClosest(
			List<AdvancedCell> possibleCells,
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

	private void moveAgent(AdvancedCell newCell,
			AdvancedCell oldCell) {
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

}
