/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical8;

import com.Util.Common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            int prgID = Common.InputInt("Choose which program to run(0-2): ");

            switch (prgID) {
                case 0: {

                    String text = "Vasya earned 5000 rubles, Petya - 7563 rubles, and Masha - 30000 rubles";
                    String[] words = text.split(" ");
                    String lastName = "";

                    Map<String, Integer> salaries = new HashMap<>();


                    for (int i = 0; i < words.length; i++) {
                        String word = words[i];
                        if (Character.isUpperCase(word.charAt(0))) {
                            lastName = word;
                        } else {
                            try {
                                int num = Integer.parseInt(word);
                                salaries.put(lastName, num);
                            } catch (NumberFormatException e) {

                            }
                        }
                    }

                    Common.Println("Vasya and Masha earned " + (salaries.get("Vasya") + salaries.get("Masha")) + " rubles in total!");
                    break;
                }
                case 1: {

                    String fullName = Common.InputString("Please enter your full name: ");
                    Pattern fullNamePattern = Pattern.compile("([\\w\\-]+)\\s([\\w\\-]+)\\s?([\\w\\-]+)?");
                    Matcher matcher = fullNamePattern.matcher(fullName);
                    if (matcher.find()) {
                        Common.Println("Last name: " + matcher.group(1));
                        Common.Println("First name: " + matcher.group(2));
                        if (matcher.group(3) != null) {
                            Common.Println("Middle name: " + matcher.group(3));
                        }
                    } else {
                        Common.Println("Your entered string does not contain a valid full name!");
                    }

                    break;
                }
                case 2: {
                    String phoneNumber = Common.InputString("Please enter your phone number(in any format): ");
                    int numberCount = 0;

                    for (int i = 0; i < phoneNumber.length(); i++) {
                        char c = phoneNumber.charAt(i);
                        if (Character.isDigit(c)) {
                            numberCount++;
                        }
                    }

                    if (numberCount != 10 && numberCount != 11) {
                        Common.Println("Your entered string does not contain a valid phone number!");
                        break;
                    }

                    Pattern numberPattern = Pattern.compile("\\+?[78]?[\\s\\-]?\\(?(\\d{3})\\)?[\\s\\-]?(\\d{3})[\\s\\-]?(\\d{2})[\\s\\-]?(\\d{2})");
                    Matcher matcher = numberPattern.matcher(phoneNumber);
                    if (matcher.find()) {
                        String formattedNumber = String.format("+7 (%03d) %03d-%02d-%02d", Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
                        Common.Println("Thanks, your phone number is valid. Formatted number: " + formattedNumber);
                    } else {
                        Common.Println("Your entered string does not contain a valid phone number!");
                    }
                    break;
                }
            }
            boolean runMore = Common.InputQuestion("Do you want to run another program?");
            if (!runMore) {
                running = false;
            }
        }


    }
}
