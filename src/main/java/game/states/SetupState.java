package game.states;

import engine.gui.GUIControls;
import engine.state.State;
import game.App;
import game.DataStorage;
import game.board.Board;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SetupState extends State {

	private Board shipBoard = new Board(10);
	private Label placeLabel = GUIControls.createLabel("Ship Placement: Player " + DataStorage.getCurrentPlayerTurn(), new Font("Arial", 16));
	
	public SetupState(GameState s) {
		super(s);

		Button backToMenuButton = GUIControls.createButton("Back to Menu", 128, 32);
		Button startButton = GUIControls.createButton(DataStorage.getBoards().size() == 1 ? "Start Game" : "Ready", 128, 32);
		
		backToMenuButton.setOnMouseClicked((e) -> {
			try {
				App.setState(GameState.STATE_MENU);				 
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});
		startButton.setOnMouseClicked((e) -> {
			try {
				DataStorage.addBoard(shipBoard);
				if(DataStorage.getBoards().size() == 1) {
					DataStorage.getNextPlayerTurn();
				}
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});

		VBox labelContainer = new VBox();
		labelContainer.setSpacing(0);
		//labelContainer.getChildren().addAll(GUIControls.createLabel(this.getClass().getName()));
		labelContainer.getChildren().addAll(GUIControls.createLabel("New Game", new Font("Arial", 30)));
		labelContainer.getChildren().addAll(placeLabel);
		labelContainer.setPadding(new Insets(25, 25, 25, 25));
		labelContainer.setAlignment(Pos.CENTER);

		HBox buttonContainer = new HBox();
		buttonContainer.getChildren().addAll(startButton, backToMenuButton);
		buttonContainer.setSpacing(25);
		buttonContainer.setPadding(new Insets(25, 25, 0, 25));
		buttonContainer.setAlignment(Pos.CENTER);
		
		Board boardContainer = new Board(10);
		boardContainer.boardColumns().setSpacing(25);
		boardContainer.boardColumns().setPadding(new Insets(25, 25, 0, 25));
		boardContainer.boardColumns().setAlignment(Pos.CENTER);
		
		VBox rootContainer = new VBox();
		rootContainer.setPadding(new Insets(25, 25, 25, 25));
		rootContainer.setSpacing(0);
		rootContainer.getChildren().addAll(labelContainer, new Board(10).boardColumns(), buttonContainer);
		rootContainer.setAlignment(Pos.CENTER);
        
		super.stateScene = new Scene(rootContainer);
		this.getStage().setTitle("State: Board Setup");
		this.getStage().setScene(super.stateScene);
	}
	
	@Override
	public void update() { 
		placeLabel.setText("Ship Placement: Player " + DataStorage.getCurrentPlayerTurn());
		
		if(DataStorage.getBoards().size() == 2) {
			
		}
		
	}
}
