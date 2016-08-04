package com.vaidesai.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/*-
 * It implements the BlockingQueue interface
 * 
 * 	- unbounded concurrent queue
 * 	- it uses the same ordering rules as the java.util.PriorityQueue class -> have to implement the Comparable interface
 * 			The comparable interface will determine what will the order in the queue
 * 
 * 			The priority can be the same compare() == 0 case
 * 
 *  - no null items !!!
 * 
 *
 */

class Producer1 implements Runnable {

	private BlockingQueue<Integer> queue;

	public Producer1(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	public void run() {

		try {
			queue.put(91);
			queue.put(10);
			queue.put(103);
			queue.put(43);
			System.out.println("Produced 4 values");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer1 implements Runnable {

	private BlockingQueue<Integer> queue;

	public Consumer1(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	public void run() {

		try {
			// wait for all 4 numbers to be added in queue
			// this guy will consume in asc order (default ordering for
			// Integers)
			Thread.sleep(3000);
			System.out.println("Consumed: " + queue.take());
			System.out.println("Consumed: " + queue.take());
			System.out.println("Consumed: " + queue.take());
			System.out.println("Consumed: " + queue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class PriorityBlockingQueueDemo1 {

	public static void main(String[] args) {

		// Priority will be defaulted to whatever data type you are using
		// For eg:
		// for strings, it is asc order
		// for integers, it is asc order
		// for custom objects, you have to implement Comparable interface
		BlockingQueue<Integer> queue = new PriorityBlockingQueue<Integer>();

		Thread producer = new Thread(new Producer1(queue));
		Thread consumer = new Thread(new Consumer1(queue));

		// runs infinitely
		consumer.start();
		producer.start();
	}

}
