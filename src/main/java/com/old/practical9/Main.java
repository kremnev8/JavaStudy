/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.old.practical9;


import com.Util.Common;
import com.github.javafaker.Faker;

import java.util.Calendar;

class HandlerWithA implements IObjectHandler<Employee>, IObjectSelector<Employee> {

	@Override
	public void Handle(Employee object, int index) {
		Common.Println(object.toString());
	}

	@Override
	public boolean Select(Employee object, int index) {
		return object.firstName.toLowerCase().charAt(0) == 'a';
	}
}


public class Main {

	public static void main(String[] args) {
		Company helloWorldInc = new Company();
		Faker faker = new Faker();

		for (int i = 0; i < 50; i++) {
			Employee employee = new Employee(faker.name().firstName(), faker.name().lastName(), faker.date().birthday(20, 40));
			employee.setHomeAddress(faker.address().fullAddress());
			employee.setPhoneNumber(faker.phoneNumber().cellPhone());
			employee.setSalary(Common.PickRandom(0, 50000, 70000));
			helloWorldInc.Hire(employee);
		}

		Common.Println("\nEmployees born after 1995");
		helloWorldInc.HandleEmployees((object, index) -> {
			Calendar cal = Calendar.getInstance();
			cal.setTime(object.birthdate);
			return cal.get(Calendar.YEAR) > 1995;
		},   (object, index) -> {
			Common.Println(object.toString());
		});

		Common.Println("\nEmployees earning more than 60000");
		helloWorldInc.HandleEmployees(new IObjectSelector<Employee>() {
			@Override
			public boolean Select(Employee object, int index) {
				return object.salary > 60000;
			}
		}, new IObjectHandler<Employee>() {
			@Override
			public void Handle(Employee object, int index) {
				if (object.salary > 60000) {
					Common.Println(object.toString());
				}
			}
		});

		Common.Println("\nEmployees with name starting with A");
		helloWorldInc.HandleEmployees(new HandlerWithA());
	}

}
