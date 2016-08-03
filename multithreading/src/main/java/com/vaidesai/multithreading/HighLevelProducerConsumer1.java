package com.vaidesai.multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// This example is same as WaitAndNotify but using high level locks instead of using synchronized.
// Here, condition.await() is same as "wait()"
// And consition.signal() is same as "notify()" 
class Processor3 {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void produce() throws Exception {
		lock.lock();
		System.out.println("In Producer");
		condition.await();
		System.out.println("In Producer Again");
		lock.unlock();
	}

	public void consume() throws Exception {
		lock.lock();
		Thread.sleep(2000);
		System.out.println("In Consumer");
		Thread.sleep(3000);
		condition.signal();
		lock.unlock();
	}
}

public class HighLevelProducerConsumer1 {

	static Processor3 processor = new Processor3();

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

		producer.join();
		consumer.join();

		System.out.println("Finished...");
	}

}
