package study;

import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
	// write your code here

        boolean running = true;

        while (running) {

            int prgID = Common.InputInt("Choose which program to run(0-4): ");

            switch (prgID) {
                case 0: {
                    int[] array = {1, 3, 5, 7, 11, 13};
                    int sum = 0;

                    for (int i = 0; i < array.length; i++) {
                        sum += array[i];
                    }
                    Common.Println("Array sum: " + sum);
                    break;
                }
                case 1: {

                    String output = "";

                    for (int i = 0; i < args.length; i++) {
                        output += " " + args[i];
                    }
                    Common.Println("Arguments:" + output);
                    break;
                }
                case 2: {
                    int num = Common.InputInt("Number of elements to print: ");

                    DecimalFormat formatter = new DecimalFormat("###.###");
                    String output = "";

                    for (int i = 1; i <= num; i++) {
                        double curNum = 1 / (double) i;
                        output += (i == 1 ? "" : " ") + formatter.format(curNum);
                    }

                    Common.Println("First " + num + " elements of Harmonic Series:");
                    Common.Println(output);
                    break;
                }
                case 3: {
                    int num = Common.InputInt("Number of elements: ");

                    int[] array = new int[num];

                    int selection = Common.InputInt("Select 0 - Math.random() or 1 - Random: ");
                    String output = "";

                    for (int i = 0; i < num; i++) {
                        array[i] = Common.PickRandom(selection, 0, 1024);
                        output += (i == 0 ? "" : " ") + array[i];
                    }

                    Common.Println(output);
                    output = "";

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
                        output += (i == 0 ? "" : " ") + array[i];
                    }

                    Common.Println(output);
                    break;

                }
                case 4: {
                    int num = Common.InputInt("Calculate factorial of?: ");
                    int res = Common.Factorial(num);
                    Common.Println("Result: " + res);
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
