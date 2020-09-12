package practical2;

import Util.IShape;

public class Square implements IShape {

    private float a;

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
