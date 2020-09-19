/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package Util;

import Util.IShape;

public abstract class Shape implements IShape {

	public String name;

	public void SetName(String name) {
		this.name = name;
	}

	public abstract float GetPerimeter();

	public abstract float GetArea();

	public abstract float[] GetCenter();

	@Override
	public String toString() {
		return "Shape(" +
				name +
				')';
	}
}
