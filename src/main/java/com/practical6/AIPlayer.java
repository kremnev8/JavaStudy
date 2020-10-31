/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical6;

import com.Util.Common;

import java.util.ArrayList;
import java.util.Comparator;
import com.practical6.GameController.*;

public class AIPlayer {

    private int chosen = -1;

    int getStateScore(GameState node, FieldState state) {
        int moveCount = 0;
        for (int i = 0; i < 9; i++) {
            if (node.getMark(i) != EnumPlayers.None)
                moveCount++;
        }
        if (state == FieldState.Draw) {
            return 0;
        } else if (state.getWinner() == EnumPlayers.PlayerX) {
            return 10 - moveCount;
        } else {
            return -10 + moveCount;
        }
    }

    int alphaBeta(GameState node, int alpha, int beta, EnumPlayers player) {

        FieldState state = node.evalFieldState();
        if (state != FieldState.GameInProgress) {
            return getStateScore(node, state);
        }
        int value;
        if (player == EnumPlayers.PlayerX) {
            value = -1000;
        } else {
            value = 1000;
        }

        for (int i = 0; i < 9; i++) {
            if (node.canMark(i)) {
                GameState child = node.copy();
                child.setMark(player, i);
                int val = alphaBeta(child, alpha, beta, player.getOpponent());

                if (player == EnumPlayers.PlayerX) {
                    value = Math.max(value, val);
                    alpha = Math.max(alpha, value);
                    if ( alpha >= beta )
                        break;
                } else {
                    value = Math.min(value, val);
                    beta = Math.min(beta, value);
                    if ( beta <= alpha )
                        break;
                }
            }
        }
        return value;
    }

    public void evalNextAction(GameState state){
        ArrayList<scoredAction> scores = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (state.canMark(i)) {
                GameState child = state.copy();
                child.setMark(EnumPlayers.PlayerO, i);

                int score = alphaBeta(child, -1000, 1000, EnumPlayers.PlayerX);
                scores.add(new scoredAction(i, score));
            }
        }

        scores.sort(Comparator.comparingInt(value -> value.score));

        int rnd = Common.PickRandom(1, 0,10);
        if (rnd <= 6) {
            chosen = scores.get(0).action;
        }else {
            if (scores.size() > 1) {
                chosen = scores.get(1).action;
            }else {
                chosen = scores.get(0).action;
            }
        }
    }

    public int getMove() {
        return chosen;
    }

    class scoredAction {
        int action;
        int score;

        scoredAction(int action, int score) {
            this.action = action;
            this.score = score;
        }
    }
}
