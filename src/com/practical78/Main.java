/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical78;

import com.Util.Common;
import com.github.javafaker.Faker;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Company company = new Company();
		Faker faker = new Faker();

		for (int i = 0; i < 180; i++) {
			Employee employee = new Employee(faker.name().firstName(), faker.name().lastName(), Common.PickRandom(0, 50000, 70000));
			employee.SetPosition(new Operator());
			company.Hire(employee);
		}

		for (int i = 0; i < 80; i++) {
			Employee employee = new Employee(faker.name().firstName(), faker.name().lastName(), Common.PickRandom(0, 50000, 120000));
			employee.SetPosition(new Manager());
			company.Hire(employee);
		}

		for (int i = 0; i < 10; i++) {
			Employee employee = new Employee(faker.name().firstName(), faker.name().lastName(), Common.PickRandom(0, 60000, 150000));
			employee.SetPosition(new TopManager(company));
			company.Hire(employee);
		}

		List<Employee> employeeList = company.GetTopSalaryStaff(15);
		Common.Println("Employees with highest salary: ");
		for (Employee employee: employeeList) {
			Common.Println(employee.toString());

		}
		Common.Println("");

		employeeList = company.GetLowestSalaryStaff(30);
		Common.Println("Employees with lowest salary: ");
		for (Employee employee: employeeList) {
			Common.Println(employee.toString());

		}
		Common.Println("");
		int layoffCount = company.employees.size() / 2;
		Common.Println("Initiating massive layoff!");
		for (int i = 0; i < layoffCount; i++) {
			int pick = Common.PickRandom(0,0, company.employees.size());
			Employee employee = company.employees.get(pick);
			Common.Println("Firing " + employee.toString());
			company.Fire(employee);
		}
		Common.Println("");

		employeeList = company.GetTopSalaryStaff(15);
		Common.Println("Employees with highest salary: ");
		for (Employee employee: employeeList) {
			Common.Println(employee.toString());

		}
		Common.Println("");

		employeeList = company.GetLowestSalaryStaff(30);
		Common.Println("Employees with lowest salary: ");
		for (Employee employee: employeeList) {
			Common.Println(employee.toString());

		}
	}
}
