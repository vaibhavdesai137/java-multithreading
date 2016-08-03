package com.vaidesai.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*-
 * 
 *  1.) ExecutorService es = Executors.newCachedThreadPool();
 *  	- going to return an executorService that can dynamically
 *  		reuse threads
 *		- before starting a job -> it going to check whether there are any threads that
 *			finished the job...reuse them
 *		- if there are no waiting threads -> it is going to create another one 
 *		- good for the processor ... effective solution !!!
 *
 *	2.) ExecutorService es = Executors.newFixedThreadPool(N);
 *		- maximize the number of threads
 *		- if we want to start a job -> if all the threads are busy, we have to wait for one
 *			to terminate
 *
 *	3.) ExecutorService es = Executors.newSingleThreadExecutor();
 *		It uses a single thread for the job
 *
 *		execute() -> runnable + callable
 *		submit() -> runnable

 */

class Executor2 implements Runnable {

	public void run() {
		for (int i = 1; i <= 3; i++) {
			try {
				Thread.sleep(300);
				System.out.println(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

public class ExecServiceSingleThread {

	public static void main(String[] args) {

		// 1 thread only
		// Call the same 5 times though
		// Should print: 1 2 3 1 2 3 1 2 3 1 2 3 1 2 3
		ExecutorService svc = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			svc.execute(new Executor2());
		}
		svc.shutdown();
	}
}
