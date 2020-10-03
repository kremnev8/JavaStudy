/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical3.Human;

public class Head extends HumanPart {

	private float brainIQ;

	public Head(float health, SkinColor color, float brainIQ) {
		super(health, color);
		this.brainIQ = brainIQ;
	}

	public float getBrainIQ() {
		return brainIQ;
	}

	public void setBrainIQ(float brainIQ) {
		this.brainIQ = brainIQ;
	}

	@Override
	public String toString() {
		return "Head{" +
				super.toString() +
				", IQ= " + brainIQ +
				'}';
	}


}
