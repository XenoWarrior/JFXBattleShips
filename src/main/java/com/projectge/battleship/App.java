package com.projectge.battleship;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		Button btnHelloWorld1 = new Button("Hello World");
		btnHelloWorld1.setOnAction((e) -> {
			System.out.println("Hello World!");
		});

	    StackPane stackpaneRoot1 = new StackPane();
	    stackpaneRoot1.getChildren().add(btnHelloWorld1);

	    Scene sceneHelloWorld1 = new Scene(stackpaneRoot1, 300, 300);

	    Stage stageHelloWorld1 = new Stage();
	    stageHelloWorld1.setTitle("Java FX App");
	    stageHelloWorld1.setScene(sceneHelloWorld1);
	    stageHelloWorld1.show();

	}

}
