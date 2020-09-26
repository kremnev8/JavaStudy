/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package practical78;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Company {

	public ArrayList<Employee> employees;

	public Company() {
		employees = new ArrayList<>();
	}

	public void Hire(Employee employee){
		employees.add(employee);
	}

	public void HireAll(ArrayList<Employee> employees){
		this.employees.addAll(employees);
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
		for (int i = 0; i < employees.size(); i++) {
			Employee employee_t = employees.get(i);
			if (employee_t.firstName.equals(employee.firstName) && employee_t.lastName.equals(employee.lastName)) {
				employees.remove(i);
				i--;
			}
		}
	}

	public double GetIncome() {
		double income = 0;
		for (Employee employee: employees) {
			income += employee.position.GetIncome();
		}

		return income;
	}


	public List<Employee> GetTopSalaryStaff(int count) {
		if (count < 0) count = 0;

		return employees.stream()
				.sorted(Comparator.comparingDouble(Employee::GetMySalary).reversed())
				.limit(count).collect(Collectors.toList());
	}

	public List<Employee> GetLowestSalaryStaff(int count){
		if (count < 0) count = 0;

		return employees.stream()
				.sorted(Comparator.comparingDouble(Employee::GetMySalary))
				.limit(count).collect(Collectors.toList());
	}

}
