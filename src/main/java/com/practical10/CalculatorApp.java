/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical10;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.net.URL;

public class CalculatorApp extends Application {

	public static void main(String[] args) {
		Application.launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {


			FXMLLoader loader = new FXMLLoader();
			URL xmlUrl = getClass().getResource("/practical10/Calculator.fxml");
			loader.setLocation(xmlUrl);
			Parent root = loader.load();

			primaryStage.setTitle("Simple calculator");
			primaryStage.setWidth(900);
			primaryStage.setHeight(600);
			primaryStage.setMinWidth(300);
			primaryStage.setMinHeight(500);

			Scene primaryScene = new Scene(root);
			JMetro jMetro = new JMetro(Style.LIGHT);
			jMetro.setScene(primaryScene);
			primaryStage.setScene(primaryScene);

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

			primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
				// Do whatever you want
			});

			primaryStage.show();
	}
}
