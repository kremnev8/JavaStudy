/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical8;

import com.Util.Common;

public class Manager implements IEmployeePosition {

	private double myIncome;

	public Manager() {
		myIncome = Common.PickRandom(0, 115000, 140000);
	}

	public double GetIncome() {
		return myIncome;
	}

	@Override
	public String GetJobTitle() {
		return "manager";
	}

	@Override
	public double CalculateSalary(double baseSalary) {
		return baseSalary + myIncome * 0.05d;
	}
}
