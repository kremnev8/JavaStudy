/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical7;

import com.practical7.GameController.*;

public interface IGraphicsController {
    void setMarker(EnumPlayers type, int num);
    void clearField();
    void changePlayer(EnumPlayers newPlayer);
    void showWinner(EnumPlayers player);
    void showDrawWarn();
}
