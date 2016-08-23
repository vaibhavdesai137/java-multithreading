package com.vaidesai.minesweeper.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton startButton;
	private JButton stopButton;
	private ButtonListener buttonListener;

	public Toolbar() {

		setLayout(new FlowLayout(FlowLayout.CENTER));

		startButton = new JButton("Start");
		startButton.addActionListener(this);

		stopButton = new JButton("Stop");
		stopButton.addActionListener(this);

		// add to toolbar
		add(startButton);
		add(stopButton);
	}

	public void setButtonListener(ButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}

	public void actionPerformed(ActionEvent e) {

		try {
			if ((JButton) e.getSource() == startButton) {
				buttonListener.startClicked();
			} else if ((JButton) e.getSource() == stopButton) {
				buttonListener.stopClicked();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
