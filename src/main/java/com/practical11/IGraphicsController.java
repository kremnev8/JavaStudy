/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical11;

public interface IGraphicsController {
    void setMarker(int type, int x, int y);
    void clearField();
    void changePlayer(int newPlayer);
    void showWinner(int player);
    void showDrawWarn();
}
