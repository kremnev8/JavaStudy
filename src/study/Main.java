package study;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {



    public static void main(String[] args) {
	// write your code here

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose which program to run(0-4): ");
        int prgID = 0;
        try {
             prgID = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (prgID) {
            case 0:{
                int[] array = {1, 3, 5, 7, 11, 13};
                int sum = 0;

                for (int i = 0; i < array.length; i++){
                    sum += array[i];
                }
                System.out.println("Array sum: " + sum);
            }
        }
    }
}
