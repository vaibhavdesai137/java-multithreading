package com.vaidesai.conway.app;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class App {

	public static void main(String[] args) throws Exception {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		// start our app in a new thread
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});

	}
}
