/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical11;

import com.practical11.GameController.*;

import java.util.Arrays;

public class GameState {

    public static final int[][] winningCombos  = new int[][]{
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    private EnumPlayers[] field;

    public GameState() {
        field = new EnumPlayers[9];
        clear();
    }

    public void clear() {
        for (int i = 0; i < 9; i++) {
            field[i] = EnumPlayers.None;
        }
    }

    public void setMark(EnumPlayers player, int index) {
        if (index >= 0 && index < field.length && field[index] == EnumPlayers.None) {
            field[index] = player;
        }
    }

    public boolean canMark(int index) {
        return index >= 0 && index < field.length && field[index] == EnumPlayers.None;
    }

    public EnumPlayers getMark(int index) {
        if (index >= 0 && index < field.length)
            return  field[index];
        return null;
    }

    public FieldState evalFieldState() {
        for (int i = 0; i < winningCombos.length; i++) {
            int[] combo = winningCombos[i];
            EnumPlayers lastValue = field[combo[0]];
            for (int j = 1; j < 3; j++) {
                if (field[combo[j]] != lastValue) {
                    lastValue = EnumPlayers.None;
                    break;
                }
            }
            if (lastValue != EnumPlayers.None) {
                return FieldState.getByIndex(lastValue.ordinal());
            }
        }
        int marksCount = 0;
        for (int i = 0; i < 9; i++) {
            if (field[i] != EnumPlayers.None) marksCount++;
        }

        if (marksCount == 9) {
            return FieldState.Draw;
        }

        return FieldState.GameInProgress;
    }

    public GameState copy() {
        GameState copy = new GameState();
        copy.field = Arrays.copyOf(field, field.length);
        return copy;
    }
}
