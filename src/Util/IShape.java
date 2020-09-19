/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package Util;

import java.awt.*;

public interface IShape {
    float GetPerimeter();
    float GetArea();
    float[] GetCenter();
    void Draw(Graphics g);
}

