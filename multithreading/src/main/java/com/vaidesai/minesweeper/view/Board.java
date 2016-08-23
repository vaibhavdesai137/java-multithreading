package com.vaidesai.minesweeper.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.vaidesai.minesweeper.constants.Constants;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;

	private Cell[] cells;
	private int nMines;

	public Board() {
		initClass();
		setLayout(new GridLayout(Constants.BOARD_ROWS, Constants.BOARD_COLS));
		initBoard();
	}

	public void initClass() {
		this.cells = new Cell[Constants.BOARD_ROWS * Constants.BOARD_COLS];
		this.nMines = 0;
	}

	// create cells
	public void initBoard() {
		for (int i = 0; i < Constants.BOARD_ROWS * Constants.BOARD_COLS; i++) {
			cells[i] = new Cell(i + 1);
			cells[i].setBackground(Color.GRAY);
			add(cells[i]);
		}
	}

	// on stop button click
	public void clearBaord() {
		for (int i = 0; i < Constants.BOARD_ROWS * Constants.BOARD_COLS; i++) {
			cells[i].setBackground(Color.GRAY);
		}
	}

	public void setMine(int index) throws Exception {
		cells[index].lockCell();
		incrementMinesCount();
		cells[index].setBackground(Color.RED);
		cells[index].unlockCell();

		Thread.sleep(500);
	}

	public void sweepMine(int index) throws Exception {
		cells[index].lockCell();
		decrementMinesCount();
		cells[index].setBackground(Color.WHITE);
		cells[index].unlockCell();

		Thread.sleep(500);
	}

	public synchronized void incrementMinesCount() {
		nMines++;
	}

	public synchronized void decrementMinesCount() {
		nMines--;
	}

	public int getNumberOfMines() {
		return nMines;
	}
}
