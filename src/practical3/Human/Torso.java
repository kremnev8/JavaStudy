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
