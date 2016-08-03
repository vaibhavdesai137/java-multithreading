package com.vaidesai.multithreading;

/*-
 * 
 * The priority of a thread -> importance of a thread to the scheduler
 * 
 * However, this doesn’t mean that threads with lower priority aren’t run (so
 * you can’t get deadlocked because of priorities)
 * 
 * Lower-priority threads just tend to run less often
 * 
 * - The vast majority of the time, all threads should run at the default
 * priority. Trying to manipulate thread priorities is usually a mistake !!!
 * 
 * getPriority() 
 * setPriority()
 * Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
 * 
 * - Although the JDK has 10 priority levels, this doesn’t map well to many
 * operating systems: we should use 3 types 
 * 		MAX_PRIORITY - NORM_PRIORITY - MIN_PRIORITY
 * 
 * Thread.yield(); -> This static method is essentially used to notify the system that
 * the current thread is willing to give up the CPU for a while
 * 
 * Technically, in process scheduler's terminology, the executing thread is put
 * back into the ready queue of the processor and waits for its next turn !!!
 * 
 */

class MyWorker implements Runnable {

	private String name;

	public MyWorker(String name) {
		this.name = name;
	}

	public void run() {
		while (true) {
			System.out.println("Thread name: " + this);
		}
	}

	@Override
	public String toString() {
		return this.name;
	}

}

public class Priority {

	public static void main(String[] args) {

		Thread t1 = new Thread(new MyWorker("Minion1"));
		t1.setPriority(Thread.MAX_PRIORITY);

		Thread t2 = new Thread(new MyWorker("Minion2"));
		t2.setPriority(Thread.MIN_PRIORITY);

		// start both threads
		// t1 should run a lot more than t2 since the priorities are set
		t1.start();
		t2.start();
	}
}
