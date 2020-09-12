package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

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
}
