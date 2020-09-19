/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package practical3.Human;

public class Torso extends HumanPart {

	public Torso(float health, SkinColor color) {

		super(health,color);
	}

	@Override
	public String toString() {
		return "Torso{" +
				super.toString() +
				'}';
	}
}
