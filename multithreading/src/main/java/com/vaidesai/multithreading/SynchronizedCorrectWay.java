package com.vaidesai.multithreading;

class CounterCorrect implements Runnable {

	public static int count = 0;

	// allows only one thread to enter.
	// the value of "count" won't get messed up.
	// this is inefficient way since this will acquire lock at the class level
	public synchronized static void increment() {
		count++;
	}

	public void run() {
		for (int i = 0; i < 10000; i++) {
			increment();
		}
	}
}

public class SynchronizedCorrectWay {

	public static void main(String[] args) throws Exception {

		CounterCorrect c = new CounterCorrect();

		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		// count will always be 20000
		System.out.println(CounterCorrect.count);
	}
}
