package com.vaidesai.multithreading;

// new thread via interface 
class CounterRunnable1 implements Runnable {

	public void run() {

		for (int i = 0; i < 10; i++) {

			System.out.println("CounterRunnable: " + i);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

// new thread via extends
class CounterThread1 extends Thread {

	@Override
	public void run() {

		for (int i = 0; i < 10; i++) {

			System.out.println("CounterThread: " + i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

public class StartThreads {

	public static void main(String[] args) {

		// interface
		Thread t1 = new Thread(new CounterRunnable1());
		t1.start();

		// extends
		Thread t2 = new CounterThread1();
		t2.start();
	}
}
