package game.states;

import engine.gui.GUIControls;
import engine.state.State;
import game.App;
import game.DataStorage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class EndState extends State {

	private Label winnerLabel = GUIControls.createLabel("Winner: Player", new Font("Arial", 16));
	
	public EndState(GameState s) {
		super(s);

		Button backToMenuButton = GUIControls.createButton("Back to Menu", 128, 32);
		
		backToMenuButton.setOnMouseClicked((e) -> {
			try {
				App.setState(GameState.STATE_MENU);				 
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});

		VBox labelContainer = new VBox();
		labelContainer.setSpacing(0);
//		labelContainer.getChildren().addAll(GUIControls.createLabel(this.getClass().getName()));
		labelContainer.getChildren().addAll(GUIControls.createLabel("Game Over", new Font("Arial", 30)));
		labelContainer.getChildren().addAll(winnerLabel);
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
		rootContainer.setAlignment(Pos.CENTER);
		
		super.stateScene = new Scene(rootContainer);
		this.getStage().setTitle("State: Game Over");
		this.getStage().setScene(super.stateScene);
		
	}
	
	@Override
	public void update() {
		winnerLabel.setText("Winner: Player " + DataStorage.getCurrentPlayerTurn());
	}
	
}
