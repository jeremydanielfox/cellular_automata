import java.util.ArrayList;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;


public class ParameterControlBox {
	private HBox myParameterControls;
	private ArrayList<String> myParams;
	private ArrayList<Slider> mySliders;
	private SimBrain myBrain;

	public ParameterControlBox(SimBrain brain, Map<String, ArrayList<Double>> paramsToAdd){
		myBrain = brain;
		myParameterControls = new HBox(SimBrain.CONTROL_PANEL_BUTTON_SPACING);
		myParameterControls.setMaxHeight(SimBrain.CONTROL_PANEL_MAX_HEIGHT);
		myParameterControls.setPrefWidth(SimWindow.WINDOW_WIDTH);
		myParameterControls.setAlignment(Pos.BOTTOM_CENTER);
		myParams = new ArrayList<String>(paramsToAdd.keySet());
		generateHBox(paramsToAdd);
	}

	private void generateHBox(Map<String, ArrayList<Double>> paramMap){
		mySliders = new ArrayList<>();
		for(int i = 0; i < myParams.size(); i++){
			myParameterControls.getChildren().add(new Label(myParams.get(i) + ": "));
			Slider curSlider = new Slider(paramMap.get(myParams.get(i)).get(0),
					paramMap.get(myParams.get(i)).get(1),paramMap.get(myParams.get(i)).get(2));
			curSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
				public void changed( ObservableValue<? extends Boolean> observableValue,
						Boolean wasChanging,Boolean changing) {
					if(!changing)
						paramChanged(curSlider);
				}
			});
			mySliders.add(i, curSlider);
			myParameterControls.getChildren().add(mySliders.get(i));
		}
	}

	private void paramChanged(Slider activeSlider){
		myBrain.updateParameter(myParams.get(mySliders.indexOf(activeSlider)), activeSlider.getValue());
	}

	public HBox getParameterControls(){
		return myParameterControls;
	}

}
