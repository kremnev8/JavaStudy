 /*
  * Copyright (c) 2020 Ilya Kremnev
  * MIT License
  *
  * The above copyright notice and this permission notice shall be included in all
  * copies or substantial portions of the Software.
  */

 package com.practical7;

 import javafx.application.Application;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Parent;
 import javafx.scene.Scene;
 import javafx.stage.Stage;

 import java.net.URL;

 public class Main extends Application {

     public static void main(String[] args) {
         launch(args);
     }

     public static TicTacToeController instance;

     @Override
     public void start(Stage primaryStage) throws Exception {

         // Starting application, reading FXML, etc
         FXMLLoader loader = new FXMLLoader();
         URL xmlUrl = getClass().getResource("/practical7/TicTacToe.fxml");
         loader.setLocation(xmlUrl);
         Parent root = loader.load();
         instance = loader.getController();
         instance.start(primaryStage, root);

         primaryStage.setTitle("TicTacToe");
         primaryStage.setWidth(400);
         primaryStage.setHeight(550);
         primaryStage.setResizable(false);

         Scene primaryScene = new Scene(root);
         primaryStage.setScene(primaryScene);

         primaryStage.show();
     }
 }
