/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package Util;

public interface IMovable {

	class Vector2 {

		public static final Vector2 zero = new Vector2(0,0);


		public float x, y;


		public Vector2(float x, float y) {
			this.x = x;
			this.y = y;
		}

		public Vector2(Vector2 other) {
			this.x = other.x;
			this.y = other.y;
		}

		public float getX() {
			return x;
		}

		public void setX(float x) {
			this.x = x;
		}

		public float getY() {
			return y;
		}

		public void setY(float y) {
			this.y = y;
		}

		public boolean isZero() {
			return x == 0 && y == 0;
		}

		public Vector2 add(Vector2 rhs) {
			return new Vector2(x + rhs.x, y + rhs.y);
		}

		public Vector2 mul(Vector2 rhs) {
			return new Vector2(x * rhs.x, y * rhs.y);
		}

	}


	Vector2[] GetPositions();
	Vector2 GetVelocity();
	void SetVelocity(Vector2 velocity);
	void MoveShape(Vector2[] posArr);
	void ChangeEdge(int index, Vector2 pos);
	void Update();
}
