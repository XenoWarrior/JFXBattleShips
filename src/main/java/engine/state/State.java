package engine.state;

import engine.gui.GUIControls;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class State {

	protected Stage stateStage = new Stage();

	public State() {
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
        
		Scene scene = new Scene(rootContainer);
		this.getStage().setTitle("State: Empty State");
		this.getStage().setScene(scene);
	}
	
	public Stage getStage() { 
		return stateStage;
	}
	
}
