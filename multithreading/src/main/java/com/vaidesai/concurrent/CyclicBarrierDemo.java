package com.vaidesai.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*-
 * A CyclicBarrier is used in situations where you want to create a group of tasks 
 * to perform work in parallel + wait until they are all finished before 
 * moving on to the next step
 * 			-> something like join()
 * 			-> something like CountDownLatch
 * 
 * 		CountDownLatch: one-shot event
 * 		CyclicBarrier: it can be reused over and over again
 * 			
 * 				IMPORTANT:
 * 				+ cyclicBarrier has a barrier action: a runnable, that will run automatically 
 * 					when the count reaches 0 !!
 * 
 * 	new CyclicBArrier(N) -> N threads will wait for each other
 * 
 */

class MyBarrierAction implements Runnable {

	// this will be invoked when all threads have finished
	public void run() {
		System.out.println("");
		System.out.println("All tasks completed");
		System.out.println("");
	}

}

class Worker3 implements Runnable {

	private int id;
	private CyclicBarrier barrier;

	public Worker3(int id, CyclicBarrier barrier) {
		this.id = id;
		this.barrier = barrier;
	}

	public void run() {

		try {
			System.out.println("Thread running: " + id);
			Thread.sleep(2000);
			System.out.println("Thread finished: " + id);

			// Waits for this thread to finish.
			// When finished, barrier will update its count internally.
			// You don't explicitly say countdown() as with CountDownLatch.
			barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class CyclicBarrierDemo {

	public static void main(String[] args) {

		// barrier will wait for all 10 to finish
		// when all 10 are done, invoke the runnable of MyBarrierAction
		CyclicBarrier barrier = new CyclicBarrier(10, new MyBarrierAction());

		// for whatever reason, if this count is not as barrier's count, it
		// doesn't work
		ExecutorService svc = Executors.newFixedThreadPool(10);

		for (int i = 1; i <= 10; i++) {
			svc.execute(new Worker3(i, barrier));
		}

		svc.shutdown();
	}

}
