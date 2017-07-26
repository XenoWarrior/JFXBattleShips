package game.states;

import engine.gui.GUIControls;
import engine.state.State;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class InitState extends State {

	public InitState(GameState s) { 
		super(s);
		
		HBox labelContainer = new HBox();
		labelContainer.getChildren().addAll(GUIControls.createLabel(this.getClass().getName(), new Font("Arial", 30)));
		labelContainer.setSpacing(25);
		labelContainer.setPadding(new Insets(25, 25, 25, 25));
		labelContainer.setAlignment(Pos.CENTER);
		
		VBox rootContainer = new VBox();
		rootContainer.setPadding(new Insets(25, 25, 25, 25));
		rootContainer.setSpacing(0);
		rootContainer.getChildren().addAll(labelContainer);
		rootContainer.setAlignment(Pos.CENTER);
        
		super.stateScene = new Scene(rootContainer);
		super.getStage().setTitle("State: Game Loading");
		super.getStage().setScene(super.stateScene);
	}
}
