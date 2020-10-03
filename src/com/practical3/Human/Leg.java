/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical3.Human;

public class Leg extends HumanPart {

	private boolean left;

	public Leg(float health, SkinColor color, boolean left) {
		super(health, color);
		this.left = left;
	}

	public boolean IsLeft() {
		return left;
	}

	public void SetLeft(boolean left) {
		this.left = left;
	}

	@Override
	public String toString() {
		return "Leg{" +
				super.toString() +
				", left= " + left +
				'}';
	}
}
