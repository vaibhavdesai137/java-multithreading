package com.vaidesai.multithreading;

import java.util.ArrayList;
import java.util.List;

class Processor2 {

	private List<Integer> list = new ArrayList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();

	public void produce() throws Exception {

		int value = 1;

		while (true) {

			synchronized (lock) {
				while (list.size() == LIMIT) {
					lock.wait();
				}

				list.add(value);
				value++;
				System.out.println("Producer added: " + value);
				lock.notify();
			}
		}
	}

	public void consume() throws Exception {

		while (true) {

			synchronized (lock) {
				while (list.size() == 0) {
					lock.wait();
				}

				int value = list.remove(0);
				System.out.println("Consumer consumed: " + value);
				lock.notify();
			}

			Thread.sleep(1000);
		}
	}
}

public class LowLevelProducerConsumer {

	static Processor2 processor = new Processor2();

	public static void main(String[] args) {

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
		producer.start();
		consumer.start();
	}

}
