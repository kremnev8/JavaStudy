/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.net.URL;

public class Main extends Application {

	public static void main(String[] args) {
		Application.launch();
	}

	public static CalculatorApp instance;

	@Override
	public void start(Stage primaryStage) throws Exception {

			// Starting application, reading FXML, etc
			FXMLLoader loader = new FXMLLoader();
			URL xmlUrl = getClass().getResource("/practical10/Calculator.fxml");
			loader.setLocation(xmlUrl);
			Parent root = loader.load();
			instance = loader.getController();
			instance.start(primaryStage);

			primaryStage.setTitle("Simple calculator");
			primaryStage.setWidth(900);
			primaryStage.setHeight(600);
			primaryStage.setMinWidth(300);
			primaryStage.setMinHeight(500);

			Scene primaryScene = new Scene(root);
			JMetro jMetro = new JMetro(Style.LIGHT);
			jMetro.setScene(primaryScene);
			primaryStage.setScene(primaryScene);

			// Resizing logic
			primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
				if ((double)newVal <= 600) {
					GridPane pane = ((GridPane)root);
					pane.getColumnConstraints().get(1).setMaxWidth(0);
					pane.getChildren().get(0).setVisible(false);
				}else {
					GridPane pane = ((GridPane)root);
					pane.getColumnConstraints().get(1).setMaxWidth(300);
					pane.getChildren().get(0).setVisible(true);
				}
			});

			primaryStage.show();
	}
}
