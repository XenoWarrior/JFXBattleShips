package game.states;

import engine.gui.GUIControls;
import engine.state.State;
import game.App;
import game.DataStorage;
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
		
		Button startButton = GUIControls.createButton("New Game", 128, 32);
		Button exitButton = GUIControls.createButton("Exit Game", 128, 32);
		
		startButton.setOnMouseClicked((e) -> {
			try {
				App.setState(GameState.STATE_SETUP);				 
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
//		labelContainer.getChildren().addAll(GUIControls.createLabel(this.getClass().getName()));
		labelContainer.getChildren().addAll(GUIControls.createLabel("Main Menu", new Font("Arial", 30)));
		labelContainer.setPadding(new Insets(25, 25, 0, 25));
		labelContainer.setAlignment(Pos.CENTER);
		
		HBox buttonContainer = new HBox();
		buttonContainer.setSpacing(25);
		buttonContainer.getChildren().addAll(startButton, exitButton);
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

	@Override
	public void update() {
		DataStorage.reset();
	}
	
}
