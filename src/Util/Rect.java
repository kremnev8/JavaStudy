/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package Util;

import java.awt.*;

public class Rect extends Shape {
    private float a;
    private float b;

    public Rect(float a, float b) {

        this.a = a;
        this.b = b;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    @Override
    public float GetPerimeter() {

        return 2*a + 2*b;
    }

    @Override
    public float GetArea() {

        return a * b;
    }

    @Override
    public float[] GetCenter() {

        return new float[]{a / 2, b / 2};
    }
}
