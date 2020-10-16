/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical11;

public class GameController {
    private int[] field;

    public static final int[][] winningCombos  = new int[][]{
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    private int currentPlayer;
    private boolean gameActive = true;

    public IGraphicsController controller;

    public GameController(IGraphicsController controller, int initialPlayer) {

        field = new int[9];
        this.controller = controller;
        this.currentPlayer = initialPlayer;

        for (int i = 0; i < 9; i++) {
                field[i] = 0;
        }
    }

    public void newGame(int initialPlayer) {
        this.currentPlayer = initialPlayer;
        this.gameActive = true;

        for (int i = 0; i < 9; i++) {
            field[i] = 0;
        }

        controller.clearField();
        controller.changePlayer(currentPlayer);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int[] getField() {
        return field;
    }

    public void mark(int player, int x, int y) {
       if (field[3*y + x] == 0 && gameActive && player == currentPlayer) {
           field[3*y + x] = currentPlayer + 1;
           controller.setMarker(currentPlayer, x, y);
           int res = getWinner(field);
           if (res == -1) {
               currentPlayer = getOpponent(currentPlayer);
               controller.changePlayer(currentPlayer);
           }else if (res == 2) {
               controller.showDrawWarn();
           }else {
               gameOver(res);
           }
       }
    }

    public static int getWinner(int[] field) {
        for (int i = 0; i < winningCombos.length; i++) {
            int[] combo = winningCombos[i];
            int lastValue = field[combo[0]];
            for (int j = 1; j < 3; j++) {
                if (field[combo[j]] != lastValue) {
                    lastValue = 0;
                    break;
                }
            }
            if (lastValue != 0) {
                return lastValue - 1;
            }
        }
        int marksCount = 0;
        for (int i = 0; i < 9; i++) {
            if (field[i] != 0) marksCount++;
        }

        if (marksCount == 9) {
            return 2;
        }

        return -1;
    }

    public static int getOpponent(int player) {
        return (player + 1) % 2;
    }

    public void gameOver(int winnerIndex) {
        controller.showWinner(winnerIndex);
        gameActive = false;
    }
}
