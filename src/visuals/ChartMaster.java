package visuals;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * This is the class that will be responsible for the live graphing of number of
 * cells of specific types on screen. It contains a JavaFX Chart object that
 * will be displayed on screen.
 * 
 * @author Jeremy
 *
 */
public class ChartMaster {
	private static final NumberAxis X_AXIS = new NumberAxis();
	private static final NumberAxis Y_AXIS = new NumberAxis();
	private static final LineChart<Number, Number> MY_CHART = new LineChart<>(
			X_AXIS, Y_AXIS);
	private int myCount = 0;
	private int numSeries;
	private List<XYChart.Series> mySeries = new ArrayList<>();
	private String xAxisLabel;
	private String yAxisLabel;

	public void initializeChart(String myTitle, String xlabel, String ylabel,
			String[] seriesNames, int xOffset, int yOffset) {
		MY_CHART.setTitle(myTitle);
		xAxisLabel = xlabel;
		yAxisLabel = ylabel;
		numSeries = seriesNames.length;
		for (int i = 0; i < numSeries; i++) {
			XYChart.Series temp = new XYChart.Series();
			temp.setName(seriesNames[i]);
			mySeries.add(temp);
		}
		MY_CHART.setTranslateY(yOffset);
		MY_CHART.setTranslateX(xOffset);
		MY_CHART.setData(FXCollections.observableArrayList(mySeries));
	}

	/**
	 * Given an int array, adds points to the graph with the y value being the
	 * int value in the array, the x value being the iteration of the
	 * simulation, and adds the point to the series number that matches the
	 * index of the value in the array
	 * 
	 * @param myData
	 */
	public void addData(int[] myData) {
		if (myData.length != numSeries) {
			System.out.println("Amount of data doesn't equal number of series");
			return;
		}
		for (int i = 0; i < myData.length; i++) {
			mySeries.get(i).getData().add(new XYChart.Data(myCount, myData[i]));
		}
		myCount++;

	}

	public LineChart<Number, Number> getChart() {
		return MY_CHART;
	}
}
