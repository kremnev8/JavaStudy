/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical7;

public class GameController {
    private GameState state;

    private EnumPlayers currentPlayer;
    private boolean gameActive = true;

    public IGraphicsController controller;

    public GameController(IGraphicsController controller, EnumPlayers initialPlayer) {

        this.controller = controller;
        this.currentPlayer = initialPlayer;

        state = new GameState();
    }

    public void newGame(EnumPlayers initialPlayer) {
        this.currentPlayer = initialPlayer;
        this.gameActive = true;

        state.clear();

        controller.clearField();
        controller.changePlayer(currentPlayer);
    }

    public EnumPlayers getCurrentPlayer() {
        return currentPlayer;
    }

    public GameState getState() {
        return state;
    }

    public void mark(EnumPlayers player, int num) {
       if (state.canMark(num) && gameActive && player == currentPlayer) {
           state.setMark(player, num);
           controller.setMarker(currentPlayer, num);

           FieldState res = state.evalFieldState();
           if (res == FieldState.GameInProgress) {
               currentPlayer = currentPlayer.getOpponent();
               controller.changePlayer(currentPlayer);
           }else if (res == FieldState.Draw) {
               controller.showDrawWarn();
           }else {
               gameOver(res);
           }
       }
    }

    public void gameOver(FieldState state) {
        controller.showWinner(state.getWinner());
        gameActive = false;
    }

    enum FieldState {
        WonPlayerX,
        WonPlayerO,
        Draw,
        GameInProgress;

        public static FieldState getByIndex(int index) {
            if (index >= 0 && index < FieldState.values().length)
                return FieldState.values()[index];
            return null;
        }

        GameController.EnumPlayers getWinner() {
            if (this.ordinal() < GameController.EnumPlayers.values().length) {
                return GameController.EnumPlayers.getByIndex(this.ordinal());
            }
            return null;
        }
    }

    enum EnumPlayers {
        PlayerX('X'),
        PlayerO('O'),
        None(' ');

        char icon;

        EnumPlayers(char icon) {
            this.icon = icon;
        }

        public static EnumPlayers getByIndex(int index) {
            if (index >= 0 && index < EnumPlayers.values().length)
                return EnumPlayers.values()[index];
            return null;
        }

        public EnumPlayers getOpponent() {
            if (this == EnumPlayers.None) return EnumPlayers.None;

            return getByIndex((this.ordinal() + 1 ) %2);
        }
    }
}
