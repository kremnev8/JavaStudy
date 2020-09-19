package practical1;

import Util.Common;

import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
	// write your code here

        boolean running = true;

        while (running) {

            int prgID = Common.InputInt("Choose which program to run(0-4): ");

            switch (prgID) {
                case 0 -> {
                    int[] array = {1, 3, 5, 7, 11, 13};
                    int sum = 0;

                    for (int j : array) {
                        sum += j;
                    }
                    Common.Println("Array sum: " + sum);
                }
                case 1 -> {

                    StringBuilder output = new StringBuilder();

                    for (String arg : args) {
                        output.append(" ").append(arg);
                    }
                    Common.Println("Arguments:" + output);
                }
                case 2 -> {
                    int num = Common.InputInt("Number of elements to print: ");

                    DecimalFormat formatter = new DecimalFormat("###.###");
                    StringBuilder output = new StringBuilder();

                    for (int i = 1; i <= num; i++) {
                        double curNum = 1 / (double) i;
                        output.append(i == 1 ? "" : " ").append(formatter.format(curNum));
                    }

                    Common.Println("First " + num + " elements of Harmonic Series:");
                    Common.Println(output.toString());
                }
                case 3 -> {
                    int num = Common.InputInt("Number of elements: ");

                    int[] array = new int[num];

                    int selection = Common.InputInt("Select 0 - Math.random() or 1 - Random: ");
                    StringBuilder output = new StringBuilder();

                    for (int i = 0; i < num; i++) {
                        array[i] = Common.PickRandom(selection, 0, 1024);
                        output.append(i == 0 ? "" : " ").append(array[i]);
                    }

                    Common.Println(output.toString());
                    output = new StringBuilder();

                    for (int i = 0; i < num - 1; i++) {

                        int jMin = i;
                        for (int j = i + 1; j < num; j++) {
                            if (array[j] < array[jMin]) {
                                jMin = j;
                            }
                        }

                        if (jMin != i) {
                            int tmp = array[i];
                            array[i] = array[jMin];
                            array[jMin] = tmp;
                        }
                    }

                    for (int i = 0; i < num; i++) {
                        output.append(i == 0 ? "" : " ").append(array[i]);
                    }

                    Common.Println(output.toString());

                }
                case 4 -> {
                    int num = Common.InputInt("Calculate factorial of?: ");
                    int res = Common.Factorial(num);
                    Common.Println("Result: " + res);
                }
            }
            boolean runMore = Common.InputQuestion("Do you want to run another program?");
            if (!runMore) {
                running = false;
            }
        }
    }
}
