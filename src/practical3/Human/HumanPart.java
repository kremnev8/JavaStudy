package practical3.Human;

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
