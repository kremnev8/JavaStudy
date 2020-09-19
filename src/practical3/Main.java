/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package practical3;

import Util.Circle;
import Util.Common;
import Util.IShape;
import practical3.Human.Human;
import practical3.Human.SkinColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		boolean running = true;

		while (running) {

			int prgID = Common.InputInt("Choose which program to run(0-2): ");

			switch (prgID) {
				case 0 -> {
					float r = Common.InputInt("Circle Radius: ");
					float x = Common.InputInt("Circle X: ");
					float y = Common.InputInt("Circle Y: ");
					Circle shape1 = new Circle(r, x, y);
					Common.Println("Circle with radius: " + shape1.GetRadius());
					Common.Println("Perimeter: " + shape1.GetPerimeter() + ", Area: " + shape1.GetArea());
					Common.Println("Center: " + Arrays.toString(shape1.GetCenter()));
				}
				case 1 -> {
					Common.Println("Creating a new Human, please wait.");
					Human human = new Human(100, SkinColor.White);
					Common.Println(human.toString());
				}
				case 2 -> {
					List<Book> books = new ArrayList<>();
					List<Book> takenBooks = new ArrayList<>();
					books.add(new Book("Joan Rowling", "Harry Potter and the prisoner of Askaban", 2007, 1));
					books.add(new Book("Ernest Clain", "Ready player one", 2011, 2));
					while (true) {
						Common.Println("Book shelf contents:");

						for (int i = 0; i < books.size(); i++) {
							Common.Println((i + 1) + ": " + books.get(i).toString());
						}

						Common.Println("My books:");

						for (int i = 0; i < takenBooks.size(); i++) {
							Common.Println((i + 1) + ": " + takenBooks.get(i).toString());
						}

						boolean add = Common.InputQuestion("Add another book?");
						if (add) {

							String name = Common.InputString("Book's name: ");
							String author = Common.InputString("Book's author: ");
							int year = Common.InputInt("Publishing year: ");
							int edition = Common.InputInt("Edition: ");
							Book book = new Book(author, name, year, edition);

							books.add(book);
							Common.Println("Added book successfully!");
						}
						boolean read = Common.InputQuestion("Want to read a book?");
						if (read && takenBooks.size() == 0) {
							int num = Common.InputInt("Enter book number: ") - 1;
							if (num >= 0 && num < books.size()) {
								boolean result = Common.InputQuestion("You want to read " + books.get(num).GetName());
								if (result) {
									takenBooks.add(books.remove(num));
								}
							} else {
								Common.Println("Could not find this book!");
							}
						} else {
							Common.Println("You already got books, return your books first!");
						}
						if (takenBooks.size() > 0) {
							boolean returnBook = Common.InputQuestion("Want to return a book?");
							if (returnBook && takenBooks.size() > 0) {
								int num = Common.InputInt("Enter book number: ") - 1;
								if (num >= 0 && num < takenBooks.size()) {
									boolean result = Common.InputQuestion("You want to return " + takenBooks.get(num).GetName());
									if (result) {
										books.add(takenBooks.remove(num));
									}
								} else {
									Common.Println("Could not find this book!");
								}
							}
						}
						if (!add && !read) {
							break;
						}
					}
				}
			}
			boolean runMore = Common.InputQuestion("Do you want to run another program?");
			if (!runMore) {
				running = false;
			}
		}
	}
}
