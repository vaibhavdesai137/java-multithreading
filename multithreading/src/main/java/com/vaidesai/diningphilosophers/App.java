package com.vaidesai.diningphilosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static int getLeftChopStick(int philosopherId) {
		// left chopstick id will be same as the philosophers id
		int leftChopStickId = philosopherId;
		return leftChopStickId;
	}

	public static int getRightChopStick(int philosopherId) {
		int rightChopStickId = (philosopherId - 1) < 0 ? Constants.NUMBER_OF_CHOPSTICKS - 1
				: philosopherId - 1;
		return rightChopStickId;
	}

	public static void main(String[] args) throws Exception {

		ExecutorService exSvc = Executors
				.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);

		Philosopher[] philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
		ChopStick[] chopsticks = new ChopStick[Constants.NUMBER_OF_CHOPSTICKS];

		try {

			// init chopsticks
			for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; i++) {
				chopsticks[i] = new ChopStick(i);
			}

			// init philosophers
			for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHERS; i++) {

				int leftChopStickId = App.getLeftChopStick(i);
				ChopStick left = chopsticks[leftChopStickId];

				int rightChopStickId = App.getRightChopStick(i);
				ChopStick right = chopsticks[rightChopStickId];

				// give this philosophers left & right to the constructor
				philosophers[i] = new Philosopher(i, left, right);
			}

			// start all philosophers
			for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHERS; i++) {
				exSvc.execute(philosophers[i]);
			}

			// run the simulation for configured time
			Thread.sleep(Constants.SIMULATION_RUNNING_TIME_IN_MS);

			// break the while loops in philosopher class
			for (Philosopher p : philosophers) {
				// note that "isFull" is a volatile boolean
				p.setFull(true);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			exSvc.shutdown();

			// wait for all threads to terminate
			while (!exSvc.isTerminated()) {
				Thread.sleep(1000);
			}

			// now check how many times each philosopher ate
			for (Philosopher p : philosophers) {
				System.out.println(p + " ate " + p.getEatingCounter()
						+ " times");
			}
		}

	}

}
