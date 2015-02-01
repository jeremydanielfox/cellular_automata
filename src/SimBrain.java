import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class is responsible for running the simulation.
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

	private static final String UPLOAD_FILE_TEXT = "Upload XML File";
	private static final String PLAY_TEXT = "Play";
	private static final String PAUSE_TEXT = "Pause";
	private static final String INC_SPEED_TEXT = "+";
	private static final String DEC_SPEED_TEXT = "-";
	private static final String STEP_BUTTON_TEXT = "Step";
	private static final int NUM_FRAMES_PER_SECOND = 10;
	private static final int FRAME_SPEED_CHANGE_VALUE = 300;
	private static final int MIN_FRAME_PER_SECOND = 0;
	private static final int MAX_FRAME_PER_SECOND = 5000;
	private static final int SCREEN_BORDER_BUFFER = 50;
	private static final int CELL_REGION_WIDTH = SimWindow.WINDOW_WIDTH-2*SCREEN_BORDER_BUFFER;
	private static final int CELL_REGION_HEIGHT = SimWindow.WINDOW_HEIGHT-2*SCREEN_BORDER_BUFFER;

	@Override
	public void start(Stage s) throws Exception {
		myStage = s;
		myWindow = new SimWindow(s, makeControlPanel());
		initializeAnimationTimeline();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private HBox makeControlPanel() {
		HBox controlPanel = new HBox(10);
		controlPanel.setMaxHeight(50);
		controlPanel.setPrefWidth(SimWindow.WINDOW_WIDTH);
		controlPanel.setAlignment(Pos.BOTTOM_CENTER);
		controlPanel.getChildren().add(makeUploadFileButton());
		myPlayButton = makePlayButton();
		myPlayButton.setDisable(true);
		controlPanel.getChildren().add(myPlayButton);
		myPauseButton = makePauseButton();
		myPauseButton.setDisable(true);
		controlPanel.getChildren().add(myPauseButton);
		myIncSpeedButton = makeIncSpeedButton();
		myIncSpeedButton.setDisable(true);
		controlPanel.getChildren().add(myIncSpeedButton);
		myDecSpeedButton = makeDecSpeedButton();
		myDecSpeedButton.setDisable(true);
		controlPanel.getChildren().add(myDecSpeedButton);
		myStepButton = makeStepButton();
		myStepButton.setDisable(true);
		controlPanel.getChildren().add(myStepButton);
		return controlPanel;
	}
	
	private Button makeUploadFileButton() {
		Button uploadFile = new Button(UPLOAD_FILE_TEXT);
		uploadFile.setOnAction(e -> startNewSim());
		return uploadFile;
	}

	private File uploadFile() {
		FileChooser fileSelector = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileSelector.getExtensionFilters().add(extFilter);
		File file = fileSelector.showOpenDialog(myStage);
		return file;
	}

	private Button makePlayButton() {
		Button playButton = new Button(PLAY_TEXT);
		playButton.setOnAction(e -> playSimulation());
		return playButton;
	}

	private void playSimulation() {
		myAnimation.play();
		myPauseButton.setDisable(false);
		myPlayButton.setDisable(true);
		myIncSpeedButton.setDisable(false);
		myDecSpeedButton.setDisable(false);
	}

	private Button makePauseButton() {
		Button playButton = new Button(PAUSE_TEXT);
		playButton.setOnAction(e -> pauseSimulation());
		return playButton;
	}

	private Button makeStepButton() {
		Button pauseButton = new Button(STEP_BUTTON_TEXT);
		pauseButton.setOnAction(e -> stepSimulation());
		return pauseButton;
	}

	private void stepSimulation() {
		pauseSimulation();
		updateSim();
	}

	private void pauseSimulation() {
		myAnimation.pause();
		myPauseButton.setDisable(true);
		myPlayButton.setDisable(false);
		myIncSpeedButton.setDisable(true);
		myDecSpeedButton.setDisable(true);
	}

	private Button makeIncSpeedButton() {
		Button playButton = new Button(INC_SPEED_TEXT);
		playButton.setOnAction(e -> incSpeed());
		return playButton;
	}

	private void incSpeed() {
		myAnimation.stop();
		changeFramesPerSecondValue(-1);
		initializeAnimationTimeline();
		runSim();
		myDecSpeedButton.setDisable(false);
		System.out.println("inc speed");
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
		System.out.println(framesPerSecond);
	}

	private Button makeDecSpeedButton() {
		Button playButton = new Button(DEC_SPEED_TEXT);
		playButton.setOnAction(e -> decSpeed());
		return playButton;
	}

	private void decSpeed() {
		myAnimation.stop();
		changeFramesPerSecondValue(1);
		initializeAnimationTimeline();
		runSim();
		System.out.println("DEC SPEED");
	}

	private void runSim() {
		myWindow.paintCells(myEngine.getListOfCells());
		//myAnimation.play();
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
			readFile(modelSetUp);
		myEngine = new SimEngine(myXMLContents.getModel(),
				myXMLContents.getParams(), myXMLContents.getCellsToConfig(),
				CELL_REGION_WIDTH, CELL_REGION_HEIGHT, SCREEN_BORDER_BUFFER,
				SCREEN_BORDER_BUFFER);
		myWindow.setStageTitle(myXMLContents.getTitle() + " by "+ myXMLContents.getAuthor());
		runSim();
		//myPauseButton.setDisable(false);
		//myIncSpeedButton.setDisable(false);
		//myDecSpeedButton.setDisable(false);
		myStepButton.setDisable(false);
		myPlayButton.setDisable(false);
		}
	}

	private void readFile(File file) {
		try {
			myXMLContents = new XMLContents(file);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
