/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical9;

import java.util.ArrayList;

public class Company {

	protected ArrayList<Employee> employees;

	public Company() {
		employees = new ArrayList<>();
	}

	public void Hire(Employee employee) {
		this.employees.add(employee);
	}

	public void Fire(String firstName, String lastName) {
		for (int i = 0; i < employees.size(); i++) {
			Employee employee = employees.get(i);
			if (employee.firstName.equals(firstName) && employee.lastName.equals(lastName)) {
				employees.remove(i);
				i--;
			}
		}
	}

	public void Fire(Employee employee) {
		Fire(employee.firstName, employee.lastName);
	}

	public void HandleEmployees(IObjectSelector<Employee> selector, IObjectHandler<Employee> handler) {

		for (int i = 0; i < employees.size(); i++) {
			if (selector.Select(employees.get(i), i)) {
				handler.Handle(employees.get(i), i);
			}
		}
	}

	public <T extends IObjectSelector<Employee> & IObjectHandler<Employee>> void HandleEmployees(T handler) {

		for (int i = 0; i < employees.size(); i++) {
			if (handler.Select(employees.get(i), i)) {
				handler.Handle(employees.get(i), i);
			}
		}
	}

}
