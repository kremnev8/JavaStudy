/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical3.Human;

import java.util.Arrays;

public class Human {

	public Torso torso;
	public Leg[] legs = new Leg[2];
	public Arm[] arms = new Arm[2];
	public Head head;

	public Human(float health, SkinColor color){
		torso = new Torso(health, color);
		head = new Head(health, color, 120);
		legs[0] = new Leg(health, color, true);
		legs[1] = new Leg(health, color, false);

		arms[0] = new Arm(health, color, true);
		arms[1] = new Arm(health, color, false);
	}

	@Override
	public String toString() {
		return "Human{" +
				"\ntorso= " + torso.toString() +
				"\nlegs= " + Arrays.toString(legs) +
				"\narms= " + Arrays.toString(arms) +
				"\nhead= " + head.toString() +
				"\n}";
	}
}
