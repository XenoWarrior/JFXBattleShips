package game.states;

import engine.gui.GUIControls;
import engine.state.State;
import game.App;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PlayState extends State {

	public PlayState() {

		Button backToMenuButton = GUIControls.createButton("Back to Menu", 128, 32);
		Button endGameButton = GUIControls.createButton("End Game", 128, 32);
		
		backToMenuButton.setOnMouseClicked((e) -> {
			try {
				App.setState(GameState.STATE_MENU);				 
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});
		endGameButton.setOnMouseClicked((e) -> {
			try {
				App.setState(GameState.STATE_END);				 
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});

		VBox labelContainer = new VBox();
		labelContainer.setSpacing(0);
		labelContainer.getChildren().addAll(GUIControls.createLabel(this.getClass().getName()));
		labelContainer.getChildren().addAll(GUIControls.createLabel("Battleships", new Font("Arial", 30)));
		labelContainer.setPadding(new Insets(25, 25, 0, 25));
		labelContainer.setAlignment(Pos.CENTER);

		HBox buttonContainer = new HBox();
		buttonContainer.getChildren().addAll(backToMenuButton, endGameButton);
		buttonContainer.setSpacing(25);
		buttonContainer.setPadding(new Insets(25, 25, 0, 25));
		buttonContainer.setAlignment(Pos.CENTER);
		
		VBox rootContainer = new VBox();
		rootContainer.setPadding(new Insets(25, 25, 25, 25));
		rootContainer.setSpacing(0);
		rootContainer.getChildren().addAll(labelContainer, buttonContainer);
		
		Scene scene = new Scene(rootContainer);
		this.getStage().setTitle("State: Play Battleships");
		this.getStage().setScene(scene);
	}	
}
