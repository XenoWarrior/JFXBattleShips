package game.states;

import engine.gui.GUIControls;
import engine.state.State;
import game.App;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class InitState extends State {

	public InitState(GameState s) { 
		super(s);
		
		VBox labelContainer = new VBox();
		labelContainer.setSpacing(0);
		labelContainer.getChildren().addAll(GUIControls.createLabel(this.getClass().getName()));
		labelContainer.getChildren().addAll(GUIControls.createLabel("Splash Screen", new Font("Arial", 30)));
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
	
	@Override 
	public void update() {
		if(App.runTimeVal > 1) {
			try {
				App.setState(GameState.STATE_MENU);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
