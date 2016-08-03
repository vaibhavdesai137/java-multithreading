package com.vaidesai.multithreading;

class CounterIncorrect implements Runnable {

	public static int count = 0;

	public void run() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}
}

public class SynchronizedIncorrectWay {

	public static void main(String[] args) throws Exception {

		CounterIncorrect c = new CounterIncorrect();

		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);

		// both threads are modyfying the same var
		// incrementing is not atomic
		// so while one thread reads the value, the other may have modified it
		t1.start();
		t2.start();

		t1.join();
		t2.join();

		// count may or may be 20000
		System.out.println(CounterIncorrect.count);
	}
}
