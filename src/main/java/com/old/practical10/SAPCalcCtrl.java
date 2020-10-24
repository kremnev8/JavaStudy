/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */
package com.old.practical10;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SAPCalcCtrl extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public TextField aInput;

    @FXML
    public TextField bInput;

    @FXML
    public ChoiceBox<String> selectOP;

    @FXML
    public Button calcButton;

    @FXML
    public Label output;

    public static SAPCalcCtrl instance;

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/old/practical10/SAPCalc.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        instance = loader.getController();
        ArrayList<String> items = new ArrayList<>();
        items.add("Add");
        items.add("Subtract");
        items.add("Multiply");
        items.add("Divide");

        instance.selectOP.setItems(FXCollections.observableArrayList(items));

        instance.calcButton.setOnAction(event -> {
            String a = instance.aInput.getText();
            String b = instance.bInput.getText();
            String op = instance.selectOP.getValue();

            if (op == null) {
                instance.output.setText("Please select operation!");
                return;
            }

            float an, bn, res = 0;
            try {
                an = Float.parseFloat(a);
            }catch (NumberFormatException e){
                instance.output.setText("Input field A contains invalid number!");
                return;
            }
            try {
                bn = Float.parseFloat(b);
            }catch (NumberFormatException e){
                instance.output.setText("Input field B contains invalid number!");
                return;
            }

            switch (op) {
                case "Add": {
                    res = an + bn;
                    break;
                }
                case "Subtract": {
                    res = an - bn;
                    break;
                }
                case "Multiply": {
                    res = an * bn;
                    break;
                }
                case "Divide": {
                    res = an / bn;
                    break;
                }
            }
            instance.output.setText( String.format("%.4f", res));
        });

        primaryStage.setTitle("SAP calculator");
        primaryStage.setResizable(false);

        Scene primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);

        primaryStage.show();

    }
}
