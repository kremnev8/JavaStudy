package practical2;

import Util.Shape;

public class Square extends Shape {

    public float a;

    public Square(float a) {

        this.a = a;
    }

    @Override
    public float GetPerimeter() {

        return 4 * a;
    }

    @Override
    public float GetArea() {

        return a * a;
    }

    @Override
    public float[] GetCenter() {

        return new float[]{a / 2, a / 2};
    }
}
