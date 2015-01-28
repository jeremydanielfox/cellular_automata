import java.io.File;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class is responsible for running the simulation.
 * @author Sierra Smith
 *
 */
public class SimBrain extends Application {
	private SimWindow myWindow;
	//private SimEngine myEngine;
	private Timeline myAnimation;
	
	private static final String UPLOAD_FILE_TEXT = "Upload XML File";
	private static final String PLAY_TEXT = "Play";
	private static final String PAUSE_TEXT = "Pause";
	private static final String INC_SPEED_TEXT = "+";
	private static final String DEC_SPEED_TEXT = "-";
	private static final int NUM_FRAMES_PER_SECOND = 60;
	private static final int FRAMES_PER_SECOND = 1000;
	
	@Override
	public void start(Stage s) throws Exception {
		myWindow = new SimWindow(s, makeControlPanel());
	}
	
	public static void main (String[] args) {
		launch(args);
	}

	private HBox makeControlPanel(){
		HBox controlPanel = new HBox(10);
		controlPanel.getChildren().add(makeUploadFileButton());
		controlPanel.getChildren().add(makePlayButton());
		controlPanel.getChildren().add(makePauseButton());
		controlPanel.getChildren().add(makeIncSpeedButton());
		controlPanel.getChildren().add(makeDecSpeedButton());
		return controlPanel;
	}
	
	private Button makeUploadFileButton(){
		Button uploadFile = new Button(UPLOAD_FILE_TEXT);
		uploadFile.setOnAction(e -> startNewSim());
		return uploadFile;
	}
	
	private void uploadFile(){
		//use file chooser to let user uplaod a file
		System.out.println("upload");
	}
	
	private Button makePlayButton(){
		Button playButton = new Button (PLAY_TEXT);
		playButton.setOnAction(e -> playSimulation());
		return playButton;
	}
	
	private void playSimulation(){
		System.out.println("hit play");
	}
	
	private Button makePauseButton(){
		Button playButton = new Button (PAUSE_TEXT);
		playButton.setOnAction(e -> pauseSimulation());
		return playButton;
	}
	
	private void pauseSimulation(){
		System.out.println("hit pause");
	}
	
	private Button makeIncSpeedButton(){
		Button playButton = new Button (INC_SPEED_TEXT);
		playButton.setOnAction(e -> incSpeed());
		return playButton;
	}
	
	private void incSpeed(){
		System.out.println("inc speed");
	}
	
	private Button makeDecSpeedButton(){
		Button playButton = new Button (DEC_SPEED_TEXT);
		playButton.setOnAction(e -> decSpeed());
		return playButton;
	}
	
	private void decSpeed(){
		System.out.println("DEC SPEED");
	}

	private void runSim(){
		KeyFrame frame = makeKeyFrame(NUM_FRAMES_PER_SECOND);
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Animation.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
		myAnimation.play();
	}
	
	private KeyFrame makeKeyFrame (int frameRate) {
		return new KeyFrame(Duration.millis(FRAMES_PER_SECOND / frameRate), e -> updateSim());
	}
	
	private void updateSim(){
		//myWindow.paintCells(myEngine.upDateCells());
	}
	
	private void startNewSim(){
		uploadFile();
		readFile();
		//construct game engine and pass parameters from XML
		runSim();
	}
	
	private void readFile(){
		//return parameters from file
		System.out.println("read file");
	}
	
	
}
