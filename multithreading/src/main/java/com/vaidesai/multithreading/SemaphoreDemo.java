package com.vaidesai.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*-
 * 
 * 	- semaphore maintains a set of permits
 *	- acquire() -> if a permit is available then takes it
 *	- release() -> adds a permit
 *		
 *	Semaphore just keeps a count of the number available
 *	Semaphore(int permits, boolean fair) !!!
 */

enum Downloader {

	INSTANCE;

	// max 3 downloads at a time
	private Semaphore s = new Semaphore(3, true);

	public void downloadData() throws Exception {
		try {
			s.acquire();
			download();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			s.release();
		}
	}

	public void download() throws Exception {
		System.out.println("Downloading...");
		Thread.sleep(3000);
		System.out.println("Downloading done");
	}
}

public class SemaphoreDemo {

	public static void main(String[] args) {

		ExecutorService svc = Executors.newCachedThreadPool();

		// try downloading 10 things together
		// only 3 will be allowed at a time
		for (int i = 1; i <= 10; i++) {
			svc.execute(new Runnable() {
				public void run() {
					try {
						Downloader.INSTANCE.downloadData();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		svc.shutdown();

	}
}
