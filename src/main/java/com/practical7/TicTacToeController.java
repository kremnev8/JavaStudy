/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical7;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.practical7.GameController.*;

public class TicTacToeController implements IGraphicsController {

    @FXML
    public Label output;

    @FXML
    public Label turnOut;

    @FXML
    public Canvas field;

    @FXML
    public Button newGame;

    @FXML
    public CheckBox p1First;

    public GraphicsContext context;

    private Image cross;
    private Image circle;
    private String[] players;
    private boolean vsAI = false;

    private GameController controller;
    private AIPlayer ai;


    public void drawLine(double x1, double y1, double x2, double y2) {

        context.beginPath();
        context.moveTo(x1, y1);
        context.lineTo(x2, y2);
        context.stroke();
    }

    public void setMarker(EnumPlayers type, int num) {
        if (type == EnumPlayers.None) return;

        int x = num % 3;
        int y = num / 3;
        context.drawImage(type == EnumPlayers.PlayerX ? cross : circle, 15 + 130 * x, 15 + 130 * y, 100, 100);
    }

    @Override
    public void clearField() {
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, 390, 390);
        context.setFill(Color.BLACK);

        for (int i = 1; i < 3; i++) {
            drawLine(130 * i, 5, 130 * i, 380);
        }

        for (int i = 1; i < 3; i++) {
            drawLine(5, 130 * i, 380, 130 * i);
        }
    }

    @Override
    public void changePlayer(EnumPlayers newPlayer) {
        turnOut.setText("Player " + (newPlayer.ordinal() + 1) + " turn");
        if (newPlayer == EnumPlayers.PlayerO && vsAI) {
            ai.evalNextAction(controller.getState());
            if (ai.getMove() != -1)
                controller.mark(EnumPlayers.PlayerO, ai.getMove());
        }
    }

    @Override
    public void showWinner(EnumPlayers player) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Game over!");

        alert.setHeaderText(null);
        alert.setContentText(players[player.ordinal()] + " has won the game!");

        alert.showAndWait();
    }

    @Override
    public void showDrawWarn() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Game over!");

        alert.setHeaderText(null);
        alert.setContentText("The game ended in a draw!");

        alert.showAndWait();
    }

    public int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }


    public void start(Stage primaryStage, Parent root) {

        context = field.getGraphicsContext2D();
        context.setLineWidth(3);
        controller = new GameController(this, EnumPlayers.PlayerX);

        ChoiceDialog dialog = new ChoiceDialog();
        boolean result = dialog.openDialog();
        vsAI = result;
        players = new String[]{"Player 1 (You)", "Player 2 (" + (result ? "AI" : "Human") + ")"};
        output.setText("Player 1 (You) vs " + players[1]);

        if (vsAI) {
            ai = new AIPlayer();
        }

        turnOut.setText("Player 1 turn");

        cross = new Image("/practical7/cross.png");
        circle = new Image("/practical7/circle.png");

        clearField();

        root.setOnMouseClicked(event -> {
            Point2D point = field.localToScene(0, 0);
            Point2D mouse = new Point2D(event.getSceneX(), event.getSceneY());
            if (event.getSceneX() - point.getX() > 0 && event.getSceneY() - point.getY() > 0) {
                int x = clamp((int) (event.getSceneX() - point.getX()) / 130, 0, 3);
                int y = clamp((int) (event.getSceneY() - point.getY()) / 130, 0, 3);
                if (vsAI) {
                    controller.mark(EnumPlayers.PlayerX, 3 * y + x);
                } else {
                    controller.mark(controller.getCurrentPlayer(), 3 * y + x);
                }
            }
        });

        newGame.setOnAction(event -> {
            controller.newGame(p1First.isSelected() ? EnumPlayers.PlayerX : EnumPlayers.PlayerO);
        });
    }

}
