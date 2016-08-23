package com.vaidesai.minesweeper.worker;

import java.util.Random;

import com.vaidesai.minesweeper.constants.Constants;
import com.vaidesai.minesweeper.view.Board;

public class MineSweeper implements Runnable {

	private int id;
	private Board board;
	private volatile boolean running;

	public MineSweeper(int id, Board board) {
		this.id = id;
		this.board = board;
		running = true;
	}

	public void run() {

		Random random = new Random();

		while (running) {

			// when stop button clicked
			if (Thread.currentThread().isInterrupted()) {
				break;
			}

			// generate random cell index
			int idx = random.nextInt(Constants.BOARD_ROWS
					* Constants.BOARD_COLS);

			try {

				// sweep the mine
				board.sweepMine(idx);

				Thread.sleep(200);

			} catch (Exception e) {

			}
		}

	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public String toString() {
		return "MS-" + id;
	}

}
