/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class CalculatorApp extends Application {

	public static void main(String[] args) {
		Application.launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
			primaryStage.setTitle("Dogs application");
			primaryStage.setWidth(500);
			primaryStage.setHeight(400);

			/*InputStream iconStream =
					getClass().getResourceAsStream("someImage.jpg");
			Image image = new Image(iconStream);
			primaryStage.getIcons().add(image);*/

			Button button = new Button("WOF WOF ???'");


			button.setOnAction(e -> {
					Alert alert = new Alert(Alert.AlertType.INFORMATION, "WOF WOF WOF!!!");
					alert.showAndWait();
			});
			Scene primaryScene = new Scene(button);
			primaryStage.setScene(primaryScene);

			primaryStage.show();
	}
}
