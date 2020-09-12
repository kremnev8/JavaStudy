package practical2;

public class Dog {

	public String name;
	public int age;

	public Dog(String name, int age){
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHumanEquivalentAge(){
		return 7 * getAge();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Dog: name: " + name + ", age: " + age;
	}
}
