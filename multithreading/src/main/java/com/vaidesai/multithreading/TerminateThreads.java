package com.vaidesai.multithreading;

/*-
 * Interrupt threads -> for example making I/O operations and the user stop the 
 * 		operation via UI ... we have to terminate the thread
 * 
 * 			boolean Thred.isInterrupted() -> check whether is it interrupted
 * 			boolean interrupted() -> checks + interrupt the thread !!!
 * 
 * Terminate a thread -> volatile boolean flags !!!
 * 
 * Thread states:
 * 
 * 		1.) RUNNABLE: if we create a new thread + call start() method
 * 				The run() method can be called...
 * 					new MyThread().start();
 * 
 * 		2.) BLOCKED: if it is waiting for an object's monitor lock
 * 			- waiting to enter a synchronized block
 * 			- after wait() waiting for the monitor lock to be free
 * 
 * 		3.) WAITING: when we call wait() on a thread ... it is going to loose
 * 				the monitor lock and wait for notify() 
 * 					notifyAll()
 *	
 *		4.) TERMINATED: when the run() method is over ...
 *			We can check it with isAlive() method
 *
 */

class Worker1 extends Thread {

	// industry wide design pattern instead of using the boolean approach
	// private volatile boolean isRunning = true;
	private volatile Thread thread = this;

	public void finish() {
		thread = null;
	}

	@Override
	public void run() {

		while (this.thread == this) {

			System.out.println("Still running...");

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class TerminateThreads {

	public static void main(String[] args) throws Exception {

		// start the thread
		Worker1 w = new Worker1();
		w.start();

		// let that run for 5 seconds
		Thread.sleep(5000);

		// then set that thread to null essentially killing it
		w.finish();

		System.out.println("Finished");
	}
}
