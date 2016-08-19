package com.vaidesai.diningphilosophers;

import java.util.Random;

public class Philosopher implements Runnable {

	private int id;
	private ChopStick leftChopStick;
	private ChopStick rightChopStick;
	private volatile boolean isFull;
	private Random random;
	private int eatingCounter;

	public Philosopher(int id, ChopStick leftChopStick, ChopStick rightChopStick) {
		this.id = id;
		this.leftChopStick = leftChopStick;
		this.rightChopStick = rightChopStick;
		this.random = new Random();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ChopStick getLeftChopStick() {
		return leftChopStick;
	}

	public void setLeftChopStick(ChopStick leftChopStick) {
		this.leftChopStick = leftChopStick;
	}

	public ChopStick getRightChopStick() {
		return rightChopStick;
	}

	public void setRightChopStick(ChopStick rightChopStick) {
		this.rightChopStick = rightChopStick;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public int getEatingCounter() {
		return eatingCounter;
	}

	public void setEatingCounter(int eatingCounter) {
		this.eatingCounter = eatingCounter;
	}

	public void run() {

		try {

			while (!isFull) {

				think();

				// try picking up both chopsticks
				if (leftChopStick.pick(this, State.LEFT)) {
					if (rightChopStick.pick(this, State.RIGHT)) {

						eat();

						// give u both chopsticks
						rightChopStick.drop(this, State.LEFT);
						leftChopStick.drop(this, State.RIGHT);
					}
				}
			}
		} catch (Exception e) {

		}
	}

	public void think() throws Exception {
		System.out.println(this + " is thinking...");
		Thread.sleep(random.nextInt(1000));
		System.out.println(this + " thinking done");
	}

	public void eat() throws Exception {
		System.out.println(this + " is eating...");
		eatingCounter++;
		Thread.sleep(random.nextInt(1000));
		System.out.println(this + " eating done");
	}

	@Override
	public String toString() {
		return "Philosopher-" + id;
	}

}
