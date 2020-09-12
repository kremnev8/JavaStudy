package practical3.Human;

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
