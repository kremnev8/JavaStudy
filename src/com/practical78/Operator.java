/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical78;

public class Operator implements IEmployeePosition {
	@Override
	public String GetJobTitle() {
		return "operator";
	}

	@Override
	public double CalculateSalary(double baseSalary) {
		return baseSalary;
	}

	@Override
	public double GetIncome() {
		return 0;
	}
}
