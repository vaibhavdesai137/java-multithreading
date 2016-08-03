package com.vaidesai.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// This example is same as LowLevelProducerConsumer but using high level locks instead of using synchronized.
// Here, condition.await() is same as "wait()"
// And consition.signal() is same as "notify()" 
class Processor4 {

	private List<Integer> list = new ArrayList<Integer>();
	private final int LIMIT = 10;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void produce() throws Exception {

		int value = 1;

		while (true) {

			lock.lock();

			while (list.size() == LIMIT) {
				condition.await();
			}

			list.add(value);
			value++;
			System.out.println("Producer added: " + value);
			condition.signal();
			lock.unlock();
		}

	}

	public void consume() throws Exception {

		while (true) {

			lock.lock();
			while (list.size() == 0) {
				condition.await();
			}

			int value = list.remove(0);
			System.out.println("Consumer consumed: " + value);
			condition.signal();
			lock.unlock();

			Thread.sleep(1000);
		}

	}
}

public class HighLevelProducerConsumer2 {

	static Processor4 processor = new Processor4();

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
		producer.start();
		consumer.start();
	}

}
