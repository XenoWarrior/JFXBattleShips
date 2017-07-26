package states;

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

public class SetupState extends State {

	public SetupState() {

		Button backToMenuButton = GUIControls.createButton("Back to Menu", 128, 32);
		backToMenuButton.setOnMouseClicked((e) -> {
			try {
				App.setState(GameState.STATE_MENU);				 
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});
		
		HBox labelContainer = new HBox();
		labelContainer.getChildren().addAll(GUIControls.createLabel(this.getClass().getName(), new Font("Arial", 30)));
		labelContainer.setSpacing(25);
		labelContainer.setPadding(new Insets(25, 25, 0, 25));
		labelContainer.setAlignment(Pos.CENTER);

		HBox buttonContainer = new HBox();
		buttonContainer.getChildren().addAll(backToMenuButton);
		buttonContainer.setSpacing(25);
		buttonContainer.setPadding(new Insets(25, 25, 0, 25));
		buttonContainer.setAlignment(Pos.CENTER);
		
		VBox rootContainer = new VBox();
		rootContainer.setPadding(new Insets(25, 25, 25, 25));
		rootContainer.setSpacing(0);
		rootContainer.getChildren().addAll(labelContainer, buttonContainer);
        
		Scene scene = new Scene(rootContainer);
		this.getStage().setTitle("State: Board Setup");
		this.getStage().setScene(scene);
	}
}
