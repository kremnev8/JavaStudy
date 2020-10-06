/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical8;

public class TopManager implements IEmployeePosition  {

	protected Company company;

	public TopManager (Company company) {
		this.company = company;
	}

	@Override
	public String GetJobTitle() {
		return "top manager";
	}

	@Override
	public double CalculateSalary(double baseSalary) {
		return baseSalary * (company.GetIncome() > 10000000 ? 2.5d : 1);
	}

	@Override
	public double GetIncome() {
		return 0;
	}
}
