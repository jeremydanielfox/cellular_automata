package visuals;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class ChartMaster {
	private static final NumberAxis X_AXIS = new NumberAxis();
	private static final NumberAxis Y_AXIS = new NumberAxis();
	private static final LineChart<Number, Number> MY_CHART = new LineChart<>(
			X_AXIS, Y_AXIS);
	private static final String X_AXIS_LABEL = "Time";
	private static final String Y_AXIS_LABEL = "Population";
}
