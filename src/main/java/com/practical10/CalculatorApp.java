/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical10;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class CalculatorApp {

    @FXML
    public AnchorPane output;

    @FXML
    public VBox history;

    @FXML
    public GridPane buttons;

    @FXML
    public Button historyEraseButton;

    @FXML
    public MenuItem copyAction;

    @FXML
    public MenuItem pasteAction;

    public CalculatorDisplay display;

    public void start(Stage primaryStage) {
        // Button press handler registration
        historyEraseButton.setOnAction(this::handleButtons);
        historyEraseButton.setFocusTraversable(false);

        for (Node node : buttons.getChildren()) {
            ((Button) node).setOnAction(this::handleButtons);
            node.setFocusTraversable(false);
        }
        // For copying into exchange buffer
        copyAction.setOnAction(event -> {
            StringSelection stringSelection = new StringSelection(display.input.getDisplay());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        });

        pasteAction.setOnAction(event -> {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            try {
                Transferable t = clipboard.getContents(null);
                if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    double num = Double.parseDouble((String) t.getTransferData(DataFlavor.stringFlavor));

                    display.input.setFromDouble(num);
                    display.set(display.input);
                }
            } catch (UnsupportedFlavorException | IOException | NumberFormatException ex) {

            }
        });

        display = new CalculatorDisplay(output);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeys);
    }

    public void addHistoryItem(NumberEntry input, ArrayList<IEntry> entries) {
        // Adding history tab item
        try {
            FXMLLoader loader = new FXMLLoader();
            URL xmlUrl = getClass().getResource("/practical10/HistoryItem.fxml");
            loader.setLocation(xmlUrl);
            Parent root = null;
            root = loader.load();
            HistoryItemController instance = loader.getController();
            instance.SetItem(root, input, entries);
            history.getChildren().add(0, root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleKeys(KeyEvent event) {
        // Receives keypresses and reacts.
        // Might be not the best approach...
        String key = event.getText();
        if (!key.equals("") && !key.equals("\r")) {
            if (Character.isDigit(key.charAt(0))) {
                display.typeNumber(key.charAt(0));
            } else {

                switch (key) {
                    case ".": {
                        display.startFloatingPoint();
                        break;
                    }
                    case "+": {
                        display.finishNumber(new OperatorEntry("+", (lhs, rhs) -> lhs + rhs));
                        break;
                    }
                    case "-": {
                        display.finishNumber(new OperatorEntry("\u2212", (lhs, rhs) -> lhs - rhs));
                        break;
                    }
                    case "*": {
                        display.finishNumber(new OperatorEntry("\u00D7", (lhs, rhs) -> lhs * rhs));
                        break;
                    }
                    case "/": {
                        display.finishNumber(new OperatorEntry("\u00F7", (lhs, rhs) -> lhs / rhs));
                        break;
                    }
                }
            }
        } else {
            switch (event.getCode()) {
                case BACK_SPACE: {
                    display.eraseNumber();
                    break;
                }
                case ESCAPE: {
                    display.clear(false);
                    break;
                }
                case DELETE: {
                    display.clear(true);
                    break;
                }
                case F9: {
                    display.invertInput();
                    break;
                }
                case ENTER: {
                    event.consume();
                    if (display.getResult())
                        addHistoryItem(display.input, display.entries);
                    break;
                }
            }
        }
    }

    public void handleButtons(ActionEvent event) {
        // Recives Button presses and reacts
        Button but = (Button) event.getSource();
        String id = but.getId();
        String result[] = id.split("_");
        id = result[1];
        //Common.Println("Pressed: " + id);

        if (Character.isDigit(id.charAt(0))) {
            display.typeNumber(id.charAt(0));
        } else {
            switch (id) {
                case "dot": {
                    display.startFloatingPoint();
                    break;
                }
                case "erase": {
                    display.eraseNumber();
                    break;
                }
                case "sign": {
                    display.invertInput();
                    break;
                }
                case "clear": {
                    display.clear(false);
                    break;
                }
                case "ce": {
                    display.clear(true);
                    break;
                }
                case "add": {
                    display.finishNumber(new OperatorEntry("+", (lhs, rhs) -> lhs + rhs));
                    break;
                }
                case "sub": {
                    display.finishNumber(new OperatorEntry("\u2212", (lhs, rhs) -> lhs - rhs));
                    break;
                }
                case "mul": {
                    display.finishNumber(new OperatorEntry("\u00D7", (lhs, rhs) -> lhs * rhs));
                    break;
                }
                case "div": {
                    display.finishNumber(new OperatorEntry("\u00F7", (lhs, rhs) -> lhs / rhs));
                    break;
                }
                case "result": {
                    if (display.getResult())
                        addHistoryItem(display.input, display.entries);
                    break;
                }
                case "erasehistory": {
                    history.getChildren().clear();
                }
            }
        }
    }
}
