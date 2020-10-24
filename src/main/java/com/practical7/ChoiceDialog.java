/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical7;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;

import java.io.IOException;

public class ChoiceDialog extends Dialog<Boolean> {

    @FXML
    public Button aiBut;

    @FXML
    public Button humanBut;

    public static ChoiceDialog instance;

    private Boolean result;

    public boolean openDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/practical7/ChoiceDialog.fxml"));
            Parent root = null;
            root = loader.load();
            instance = loader.getController();
            getDialogPane().setContent(root);

            instance.aiBut.setOnAction( event -> {
                result = true;
                instance.aiBut.getScene().getWindow().hide();
            });

            instance.humanBut.setOnAction( event -> {
                result = false;
                instance.humanBut.getScene().getWindow().hide();
            });

            setResultConverter(buttonType -> result);

            showAndWait();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
