package com.vaidesai.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*-
 * This is used to synchronize one or more tasks by forcing them to wait for the completion of a set of operations being performed by other tasks
 * 
 * 		- You give an initial count to a CountDownLatch object, and any task that calls await()
 * 				on that object will block until the count reaches zero
 * 
 * 		- Other tasks may call countDown() on the object to reduce the count, presumably when a task finishes its job
 * 		- A CountDownLatch is designed to be used in a one-shot fashion; the count cannot be reset !!!
 * 				If you need a version that resets the count, you can use a CyclicBarrier instead
 * 
 * 		- The tasks that call countDown() are not blocked when they make that call. Only the call to await() is blocked until the count reaches zero
 * 
 * 		A typical use is to divide a problem into n independently solvable tasks and create a CountDownLatch with a value of n.
 * 		When each task is finished it calls countDown() on the latch. Tasks waiting for the problem to be solved call await() 
 * 		on the latch to hold themselves back until it is completed.
 * 
 * 		Another common use case is during server startups where you want a bunch of modules to get initialized before moving on. We pass
 * 		a latch object to all such modules and each module can then countdown() on it. Once all modules have initialized, the main flow can continue.
 * 
 *  	Also, countdownlatch.await is same as Thread.join(). But to use join(), you have to deal with threads dierctly. If you are using ExecutorService, 
 *  	they will not expose the threads.join since it is dealt with internally. Thus, when using ExecutorService, you will need to use CountDownLatch if 
 *  	you want to wait for threads to finish.
 *
 */

class Worker2 implements Runnable {

	private int id;
	private CountDownLatch latch;

	public Worker2(int id, CountDownLatch latch) {
		this.id = id;
		this.latch = latch;
	}

	public void run() {

		try {
			System.out.println("Running thread: " + id);
			Thread.sleep(3000);

			// release the latch
			latch.countDown();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

public class CountDownLatchDemo {

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(3);

		// create threads (max 2 allowed to run at a time)
		// pass them the latch so they can call countdown() on it
		ExecutorService svc = Executors.newFixedThreadPool(2);
		for (int i = 1; i <= 3; i++) {
			svc.execute(new Worker2(i, latch));
		}

		// block the execution till all 3 guys have counted down on their
		// latches
		try {
			System.out.println("");
			System.out.println("Waiting for all threads to finish...");
			System.out.println("");
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("");
		System.out.println("All threads finished execution");

	}

}
