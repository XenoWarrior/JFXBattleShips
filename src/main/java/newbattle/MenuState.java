package newbattle;

import engine.GUIControls;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuState extends State {

	public MenuState() {
		Button startButton = GUIControls.createButton("Start", 64, 32);
		Button playButton = GUIControls.createButton("Test Play", 64, 32);
		Button initButton = GUIControls.createButton("Test Init", 64, 32);
		Button exitButton = GUIControls.createButton("Exit", 64, 32);
		
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
				App.setState(GameState.STATE_INIT);				 
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});
		exitButton.setOnMouseClicked((e) -> {
			Platform.exit();
		});
		
		HBox buttonContainer = new HBox();
		buttonContainer.getChildren().addAll(startButton, playButton, initButton, exitButton);
		buttonContainer.setSpacing(25);
		buttonContainer.setPadding(new Insets(25, 25, 0, 25));
		buttonContainer.setAlignment(Pos.CENTER);
		
		HBox labelContainer = new HBox();
		labelContainer.getChildren().addAll(GUIControls.createLabel("BattleShips", new Font("Arial", 30)));
		labelContainer.setSpacing(25);
		labelContainer.setPadding(new Insets(25, 25, 0, 25));
		labelContainer.setAlignment(Pos.CENTER);
		
		VBox rootContainer = new VBox();
		rootContainer.setPadding(new Insets(25, 25, 25, 25));
		rootContainer.setSpacing(0);
		rootContainer.getChildren().addAll(labelContainer, buttonContainer);
		buttonContainer.setAlignment(Pos.CENTER);
        
		Scene scene = new Scene(rootContainer);
		super.getStage().setTitle("State: Main Menu");
		super.getStage().setScene(scene);
	}

}
