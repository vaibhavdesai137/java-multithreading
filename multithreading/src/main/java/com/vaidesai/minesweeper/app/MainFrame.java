package com.vaidesai.minesweeper.app;

import java.awt.BorderLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import com.vaidesai.minesweeper.constants.Constants;
import com.vaidesai.minesweeper.view.Board;
import com.vaidesai.minesweeper.view.ButtonListener;
import com.vaidesai.minesweeper.view.Toolbar;
import com.vaidesai.minesweeper.worker.MineLayer;
import com.vaidesai.minesweeper.worker.MineSweeper;

public class MainFrame extends JFrame implements ButtonListener {

	private static final long serialVersionUID = 1L;

	private Toolbar toolbar;
	private Board board;

	private ExecutorService layersExSvc;
	private MineLayer[] mineLayers;

	private ExecutorService sweepersExSvc;
	private MineSweeper[] mineSweepers;

	public MainFrame() {

		// jframe title
		super("MineSweeper Simulation App");

		initGame();
	}

	public void initGame() {

		// setup & add toolbar
		toolbar = new Toolbar();
		toolbar.setButtonListener(this);
		add(toolbar, BorderLayout.NORTH);

		// setup & add board
		board = new Board();
		add(board, BorderLayout.CENTER);

		// jframe setup
		setSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void startClicked() throws Exception {

		// setup mine layers
		// start each mine layer in a separate thread
		layersExSvc = Executors.newFixedThreadPool(Constants.NUMBER_OF_LAYERS);
		mineLayers = new MineLayer[Constants.NUMBER_OF_LAYERS];
		for (int i = 0; i < Constants.NUMBER_OF_LAYERS; i++) {
			mineLayers[i] = new MineLayer(i + 1, board);
			layersExSvc.execute(mineLayers[i]);
		}

		// setup mine sweepers
		// start each mine sweeper in a separate thread
		sweepersExSvc = Executors
				.newFixedThreadPool(Constants.NUMBER_OF_SWEEPERS);
		mineSweepers = new MineSweeper[Constants.NUMBER_OF_SWEEPERS];
		for (int i = 0; i < Constants.NUMBER_OF_SWEEPERS; i++) {
			mineSweepers[i] = new MineSweeper(i + 1, board);
			sweepersExSvc.execute(mineSweepers[i]);
		}
	}

	public void stopClicked() throws Exception {

		// stop mine layers
		for (MineLayer ml : mineLayers) {
			ml.setRunning(false);
		}

		// stop mine sweepers
		for (MineSweeper ms : mineSweepers) {
			ms.setRunning(false);
		}

		// shutdown executors
		layersExSvc.shutdown();
		sweepersExSvc.shutdown();

		// wait for threads to die
		Thread.sleep(1000);

		// force shutdown executors
		layersExSvc.shutdownNow();
		sweepersExSvc.shutdownNow();

		layersExSvc.awaitTermination(1, TimeUnit.MINUTES);
		sweepersExSvc.awaitTermination(1, TimeUnit.MINUTES);

		// reset board
		board.clearBaord();
	}
}
