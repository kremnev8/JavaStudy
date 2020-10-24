/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.old.practical3.Human;

public class Hand extends HumanPart{

	public Hand(float health, SkinColor color) {
		super(health, color);
	}

	@Override
	public String toString() {
		return "Hand{" +
				super.toString() +
				'}';
	}
}
