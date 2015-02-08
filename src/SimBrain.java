import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Polygon;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import visuals.CellRegionDivider;
import Exceptions.CellSocietyException;
import Exceptions.PopUpWindow;
import Factories.CellRegionDividerFactory;

/**
 * This class is responsible for running the simulation and acts as the
 * "controller." It makes sure the XML file is read, controls the time line of
 * the simulation animation, and updates the simulation and the view for each
 * frame update.
 * 
 * @author Sierra Smith
 *
 */
public class SimBrain extends Application {
	private SimWindow myWindow;
	private SimEngine myEngine;
	private Timeline myAnimation;
	private XMLContents myXMLContents;
	private Stage myStage;
	private Button myPlayButton;
	private Button myPauseButton;
	private Button myIncSpeedButton;
	private Button myDecSpeedButton;
	private Button myStepButton;
	private int framesPerSecond = 2500;
	private ResourceBundle myResources;

	private static final int NUM_FRAMES_PER_SECOND = 10;
	private static final int FRAME_SPEED_CHANGE_VALUE = 300;
	private static final int MIN_FRAME_PER_SECOND = 0;
	private static final int MAX_FRAME_PER_SECOND = 5000;
	private static final int SCREEN_BORDER_BUFFER = 50;
	private static final int CELL_REGION_WIDTH = SimWindow.WINDOW_WIDTH - 2
			* SCREEN_BORDER_BUFFER;
	private static final int CELL_REGION_HEIGHT = SimWindow.WINDOW_HEIGHT - 2
			* SCREEN_BORDER_BUFFER;
	private static final int INITIAL_FRAME_RATE = 2500;
	public static final int CONTROL_PANEL_BUTTON_SPACING = 10;
	public static final int CONTROL_PANEL_MAX_HEIGHT = 50;
	private static final int DEC_FRAME_RATE_MULTIPLIER = 1;
	private static final int INC_FRAME_RATE_MULTIPLIER = -1;

