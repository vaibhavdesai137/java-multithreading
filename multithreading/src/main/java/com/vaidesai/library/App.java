package com.vaidesai.library;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) {

		ExecutorService exSvc = Executors
				.newFixedThreadPool(Constants.NUMBER_OF_STUDENTS);

		Student[] students = new Student[Constants.NUMBER_OF_STUDENTS];
		Book[] books = new Book[Constants.NUMBER_OF_BOOKS];

		try {

			// init books
			for (int i = 0; i < Constants.NUMBER_OF_BOOKS; i++) {
				books[i] = new Book(i);
			}

			// init students
			for (int i = 0; i < Constants.NUMBER_OF_STUDENTS; i++) {
				students[i] = new Student(i, books);
			}

			// start all student threads
			for (int i = 0; i < Constants.NUMBER_OF_STUDENTS; i++) {
				exSvc.execute(students[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			exSvc.shutdown();
		}

	}
}
