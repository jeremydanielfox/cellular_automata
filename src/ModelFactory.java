import java.util.Map;

public class ModelFactory {

	public BaseModel createSpecifiedModel(String model,
			Map<String, Double> parameters) {

		switch (model) {
		case "GameOfLife":
			return new GameOfLife(parameters);
		case "Fire":
			return new Fire(parameters);
		case "Segregation":
			return new Segregation(parameters);
		case "WaTorWorld":
			return new WaTorWorld(parameters);
		default:
			throw new CellSocietyException(CellSocietyException.INCORRECT_MODEL_MESSAGE);
		}

	}
}
