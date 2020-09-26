/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package practical78;

public interface IEmployeePosition {

	String GetJobTitle();
	double CalculateSalary(double baseSalary);
	double GetIncome();
}
