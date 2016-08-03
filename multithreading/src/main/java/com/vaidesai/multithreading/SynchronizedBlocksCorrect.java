package com.vaidesai.multithreading;

public class SynchronizedBlocksCorrect {

	private static int count1 = 0;
	private static int count2 = 0;

	private static Object lock1 = new Object();
	private static Object lock2 = new Object();

	// explicit locking on object, not at class level.
	// does not block addCount2() from running
	public static void addCount1() {
		synchronized (lock1) {
			count1++;
		}
	}

	// explicit locking on object, not at class level.
	// does not block addCount2() from running
	public static void addCount2() {
		synchronized (lock2) {
			count2++;
		}
	}

	public static void add() {
		for (int i = 1; i <= 1000; i++) {
			addCount1();
			addCount2();
		}
	}

	public static void main(String[] args) throws Exception {

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				add();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				add();
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		// results are correct
		System.out.println("Count 1: " + count1);
		System.out.println("Count 2: " + count2);

	}
}
