/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical3.Human;

public class HumanPart implements IHasHealth{

	private float health;
	private SkinColor color;

	public HumanPart(float health, SkinColor color){
		this.health = health;
		this.color = color;
	}

	@Override
	public float GetHealth() {
		return health;
	}

	@Override
	public void SetHealth(float health) {
		this.health = health;
	}

	public SkinColor getColor() {
		return color;
	}

	public void setColor(SkinColor color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "health= " + health + ", color= " + color.toString();
	}
}
