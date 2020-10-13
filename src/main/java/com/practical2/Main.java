/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical2;

import com.Util.Common;
import com.Util.Rect;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        boolean running = true;

        while (running) {

            int prgID = Common.InputInt("Choose which program to run(0-1): ");

            switch (prgID) {
                case 0: {
                    Rect shape1 = new Rect(10, 20);
                    Common.Println("Rect: a: " + shape1.getA() + ", b: " + shape1.getB());
                    Common.Println("Perimeter: " + shape1.GetPerimeter() + ", Area: " + shape1.GetArea());
                    break;
                }
                case 1: {
                    List<Dog> Dogs = new ArrayList<>();

                    while (true) {
                        Common.Println("Dog house contents:");
                        for (Dog dog : Dogs) {
                            Common.Println(dog.toString());
                        }

                        boolean add = Common.InputQuestion("Add another dog?");
                        if (add) {

                            String name = Common.InputString("Dog's name: ");
                            int age = Common.InputInt("Dog's age: ");
                            Dog dog = new Dog(name, age);

                            Dogs.add(dog);
                            Common.Println("Added dog successfully!");
                        } else {

                            break;
                        }
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
