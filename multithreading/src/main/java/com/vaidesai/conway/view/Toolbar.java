package com.vaidesai.conway.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton startButton;
	private JButton restartButton;
	private ButtonListener buttonListener;

	public Toolbar() {

		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(BorderFactory.createEtchedBorder());

		startButton = new JButton("Start");
		restartButton = new JButton("Restart");

		startButton.addActionListener(this);
		restartButton.addActionListener(this);

		add(startButton);
		add(restartButton);
	}

	public void setButtonListener(ButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}

	public void actionPerformed(ActionEvent e) {
		if ((JButton) e.getSource() == startButton && buttonListener != null) {
			buttonListener.startClicked();
		} else if ((JButton) e.getSource() == restartButton
				&& buttonListener != null) {
			buttonListener.restartClicked();
		}
	}

	public void setStartButton(boolean bool) {
		startButton.setEnabled(bool);
	}

	public void setRestartButton(boolean bool) {
		restartButton.setEnabled(bool);
	}
}
