package com.vaidesai.conway.app;

import java.awt.BorderLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.vaidesai.conway.constants.Constants;
import com.vaidesai.conway.view.Board;
import com.vaidesai.conway.view.ButtonListener;
import com.vaidesai.conway.view.Controller;
import com.vaidesai.conway.view.GenerationsPanel;
import com.vaidesai.conway.view.Toolbar;

public class MainFrame extends JFrame implements ButtonListener {

	private static final long serialVersionUID = 1L;
	private Board board;
	private Toolbar toolbar;
	private GenerationsPanel timePanel;
	private ExecutorService executor;

	public MainFrame() {

		super("Conway's Simulation App");

		toolbar = new Toolbar();
		timePanel = new GenerationsPanel();
		board = new Board(timePanel);

		toolbar.setButtonListener(this);

		add(board, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);
		add(timePanel, BorderLayout.SOUTH);

		setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(this);
	}

	public void startClicked() {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				toolbar.setStartButton(false);
				toolbar.setRestartButton(true);
			}
		});

		Controller.startThread();
		executor = Executors.newSingleThreadExecutor();
		executor.execute(new Controller(board));
	}

	public void restartClicked() {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				toolbar.setStartButton(true);
				toolbar.setRestartButton(false);
			}
		});

		executor.shutdown();
		Controller.stopThread();
		board.resetBorder();
		timePanel.refreshCounter();
	}
}
