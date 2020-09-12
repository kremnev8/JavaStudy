package practical2;

import Util.Shape;

public class Rect extends Shape {
    public float a;
    public float b;

    public Rect(float a, float b) {

        this.a = a;
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
