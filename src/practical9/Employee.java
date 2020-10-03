/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package practical9;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Employee {
	public String firstName;
	public String lastName;
	public Date birthdate;
	public String homeAddress;
	public String phoneNumber;
	public int salary;

	public Employee(String firstName, String lastName, Date birthdate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(birthdate);

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return "Hello, my name is " + firstName + " " + lastName + ".\nBorn in " + format.format(birthdate) + "\nAddress: " + homeAddress + " Phone: " + phoneNumber + " Salary: " + salary;
	}
}
