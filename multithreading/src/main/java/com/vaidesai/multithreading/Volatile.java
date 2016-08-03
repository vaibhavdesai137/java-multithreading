package com.vaidesai.multithreading;

class Worker implements Runnable {

	private volatile boolean isTerminated = false;

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}

	public void run() {

		// this boolean may get cached and the value may not be reflected if it
		// was set in a separate thread since this is running in its own thread.
		// that is why it has to be set as volatile to not get cached.

		// volatile forces the var to be read from main memory no matter what.
		// always use volatile when sharing vars between threads
		while (!isTerminated) {
			System.out.println("Hello");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

public class Volatile {

	public static void main(String[] args) throws Exception {

		// create a thread and start it
		Worker w = new Worker();
		Thread t1 = new Thread(w);
		t1.start();

		// wait for sometime
		Thread.sleep(5000);

		// now flip the boolean
		w.setTerminated(true);

		System.out.println("Finished");
	}

}
