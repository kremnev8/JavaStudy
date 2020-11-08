/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical9;

import com.Util.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {

	public static String[] letters = new String[]{"A", "B", "E", "K", "M", "H", "O", "P", "C", "T", "Y", "X"};


	public static void main(String[] args) {

		ArrayList<String> carNumbers = new ArrayList<>();
		HashSet<String> carNumbersHashSet = new HashSet<>();
		TreeSet<String> carNumbersTreeSet = new TreeSet<>();

		long start;
		boolean found;
		long time;
		StringBuilder builder;

		// Loop through all possible car numbers:
		for (int x = 0; x < letters.length; x++) {
			for (int num = 0; num < 10; num++) {
				for (int y = 0; y < letters.length; y++) {
					for (int z = 0; z < letters.length; z++) {
						for (int reg = 1; reg < 200; reg++) {
							if (x != y && x != z && y != z) {
								builder = new StringBuilder();
								builder.append(letters[x]);
								builder.append(num);
								builder.append(num);
								builder.append(num);
								builder.append(letters[y]);
								builder.append(letters[z]);
								builder.append(String.format("%02d", reg));
								carNumbers.add(builder.toString());
								carNumbersHashSet.add(builder.toString());
								carNumbersTreeSet.add(builder.toString());
							}
						}
					}
				}
			}
		}

		String searchTarget = Common.InputString("Enter car number to find: ");

		start = System.nanoTime();
		found = carNumbers.contains(searchTarget);
		time = System.nanoTime() - start;

		Common.Println("Brute force search result: Number " + (found ? "found" : "not found") + ", Search took " + time + "ns");

		Collections.sort(carNumbers);

		found = false;
		start = System.nanoTime();
		int index = Collections.binarySearch(carNumbers, searchTarget);
		if (index >= 0 && index < carNumbers.size()) found = true;
		time = System.nanoTime() - start;

		Common.Println("Binary search result: Number " + (found ? "found" : "not found") + ", Search took " + time + "ns");


		start = System.nanoTime();
		found = carNumbersHashSet.contains(searchTarget);
		time = System.nanoTime() - start;

		Common.Println("Hash set search result: Number " + (found ? "found" : "not found") + ", Search took " + time + "ns");


		start = System.nanoTime();
		found = carNumbersTreeSet.contains(searchTarget);
		time = System.nanoTime() - start;

		Common.Println("Tree set search result: Number " + (found ? "found" : "not found") + ", Search took " + time + "ns");

	}
}
