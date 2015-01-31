import java.util.Map;

public class ModelFactory {

	public BaseModel createSpecifiedModel(String model,
			Map<String, Double> parameters) {

		switch (model) {
			case "GameOfLife":
				return new GameOfLife(parameters);
			default:
				return new GameOfLife(parameters);
		}

	}

}
