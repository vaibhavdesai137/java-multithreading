package com.vaidesai.library;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {

	private int id;
	private Lock lock;

	public Book(int id) {
		this.id = id;
		this.lock = new ReentrantLock();
	}

	public void read(Student s) throws Exception {

		// lock the book before reading
		lock.tryLock(10, TimeUnit.MINUTES);

		// simulate reading
		System.out.println(s + " is reading " + this);
		Thread.sleep(2000);

		// leave the lock
		lock.unlock();
		System.out.println(s + " has finished reading " + this);
	}

	@Override
	public String toString() {
		return "Book-" + this.id;
	}

}
