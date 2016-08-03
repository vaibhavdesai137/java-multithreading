package com.vaidesai.multithreading;

public class SynchronizedBlocksIncorrect {

	private static int count1 = 0;
	private static int count2 = 0;

	// class level lock.
	// locking this cuases the other the wait on addCount2
	public synchronized static void addCount1() {
		count1++;
	}

	// class level lock.
	// locking this cuases the other the wait on addCount1
	public synchronized static void addCount2() {
		count2++;
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

		// results are correct, but not efficient
		System.out.println("Count 1: " + count1);
		System.out.println("Count 2: " + count2);

	}
}
