package practical3.Human;

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
