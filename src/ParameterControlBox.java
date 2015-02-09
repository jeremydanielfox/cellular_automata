import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

/**
 * The purpose of this class is to create a HBOX that can be added to a visual
 * display that allows users to change the values of certain parameters that are
 * passed into the constructor of this class. The class requires that a map of
 * string parameter names to a list of the min, max and current double values of
 * the parameter and will fail if any of these values are null. It is also
 * dependent upon the SimBrain class, whose methods and constants it uses.
 * 
 * @author Sierra Smith
 *
 */
public class ParameterControlBox {
	private HBox myParameterControls;
	private ArrayList<String> myParams;
	private ArrayList<Slider> mySliders;
	private SimBrain myBrain;

	public ParameterControlBox(SimBrain brain,
			Map<String, List<Double>> paramMap) {
		myBrain = brain;
		myParameterControls = new HBox(SimBrain.CONTROL_PANEL_BUTTON_SPACING);
		myParameterControls.setMaxHeight(SimBrain.CONTROL_PANEL_MAX_HEIGHT);
		myParameterControls.setPrefWidth(SimWindow.WINDOW_WIDTH);
		myParameterControls.setAlignment(Pos.BOTTOM_CENTER);
		myParams = new ArrayList<String>(paramMap.keySet());
		generateHBox(paramMap);
	}

	private void generateHBox(Map<String, List<Double>> paramMap) {
		mySliders = new ArrayList<>();
		for (int i = 0; i < myParams.size(); i++) {
			myParameterControls.getChildren().add(
					new Label(myParams.get(i) + ": "));
			Slider curSlider = new Slider(paramMap.get(myParams.get(i)).get(0),
					paramMap.get(myParams.get(i)).get(1), paramMap.get(
							myParams.get(i)).get(2));
			curSlider.valueChangingProperty().addListener(
					new ChangeListener<Boolean>() {
						public void changed(
								ObservableValue<? extends Boolean> observableValue,
								Boolean wasChanging, Boolean changing) {
							if (!changing)
								paramChanged(curSlider);
						}
					});
			mySliders.add(i, curSlider);
			myParameterControls.getChildren().add(mySliders.get(i));
		}
	}

	private void paramChanged(Slider activeSlider) {
		myBrain.updateParameter(myParams.get(mySliders.indexOf(activeSlider)),
				activeSlider.getValue());
	}

	public HBox getParameterControls() {
		return myParameterControls;
	}

}
