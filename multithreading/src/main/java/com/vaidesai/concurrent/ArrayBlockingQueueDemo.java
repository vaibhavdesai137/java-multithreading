package com.vaidesai.concurrent;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*-
 * 
 * 	BlockingQueue -> an interface that represents a queue that is thread safe
 * 		Put items or take items from it ...
 * 
 * 		For example: one thread putting items into the queue and another thread taking items from it
 * 			at the same time !!!
 * 				We can do it with producer-consumer pattern !!!
 * 
 * 		put() putting items to the queue
 * 		take() taking items from the queue
 * 
 * 		Producer-Consumer from bottom up:
 * 		1. LowLevelProducerConsumer.java
 * 		2. HighLevelProducerConsumer1.java
 * 		3. HoghLevelProducerConsumer2.java
 * 		4. This class ArrayBlockingQueue.java
 * 
 */

class Producer implements Runnable {

	private BlockingQueue<String> queue;

	public Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {

		try {
			while (true) {
				int randomNum = new Random().nextInt(1000);
				String s = "SomeItem-" + randomNum;
				Thread.sleep(1000);

				// blocked till space available to put
				queue.put(s);
				System.out.println("Produced: " + s);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

class Consumer implements Runnable {

	private BlockingQueue<String> queue;

	public Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {

		try {
			while (true) {

				// blocked till something available
				String s = queue.take();
				System.out.println("Consumed: " + s);
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

public class ArrayBlockingQueueDemo {

	public static void main(String[] args) {

		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

		Thread producer = new Thread(new Producer(queue));
		Thread consumer = new Thread(new Consumer(queue));

		// runs infinitely
		consumer.start();
		producer.start();

	}

}