	@Override
	public void start(Stage s) throws Exception {
		myStage = s;
		myResources = ResourceBundle.getBundle("resources/English");
		myWindow = new SimWindow(s, makeControlPanel(),
				myResources.getString("InitialWindowTitle"));
		initializeAnimationTimeline();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private HBox makeControlPanel() {
		HBox controlPanel = new HBox(CONTROL_PANEL_BUTTON_SPACING);
		controlPanel.setMaxHeight(CONTROL_PANEL_MAX_HEIGHT);
		controlPanel.setPrefWidth(SimWindow.WINDOW_WIDTH);
		controlPanel.setAlignment(Pos.BOTTOM_CENTER);
		Button uploadFileButton = makeButton(
				myResources.getString("UploadButtonText"), false);
		uploadFileButton.setOnAction(e -> startNewSim());
		controlPanel.getChildren().add(uploadFileButton);
		myPlayButton = makeButton(myResources.getString("PlayButtonText"), true);
		myPlayButton.setOnAction(e -> playSimulation());
		controlPanel.getChildren().add(myPlayButton);
		myPauseButton = makeButton(myResources.getString("PauseButtonText"),
				true);
		myPauseButton.setOnAction(e -> pauseSimulation());
		controlPanel.getChildren().add(myPauseButton);
		myIncSpeedButton = makeButton(
				myResources.getString("IncSpeedButtonText"), true);
		myIncSpeedButton
				.setOnAction(e -> changeFrameSpeed(INC_FRAME_RATE_MULTIPLIER));
		controlPanel.getChildren().add(myIncSpeedButton);
		myDecSpeedButton = makeButton(
				myResources.getString("DecSpeedButtonText"), true);
		myDecSpeedButton
				.setOnAction(e -> changeFrameSpeed(DEC_FRAME_RATE_MULTIPLIER));
		controlPanel.getChildren().add(myDecSpeedButton);
		myStepButton = makeButton(myResources.getString("StepButtonText"), true);
		myStepButton.setOnAction(e -> stepSimulation());
		controlPanel.getChildren().add(myStepButton);
		return controlPanel;
	}

	private Button makeButton(String text, boolean disabled) {
		Button newButton = new Button(text);
		newButton.setDisable(disabled);
		return newButton;
	}

	private File uploadFile() {
		FileChooser fileSelector = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileSelector.getExtensionFilters().add(extFilter);
		File file = fileSelector.showOpenDialog(myStage);
		return file;
	}

	private void playSimulation() {
		myAnimation.play();
		enableCorrectButtons(true);
	}

	private void enableCorrectButtons(boolean isPlaying) {
		myPauseButton.setDisable(!isPlaying);
		myPlayButton.setDisable(isPlaying);
	}

	private void stepSimulation() {
		pauseSimulation();
		updateSim();
	}

	private void pauseSimulation() {
		myAnimation.pause();
		enableCorrectButtons(false);
	}

	private void changeFrameSpeed(int speedChangeMultiplier) {
		Animation.Status previousStatus = myAnimation.getStatus();
		myAnimation.stop();
		changeFramesPerSecondValue(speedChangeMultiplier);
		if (speedChangeMultiplier < 0)
			myDecSpeedButton.setDisable(false);
		else
			myIncSpeedButton.setDisable(false);
		initializeAnimationTimeline();
		paintSim();
		restoreLastAnimationStatus(previousStatus);

	}

	private void restoreLastAnimationStatus(Animation.Status prevStatus) {
		if (prevStatus.equals(Animation.Status.RUNNING)) {
			playSimulation();
			enableCorrectButtons(true);
		} else if (prevStatus.equals(Animation.Status.PAUSED)) {
			enableCorrectButtons(false);
		}
	}

	public void changeFramesPerSecondValue(int i) {
		if (framesPerSecond + i * FRAME_SPEED_CHANGE_VALUE <= MIN_FRAME_PER_SECOND) {
			myIncSpeedButton.setDisable(true);
			return;
		} else if (framesPerSecond + i * FRAME_SPEED_CHANGE_VALUE >= MAX_FRAME_PER_SECOND) {
			myDecSpeedButton.setDisable(true);
			return;
		}
		framesPerSecond += i * FRAME_SPEED_CHANGE_VALUE;
	}

	private void paintSim() {
		myWindow.paintCells(myEngine.getListOfCells());
	}

	private KeyFrame makeKeyFrame(int frameRate) {
		return new KeyFrame(Duration.millis(framesPerSecond / frameRate),
				e -> updateSim());
	}

	private void initializeAnimationTimeline() {
		KeyFrame frame = makeKeyFrame(NUM_FRAMES_PER_SECOND);
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Animation.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
	}

	private void updateSim() {
		myEngine.updateCells();
	}

	private void startNewSim() {
		File modelSetUp = uploadFile();
		if (modelSetUp != null) {
			try {
				readFile(modelSetUp);
				CellRegionDividerFactory myDividerFactory = new CellRegionDividerFactory();
				int cellsAcross = (myXMLContents.getParams().get("columns"))
						.intValue();
				int cellsVertical = (myXMLContents.getParams().get("rows"))
						.intValue();
				CellRegionDivider myDivider = myDividerFactory
						.createSpecifiedDivider(cellsAcross, cellsVertical,
								CELL_REGION_HEIGHT, CELL_REGION_WIDTH,
								SCREEN_BORDER_BUFFER, SCREEN_BORDER_BUFFER,
								myXMLContents.getGraphType());
				Polygon[][] myPolygons = myDivider.divideSpace(myXMLContents.getGridLines());
				myEngine = new SimEngine(myPolygons, myXMLContents.getModel(), myXMLContents.getGraphType(),
						myXMLContents.getEdgeType(), myXMLContents.getParams(),
						myXMLContents.getCellsToConfig(), CELL_REGION_WIDTH,
						CELL_REGION_HEIGHT);
			} catch (CellSocietyException error) {
				PopUpWindow myErrorWindow = new PopUpWindow();
				myErrorWindow.setDisplayMessage(error.getErrorMessage());
				myErrorWindow.displayError();
				return;
			}
			myWindow.setStageTitle(myXMLContents.getTitle() + " by "
					+ myXMLContents.getAuthor());
			myAnimation.stop();
			framesPerSecond = INITIAL_FRAME_RATE;
			initializeAnimationTimeline();
			paintSim();
			myStepButton.setDisable(false);
			myIncSpeedButton.setDisable(false);
			myDecSpeedButton.setDisable(false);
			enableCorrectButtons(false);
			Map<String, ArrayList<Double>> paramMap = myEngine.getParamMap();
			myWindow.clearControlPanel();
			if (paramMap.keySet().size() > 0) {
				ParameterControlBox myParamControls = new ParameterControlBox(
						this, paramMap);
				myWindow.addControlPanel(myParamControls.getParameterControls());
			}
		}
	}

	private void readFile(File file) {
		try {
			myXMLContents = new XMLContents(file);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	public void updateParameter(String paramName, Double paramValue) {
		Animation.Status previousStatus = myAnimation.getStatus();
		myAnimation.pause();
		myEngine.changeParam(paramName, paramValue);
		restoreLastAnimationStatus(previousStatus);
	}
}
