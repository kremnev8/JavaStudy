/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.Util;

public class Circle extends Shape {

	private float radius;

	public Circle(float radius) {
		this.radius = radius;
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
		return new float[] {0, 0};
	}
}
