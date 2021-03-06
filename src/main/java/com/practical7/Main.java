/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical7;

import com.Util.Common;

import java.util.ArrayList;
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
                    ArrayList<String> words = new ArrayList<>();
                    String tmp = "";
                    for (int i = 0; i < text.length(); i++) {
                        char ch = text.charAt(i);
                        if (ch != ' '){
                            tmp += ch;
                        }else {
                            words.add(tmp);
                            tmp = "";
                        }
                    }
                    words.add(tmp);

                    String lastName = "";

                    Map<String, Integer> salaries = new HashMap<>();


                    for (int i = 0; i < words.size(); i++) {
                        String word = words.get(i);
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
                    boolean valid = Common.validatePhoneNumber(phoneNumber);
                    if (valid) {
                        String formattedNumber = Common.formatPhoneNumber(phoneNumber);
                        Common.Println("Thanks, your phone number is valid. Formatted number: " + formattedNumber);
                    } else {
                        Common.Println("Your entered string does not contain a valid phone number!");
                    }
                    break;
                }
                default: {
                    Common.Println("Could not find task you asked for. Check your input.");
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
