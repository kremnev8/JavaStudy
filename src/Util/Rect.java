/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package Util;

import java.awt.*;

public class Rect extends Shape implements IMovable {
    private float a;
    private float b;

    private Vector2[] positions = new Vector2[2];
    private Vector2 velocity;

    public Rect(float a, float b) {

        this.a = a;
        this.b = b;
        positions[0] = Vector2.zero;
        positions[1] = new Vector2(a,b);
        velocity = Vector2.zero;
    }

    public Rect(float x, float y, float a, float b) {

        this.a = a;
        this.b = b;
        positions[0] = new Vector2(x, y);
        positions[1] = new Vector2(x + a,y + b);
        velocity = Vector2.zero;
    }

    @Override
    public void Draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int)positions[0].x, (int)positions[0].y, (int)a, (int)b);
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

        return new float[]{positions[0].x - positions[1].x / 2, positions[0].y - positions[1].y / 2};
    }

    @Override
    public Vector2[] GetPositions() {
        return positions;
    }

    @Override
    public Vector2 GetVelocity() {
        return velocity;
    }

    @Override
    public void SetVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    @Override
    public void MoveShape(Vector2[] posArr) {
        if (posArr.length == positions.length) {
            positions = posArr;
            a = Math.abs(positions[0].x - positions[1].x);
            b = Math.abs(positions[0].y - positions[1].y);
        }else{
            throw new IllegalArgumentException("Input array length("+posArr.length+") does not match expected! ("+ positions.length + ")");
        }
    }

    @Override
    public void ChangeEdge(int index, Vector2 pos) {
        if (index >= 0 && index < positions.length) {
            positions[index] = pos;
            a = Math.abs(positions[0].x - positions[1].x);
            b = Math.abs(positions[0].y - positions[1].y);
        }
    }

    @Override
    public void Update() {
        if (!velocity.isZero()) {
            Vector2[] newPos = new Vector2[2];
            newPos[0] = positions[0].add(velocity);
            newPos[1] = positions[1].add(velocity);
            MoveShape(newPos);
        }
    }
}
