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
