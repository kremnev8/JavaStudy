/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical11;

import com.Util.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class AIPlayer {

    private int chosen = -1;

    int getStateScore(int[] node, int winner) {
        int moveCount = 0;
        for (int i = 0; i < 9; i++) {
            if (node[i] == 1)
                moveCount++;
        }
        if (winner == 2) {
            return 0;
        } else if (winner == 0) {
            return 10 - moveCount;
        } else {
            return -10 + moveCount;
        }
    }

    int alphaBeta(int[] node, int alpha, int beta, int player) {

        int rawValue = GameController.getWinner(node);
        if (rawValue != -1) {
            return getStateScore(node, rawValue);
        }
        int value;
        if (player == 0) {
            value = -1000;
        } else {
            value = 1000;
        }

        for (int i = 0; i < 9; i++) {
            if (node[i] == 0) {
                int[] child = Arrays.copyOf(node, node.length);
                child[i] = player + 1;
                int val = alphaBeta(child, alpha, beta, GameController.getOpponent(player));

                if (player == 0) {
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

    public void evalNextAction(int[] field){
        ArrayList<scoredAction> scores = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (field[i] == 0) {
                int[] child = Arrays.copyOf(field, field.length);
                child[i] = 2;

                int score = alphaBeta(child, -1000, 1000, 0);
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
