package game.states;

import engine.gui.GUIControls;
import engine.state.State;
import game.App;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuState extends State {

	public MenuState(GameState s) {
		super(s);
		
		Button startButton = GUIControls.createButton("New Game", 64, 32);
		Button playButton = GUIControls.createButton("Test Play", 64, 32);
		Button initButton = GUIControls.createButton("Test Init", 64, 32);
		Button exitButton = GUIControls.createButton("Exit Game", 64, 32);
		
		startButton.setOnMouseClicked((e) -> {
			try {
				App.setState(GameState.STATE_SETUP);				 
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});
		playButton.setOnMouseClicked((e) -> {
			try {
				App.setState(GameState.STATE_PLAY);				 
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});
		initButton.setOnMouseClicked((e) -> {
			try {
				App.startTime = System.nanoTime();
				App.runTimeVal = 0;
				
				App.setState(GameState.STATE_INIT);
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});
		exitButton.setOnMouseClicked((e) -> {
			Platform.exit();
		});

		VBox labelContainer = new VBox();
		labelContainer.setSpacing(0);
		labelContainer.getChildren().addAll(GUIControls.createLabel(this.getClass().getName()));
		labelContainer.getChildren().addAll(GUIControls.createLabel("Main Menu", new Font("Arial", 30)));
		labelContainer.setPadding(new Insets(25, 25, 0, 25));
		labelContainer.setAlignment(Pos.CENTER);
		
		HBox buttonContainer = new HBox();
		buttonContainer.setSpacing(25);
		buttonContainer.getChildren().addAll(startButton, playButton, initButton, exitButton);
		buttonContainer.setPadding(new Insets(25, 25, 0, 25));
		buttonContainer.setAlignment(Pos.CENTER);

		VBox rootContainer = new VBox();
		rootContainer.setPadding(new Insets(25, 25, 25, 25));
		rootContainer.setSpacing(0);
		rootContainer.getChildren().addAll(labelContainer, buttonContainer);
		rootContainer.setAlignment(Pos.CENTER);
        
		super.stateScene = new Scene(rootContainer);
		super.getStage().setTitle("State: Main Menu");
		super.getStage().setScene(super.stateScene);
	}

}
