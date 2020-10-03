/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical3;

public class Book {
	private String author;
	private String name;
	private int year;
	private int edition;

	public Book(String author, String name, int year, int edition) {
		this.author = author;
		this.name = name;
		this.year = year;
		this.edition = edition;
	}

	public String GetAuthor() {
		return author;
	}

	public void SetAuthor(String author) {
		this.author = author;
	}

	public String GetName() {
		return name;
	}

	public void SetName(String name) {
		this.name = name;
	}

	public int GetYear() {
		return year;
	}

	public void SetYear(int year) {
		this.year = year;
	}

	public int GetEdition() {
		return edition;
	}

	public void SetEdition(int edition) {
		this.edition = edition;
	}

	@Override
	public String toString() {
		return "Book{" +
				"author: " + author +
				", name: " + name +
				", year: " + year +
				", edition: " + edition +
				'}';
	}
}
