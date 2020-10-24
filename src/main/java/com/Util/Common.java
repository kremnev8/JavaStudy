/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {

    private static BufferedReader reader;
    private static Random rnd;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        rnd = new Random();
    }

    public static void Println(String out){
        System.out.println(out);
    }

    public static void Print(String out){
        System.out.print(out);
    }

    public static int InputInt(String message) {
        Print(message);
        int input = 0;
        try {
            input = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            Println("Invalid Input!");
        } catch (NumberFormatException e) {
            Println("Invalid Input!");
        }
        return input;
    }

    public static String InputString(String message) {
        Print(message);
        try {
            return reader.readLine();
        } catch (IOException e) {
            Println("Something went wrong!");
        }
        return "";
    }

    public static boolean InputQuestion(String message) {
        Print(message + "(Yes/No): ");
        try {
            String input = reader.readLine();
            return (input.toLowerCase().contains("y"));

        } catch (IOException e) {
            Println("Something went wrong!");
        }
        return false;
    }


    public static int PickRandom(int type, int min, int max){
        if (type == 0) {
            return (int) (Math.random() * (max - min)) + min;
        }else {
            return rnd.nextInt(max - min) + min;
        }
    }

    public static int Factorial(int num){
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    public static String truncate(String value, int length)
    {
        if (value != null && value.length() > length)
            value = value.substring(0, length);
        return value;
    }

    public static boolean validatePhoneNumber(String number) {
        int numberCount = 0;

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (Character.isDigit(c)) {
                numberCount++;
            }
        }

        if (numberCount != 10 && numberCount != 11) {
            return false;
        }

        Pattern numberPattern = Pattern.compile("\\+?[78]?[\\s\\-]?\\(?(\\d{3})\\)?[\\s\\-]?(\\d{3})[\\s\\-]?(\\d{2})[\\s\\-]?(\\d{2})");
        Matcher matcher = numberPattern.matcher(number);
        return matcher.find();
    }

    public static String formatPhoneNumber(String number) {
        if (!validatePhoneNumber(number)) return "";

        Pattern numberPattern = Pattern.compile("\\+?[78]?[\\s\\-]?\\(?(\\d{3})\\)?[\\s\\-]?(\\d{3})[\\s\\-]?(\\d{2})[\\s\\-]?(\\d{2})");
        Matcher matcher = numberPattern.matcher(number);
        if (matcher.find()) {
            return String.format("+7 (%03d) %03d-%02d-%02d", Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        }
        return "";
    }
}
