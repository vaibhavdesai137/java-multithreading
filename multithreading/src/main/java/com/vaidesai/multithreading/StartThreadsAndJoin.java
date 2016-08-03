package com.vaidesai.multithreading;

// new thread via interface 
class CounterRunnable2 implements Runnable {

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
class CounterThread2 extends Thread {

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

public class StartThreadsAndJoin {

	public static void main(String[] args) {

		// interface
		Thread t1 = new Thread(new CounterRunnable2());
		t1.start();

		// extends
		Thread t2 = new CounterThread2();
		t2.start();

		// wait for both threads to finish before moving on
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// now all threads are done executing
		System.out.println("Finished...");
	}
}
