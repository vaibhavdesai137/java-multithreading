package com.vaidesai.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocks {

	private static int counter = 0;
	private static Lock lock = new ReentrantLock();

	public static void implement() {
		lock.lock();
		counter++;
		lock.unlock();
	}

	public static void first() {
		for (int i = 1; i <= 1000; i++) {
			implement();
		}
	}

	public static void second() {
		for (int i = 1; i <= 1000; i++) {
			implement();
		}
	}

	public static void main(String[] args) throws Exception {

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					first();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					second();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// start both threads
		t1.start();
		t2.start();

		t1.join();
		t2.join();

		// should be 2000
		System.out.println("Counter: " + counter);
	}
}
