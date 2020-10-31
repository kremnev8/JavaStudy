/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical9;

import com.Util.Circle;
import com.Util.Common;

import java.util.*;
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
									if (index >= 0 && index < toDoList.size()) {
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
									if (index >= 0 && index < toDoList.size()) {
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
									if (index >= 0 && index < toDoList.size()) {
										toDoList.remove(index);
									}
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
				case 2: {
					HashMap<String, String> phonebook = new HashMap<>();

					boolean taskRun = true;

					while (taskRun) {
						String cmd = Common.InputString("> ");
						ArrayList<String> args = new ArrayList<>(Arrays.asList(cmd.split(" ")));
						if (args.size() > 0) {
							cmd = args.get(0).toLowerCase();

							switch (cmd) {
								case "add": {
									boolean ans = Common.InputQuestion("You are entering name?");
									if (ans) {
										String name = Common.InputString("Enter name: ");
										if (name.equals("")) {
											Common.Println("Name cannot be empty!");
										} else if (phonebook.containsKey(name)) {
											String number = phonebook.get(name);
											Common.Println("Phone number for " + name + " is " + number);
										} else {
											String rawNumber = Common.InputString("This name is new. Please enter corresponding phone number:");
											if (Common.validatePhoneNumber(rawNumber)) {
												String validPhoneNumber = Common.formatPhoneNumber(rawNumber);
												phonebook.put(name, validPhoneNumber);
												Common.Println("Added entry successfully!");
											} else {
												Common.Println("Phone number " + rawNumber + " is invalid!");
											}
										}
									} else {
										String rawNumber = Common.InputString("Enter phone number: ");
										if (Common.validatePhoneNumber(rawNumber)) {
											String validPhoneNumber = Common.formatPhoneNumber(rawNumber);
											Set<String> keys = phonebook.keySet();
											boolean found = false;
											for (String key : keys) {
												if (phonebook.get(key).equals(validPhoneNumber)) {
													Common.Println("Name for phone number " + validPhoneNumber + " is " + key);
													found = true;
													break;
												}
											}
											if (!found) {
												String name = Common.InputString("This phone number is new. Please enter corresponding name:");
												if (name.equals("")) {
													Common.Println("Name cannot be empty!");
												} else {
													phonebook.put(name, validPhoneNumber);
												}
											}
										} else {
											Common.Println("Phone number " + rawNumber + " is invalid!");
										}
									}
									break;
								}
								case "list": {
									Set<String> keys = phonebook.keySet();
									boolean found = false;
									for (String key: keys) {
										Common.Println("Name: " + key + ", phone number: " + phonebook.get(key));
									}

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
				default: {
					Common.Println("Could not find task you asked for. Check your input.");
					break;
				}
			}
		}
	}
}
