package com.vaidesai.minesweeper.view;

import java.awt.GridLayout;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

import com.vaidesai.minesweeper.constants.State;

public class Cell extends JPanel {

	private static final long serialVersionUID = 1L;

	private int id;
	private Lock lock;
	private State state;
	private boolean hasBomb;

	public Cell(int id) {

		this.id = id;
		this.lock = new ReentrantLock();
		this.state = State.EMPTY;
		this.hasBomb = false;

		setLayout(new GridLayout());
	}

	public void lockCell() throws Exception {
		lock.tryLock(10, TimeUnit.HOURS);
	}

	public void unlockCell() throws Exception {
		lock.unlock();
	}

	@Override
	public String toString() {
		return "Cell-" + id + ", state: " + state + ", hasBomb: " + hasBomb;
	}
}
