package com.vaidesai.diningphilosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {

	private Lock lock;
	private int id;

	public ChopStick(int id) {
		this.id = id;
		this.lock = new ReentrantLock();
	}

	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean pick(Philosopher p, State s) throws Exception {

		if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
			String msg = p + " picked up " + this + " in " + s + " hand";
			System.out.println(msg);
			return true;
		}

		return false;
	}

	public void drop(Philosopher p, State s) {
		lock.unlock();
		String msg = p + " dropped " + this + " from " + s + " hand";
		System.out.println(msg);
	}

	@Override
	public String toString() {
		return "chopstick-" + this.id;
	}

}
