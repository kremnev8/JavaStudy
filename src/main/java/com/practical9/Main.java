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
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] ar_) {

		boolean running = true;

		final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";



		while (running) {

			int prgID = Common.InputInt("Choose which program to run(0-2): ");

			switch (prgID) {
				case 0: {

					ArrayList<String> toDoList = new ArrayList<>();

					boolean taskRun = true;

					while (taskRun) {

						String cmd = Common.InputString("> ");
						ArrayList<String> args = new ArrayList<>(Arrays.asList(cmd.split(" ")));
						if (args.size() > 0) {
							cmd = args.get(0).toLowerCase();
							args.remove(0);

							switch (cmd) {
								case "list": {
									Common.Println("TODO list contains:");
									for (String value : toDoList) {
										Common.Println(value);
									}
									break;
								}
								case "add": {
									if (args.size() < 1) break;
									int index = -1;
									StringBuilder taskB = new StringBuilder();
									try {
										index = Integer.parseInt(args.get(0));
										for (int i = 1; i < args.size(); i++) {
											taskB.append(args.get(i));
										}
									} catch (NumberFormatException e) {
										for (int i = 0; i < args.size(); i++) {
											taskB.append(args.get(i));
										}
									}
									String task = taskB.toString();
									if (index != -1) {
										toDoList.add(index, task);
									} else {
										toDoList.add(task);
									}
									break;
								}
								case "edit": {
									if (args.size() < 2) break;
									int index = -1;
									StringBuilder taskB = new StringBuilder();
									try {
										index = Integer.parseInt(args.get(0));
										for (int i = 1; i < args.size(); i++) {
											taskB.append(args.get(i));
										}
									} catch (NumberFormatException e) {
										break;
									}
									if (index != -1) {
										toDoList.set(index, taskB.toString());
									}
									break;
								}
								case "delete": {
									if (args.size() < 1) break;
									int index = -1;
									try {
										index = Integer.parseInt(args.get(0));
									} catch (NumberFormatException e) {
										break;
									}

									toDoList.remove(index);
									break;
								}
								case "exit" : {
									taskRun = false;
									break;
								}
							}
						}
					}
					break;
				}

				case 1: {
					TreeSet<String> emailsSet = new TreeSet<>();

					boolean taskRun = true;

					while (taskRun) {

						String cmd = Common.InputString("> ");
						ArrayList<String> args = new ArrayList<>(Arrays.asList(cmd.split(" ")));
						if (args.size() > 0) {
							cmd = args.get(0).toLowerCase();
							args.remove(0);

							switch (cmd) {
								case "list": {
									Common.Println("e-mails set contains:");
									for (String value : emailsSet) {
										Common.Println(value);
									}
									break;
								}
								case "add": {
									if (args.size() < 1) break;
									String email = args.get(0);
									Pattern pattern = Pattern.compile(regex);
									Matcher matcher = pattern.matcher(email);
									if (matcher.matches()){
										emailsSet.add(email);
										Common.Println("E-mail " + email + " is valid.");
									}else {
										Common.Println("E-mail " + email + " is invalid!");
									}
									break;
								}
							}
						}
					}
					break;
				}
				default: {
					Common.Println("Could not find task you asked for. Check your input.");
					break;
				}
			}
		}
	}
}
