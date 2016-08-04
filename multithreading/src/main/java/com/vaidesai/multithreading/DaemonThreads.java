package com.vaidesai.multithreading;

/*-
 * Runs in background
 * 
 * 1. When JVM halts, all daemon threads are abaondoned
 * 2. For normal threads, JVm won't get killed until the threads are terminated
 * 
 * Also, finally blocks not executed for daemon threads
 * Use sparingly. Don't abuse these threads
 */

public class DaemonThreads {

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {

			public void run() {

				while (true) {
					try {
						System.out.println("Still running...");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		});

		// set as daemon
		t1.setDaemon(true);

		// start it
		t1.start();

		// here, jvm will terminate since parent/main thread is done execution
		// this will kill the daemon thread too
	}
}
