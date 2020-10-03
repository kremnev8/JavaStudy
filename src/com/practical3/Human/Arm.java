/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical3.Human;

public class Arm extends HumanPart {

	private boolean left;
	private Hand hand;

	public Arm(float health, SkinColor color, boolean left) {
		super(health,color);
		this.left = left;
		this.hand = new Hand(health, color);
	}

	public Hand getHand() {
		return hand;
	}

	public boolean IsLeft() {
		return left;
	}

	public void SetLeft(boolean left) {
		this.left = left;
	}

	@Override
	public String toString() {
		return "Arm{" +
				super.toString() +
				", hand= " + hand.toString() +
				", left= " + left +
				'}';
	}


}
