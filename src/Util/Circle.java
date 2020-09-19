/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package Util;

public class Circle extends Shape implements IPosition {

	private float radius;

	private float x;
	private float y;

	public Circle(float radius) {
		this.radius = radius;
		this.x = 0;
		this.y = 0;
	}

	public Circle(float radius, float x, float y) {
		this.radius = radius;
		this.x = x;
		this.y = y;
	}

	public float GetX() {
		return x;
	}

	public void SetX(float x) {
		this.x = x;
	}

	public float GetY() {
		return y;
	}

	public void SetY(float y) {
		this.y = y;
	}

	public float GetRadius() {
		return radius;
	}

	public void SetRadius(float radius) {
		this.radius = radius;
	}

	@Override
	public float GetPerimeter() {
		return (float) (2 * Math.PI * radius);
	}

	@Override
	public float GetArea() {
		return (float) (Math.PI * radius * radius);
	}

	@Override
	public float[] GetCenter() {
		return new float[] {x, y};
	}
}
