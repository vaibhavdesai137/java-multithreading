package com.vaidesai.library;

import java.util.Random;

public class Student implements Runnable {

	private int id;
	private Book[] books;

	public Student(int id, Book[] books) {
		this.id = id;
		this.books = books;
	}

	public void run() {

		Random random = new Random();

		while (true) {

			// select a book randomly
			int bookId = random.nextInt(Constants.NUMBER_OF_BOOKS);

			try {
				books[bookId].read(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "Student-" + this.id;
	}

}
