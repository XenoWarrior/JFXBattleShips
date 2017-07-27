package game.states;

import engine.gui.GUIControls;
import engine.state.State;
import game.App;
import game.DataStorage;
import game.board.Board;
import game.board.Cell;
import game.ship.ShipType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SetupState extends State {

	private Label placeLabel = GUIControls.createLabel("Ship Placement: Player " + DataStorage.getCurrentPlayerTurn(), new Font("Arial", 16));
	private VBox rootContainer;
	private VBox labelContainer;
	private HBox buttonContainer;
	
	private boolean boardsShown = false;

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
				if(DataStorage.getCurrentShip() == null) {
					if(DataStorage.getBoards().size() == 0 ) {
						DataStorage.addBoard(new Board(rootContainer.getChildren().get(1)));
						rootContainer.getChildren().remove(1);
						rootContainer.getChildren().add(1, new Board(10).boardColumns());
						
						System.out.println("Added board for player " + DataStorage.getCurrentPlayerTurn());
						
						DataStorage.getNextPlayerTurn();
						System.out.println("Next player is: " + DataStorage.getCurrentPlayerTurn());
					}
					else if(DataStorage.getBoards().size() == 1) {
						DataStorage.addBoard(new Board(rootContainer.getChildren().get(1)));
						rootContainer.getChildren().remove(1);
						
						System.out.println("Added board for player " + DataStorage.getCurrentPlayerTurn());
					}
					else {
						this.boardsShown = false;
						
						rootContainer.getChildren().remove(1);
						rootContainer.getChildren().add(1, new Board(10).boardColumns());
	
						DataStorage.resetPlayer();
						DataStorage.getNextPlayerTurn();
	
						DataStorage.getBoards().get(1).hideShips();
						DataStorage.getBoards().get(2).hideShips();
						
						App.setState(GameState.STATE_PLAY);
					}
				}
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});

		labelContainer = new VBox();
		labelContainer.setSpacing(0);
		labelContainer.getChildren().addAll(GUIControls.createLabel("New Game", new Font("Arial", 30)));
		labelContainer.getChildren().addAll(placeLabel);
		labelContainer.setPadding(new Insets(25, 25, 25, 25));
		labelContainer.setAlignment(Pos.CENTER);

		buttonContainer = new HBox();
		buttonContainer.getChildren().addAll(startButton);
		buttonContainer.setSpacing(25);
		buttonContainer.setPadding(new Insets(25, 25, 0, 25));
		buttonContainer.setAlignment(Pos.CENTER);
				
		rootContainer = new VBox();
		rootContainer.getChildren().addAll(labelContainer, new Board(10).boardColumns(), buttonContainer);
		rootContainer.setPadding(new Insets(25, 25, 25, 25));
		rootContainer.setSpacing(0);
		rootContainer.setAlignment(Pos.CENTER);
        
		super.stateScene = new Scene(rootContainer);
		this.getStage().setTitle("State: Board Setup");
		this.getStage().setScene(super.stateScene);
	}
	
	@Override
	public void update() { 
		
		if(DataStorage.gameReady()) {
			placeLabel.setText("Both players are ready.");

			if(!this.boardsShown) {
				HBox container = new HBox();
				container.getChildren().addAll(DataStorage.getBoards().get(1).boardColumns(), DataStorage.getBoards().get(2).boardColumns());
				container.setPadding(new Insets(25, 25, 25, 25));
				container.setSpacing(25);
				container.setAlignment(Pos.CENTER);
				
				//rootContainer.getChildren().add(1, container);
				
				this.boardsShown = true;
			}
			
			((Label)labelContainer.getChildren().get(0)).setText("Game Ready");
			((Button)buttonContainer.getChildren().get(0)).setText("Start Game");
		}
		else {
			
			if(DataStorage.getCurrentShip() != null) {
				placeLabel.setText("Player " + DataStorage.getCurrentPlayerTurn() + ", place your ship: " + DataStorage.getCurrentShip().getName() + ", size: 1 x " + DataStorage.getCurrentShip().getSize());
				((Button)buttonContainer.getChildren().get(0)).setText("NOT ENOUGH SHIPS");
			}
			else {
				placeLabel.setText("Player " + DataStorage.getCurrentPlayerTurn() + ", done placing ships." + DataStorage.getPlayerHealth(DataStorage.getCurrentPlayerTurn()));
				((Button)buttonContainer.getChildren().get(0)).setText("Ready");
			}
			
			((Label)labelContainer.getChildren().get(0)).setText("New Game");
		}
		
	}
}
