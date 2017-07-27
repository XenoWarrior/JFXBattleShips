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

public class PlayState extends State {

	private Label statusLabel = GUIControls.createLabel("Turn: Player X", new Font("Arial", 16));
	private Label stateLabel = GUIControls.createLabel("Battleships", new Font("Arial", 30));
	private int currentTurn = 0;
	private VBox rootContainer;
	private HBox buttonContainer;
	private VBox labelContainer;
	
	public PlayState(GameState s) {
		super(s);
		
		Button backToMenuButton = GUIControls.createButton("Back to Menu", 128, 32);
		Button endGameButton = GUIControls.createButton("End Game", 128, 32);
		
		backToMenuButton.setOnMouseClicked((e) -> {
			try {
				this.currentTurn = 0;
				App.setState(GameState.STATE_MENU);				 
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});
		endGameButton.setOnMouseClicked((e) -> {
			try {
				this.currentTurn = 0;
				App.setState(GameState.STATE_END);				 
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});

		labelContainer = new VBox();
		labelContainer.setSpacing(0);
//		labelContainer.getChildren().addAll(GUIControls.createLabel(this.getClass().getName()));
		labelContainer.getChildren().addAll(stateLabel);
		labelContainer.getChildren().addAll(statusLabel);
		labelContainer.setPadding(new Insets(25, 25, 0, 25));
		labelContainer.setAlignment(Pos.CENTER);

		buttonContainer = new HBox();
		buttonContainer.getChildren().addAll();
		buttonContainer.setSpacing(25);
		buttonContainer.setPadding(new Insets(25, 25, 0, 25));
		buttonContainer.setAlignment(Pos.CENTER);
		
		rootContainer = new VBox();
		rootContainer.setPadding(new Insets(25, 25, 25, 25));
		rootContainer.setSpacing(0);
		rootContainer.getChildren().addAll(labelContainer, new VBox(), buttonContainer);
		rootContainer.setAlignment(Pos.CENTER);
		
		super.stateScene = new Scene(rootContainer);
		this.getStage().setTitle("State: Play Battleships");
		this.getStage().setScene(super.stateScene);
	}	
	
	@Override
	public void update() {
		// first turn setup
		if(this.currentTurn == 0) {
			this.currentTurn = DataStorage.getCurrentPlayerTurn();
			rootContainer.getChildren().remove(1);
			
			if(this.currentTurn == 1) {
				rootContainer.getChildren().add(1, DataStorage.getBoards().get(DataStorage.getCurrentPlayerTurn()+1).boardColumns());
			}
			else {
				rootContainer.getChildren().add(1, DataStorage.getBoards().get(DataStorage.getCurrentPlayerTurn()-1).boardColumns());
			}
			stateLabel.setText("Player " + DataStorage.getCurrentPlayerTurn() + "'s turn!");
			statusLabel.setText("Hit the enemy ships!");
		}
		
		// if the turn changes from the previous value
		if(this.currentTurn != DataStorage.getCurrentPlayerTurn()) {
			
			System.out.println("The turn has changed!");
			
			if(DataStorage.currentTurnTime+3 < App.runTimeVal) {
				this.currentTurn = DataStorage.getCurrentPlayerTurn();
				
				rootContainer.getChildren().remove(1);
				
				if(this.currentTurn == 1) {
					rootContainer.getChildren().add(1, DataStorage.getBoards().get(DataStorage.getCurrentPlayerTurn()+1).boardColumns());
				}
				else {
					rootContainer.getChildren().add(1, DataStorage.getBoards().get(DataStorage.getCurrentPlayerTurn()-1).boardColumns());
				}
				
				stateLabel.setText("Player " + DataStorage.getCurrentPlayerTurn() + "'s turn!");
				statusLabel.setText("Hit the enemy ships!");
				
				DataStorage.lockBoard = false;
			}
			else {
				stateLabel.setText("Switching turn in " + String.valueOf((int)(DataStorage.currentTurnTime+4.99) - App.runTimeVal));
				statusLabel.setText("You missed the shot!");
			}
		}
		
		int enemyPlayer = (DataStorage.getCurrentPlayerTurn() == 1 ? 2 : 1);
		if(DataStorage.getPlayerHealth(enemyPlayer) == 1) {
			System.out.println("Game Over");
			this.currentTurn = 0;
			
			try {
				App.setState(GameState.STATE_END);	
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
