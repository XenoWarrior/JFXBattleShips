package engine.state;

import engine.gui.GUIControls;
import game.states.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 
 * engine.state.State
 * Abstract class for initialising game states, if a state has been made and does not contain any scene then it will return an Empty State
 * @author Ashley Scott
 *
 */
public abstract class State {

	/**
	 * Variable definitions
	 */
	protected Scene stateScene;
	protected Stage stateStage = new Stage();
	protected GameState stateType = GameState.STATE_NULL;

	/**
	 * Constructs an initial state as a fall back if the extension class does not define a scene
	 */
	public State(GameState s) {
		this.stateType = s;
		
		HBox labelContainer = new HBox();
		labelContainer.getChildren().addAll(GUIControls.createLabel(this.getClass().getName()));
		labelContainer.getChildren().addAll(GUIControls.createLabel("Empty State", new Font("Arial", 30)));
		labelContainer.setSpacing(25);
		labelContainer.setPadding(new Insets(25, 25, 25, 25));
		labelContainer.setAlignment(Pos.CENTER);
		
		VBox rootContainer = new VBox();
		rootContainer.setPadding(new Insets(25, 25, 25, 25));
		rootContainer.setSpacing(0);
		rootContainer.getChildren().addAll(labelContainer);
        
		Scene stateScene = new Scene(rootContainer);
		this.getStage().setTitle("State: Empty State");
		this.getStage().setScene(stateScene);
	}
	
	/**
	 * Used to fetch the full stage of the object for displaying
	 * @return Stage object containing all nodes
	 */
	public Stage getStage() { 
		return stateStage;
	}

	/**
	 * Gets the scene for this state
	 * @return Scene node object which can be used to find values in
	 */
	public Scene getScene() {
		return stateScene;
	}
	
	/**
	 * Gets the state enum value
	 * @return GameState enum value
	 */
	public GameState getType() {
		return stateType;
	}
	
	/**
	 * Stub update method
	 */
	public void update() { 
		//System.out.println("[" +this.getClass().getName() + "]: update()");
	}
}
