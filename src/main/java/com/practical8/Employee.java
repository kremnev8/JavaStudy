/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical8;

public class Employee {

	public String firstName;
	public String lastName;
	public double baseSalary;
	public IEmployeePosition position;


	public Employee(String firstName, String lastName, double baseSalary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.baseSalary = baseSalary;
	}

	public double GetMySalary() {
		return position.CalculateSalary(baseSalary);
	}

	@Override
	public String toString() {
		return  position.GetJobTitle() + ": " + firstName +
				" " + lastName +
				" earns " + GetMySalary();
	}

	public void SetPosition(IEmployeePosition position) {
		this.position = position;
	}
}
