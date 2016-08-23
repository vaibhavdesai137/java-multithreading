package com.vaidesai.conway.view;

import com.vaidesai.conway.constants.Constants;

public class Controller implements Runnable {

	private Board board;
	private volatile static boolean keepGoing = true;

	public Controller(Board board) {
		this.board = board;
	}

	public void run() {

		while (!Thread.currentThread().isInterrupted() && keepGoing) {

			board.newIteration();

			try {
				Thread.sleep(Constants.TIME_LAG);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}

	public static void startThread() {
		keepGoing = true;
	}

	public static void stopThread() {
		Thread.currentThread().interrupt();
		keepGoing = false;
	}
}
