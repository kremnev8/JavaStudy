/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package practical2;

public class Dog {

	public String name;
	public int age;

	public Dog(String name, int age){
		this.name = name;
		this.age = age;
	}

	public String GetName() {
		return name;
	}

	public void SetName(String name) {
		this.name = name;
	}

	public int GetHumanEquivalentAge(){
		return 7 * getAge();
	}

	public int getAge() {
		return age;
	}

	public void SetAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Dog: name: " + name + ", age: " + age;
	}
}
