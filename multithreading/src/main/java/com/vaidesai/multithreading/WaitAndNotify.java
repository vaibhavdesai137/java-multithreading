package com.vaidesai.multithreading;

class Processor1 {

	public void produce() throws Exception {

		// get a lock at class level
		synchronized (this) {
			System.out.println("Producer waiting...");

			// wait for someone else to notify.
			// please note that "synchronized" on some other thread will now
			// work since this one is waiting.
			wait();
			System.out.println("Producer done waiting");
		}
	}

	public void consume() throws Exception {

		Thread.sleep(2000);

		// get a lock if no one has it or if there or waiting threads for
		// notification.
		synchronized (this) {

			System.out.println("In Consumer");
			Thread.sleep(3000);

			// notify waiting thread
			notify();
		}
	}
}

public class WaitAndNotify {

	static Processor1 processor = new Processor1();

	public static void main(String[] args) throws Exception {

		Thread producer = new Thread(new Runnable() {
			public void run() {
				try {
					processor.produce();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Thread consumer = new Thread(new Runnable() {
			public void run() {
				try {
					processor.consume();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// start both threads
		// producer will get blocked since "wait()" is specified
		// consumer will unblock producer by "notify()"
		producer.start();
		consumer.start();

		producer.join();
		consumer.join();

		System.out.println("Finished...");

	}
}
