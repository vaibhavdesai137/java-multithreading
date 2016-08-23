package com.vaidesai.conway.view;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GenerationsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel genLabel;
	public int count = 0;

	public GenerationsPanel() {

		setLayout(new FlowLayout(FlowLayout.CENTER));

		genLabel = new JLabel("Generations will be shown here...");

		add(genLabel);
	}

	public void refreshCounter() {
		count = 0;
		refresh();
	}

	public void refresh() {
		genLabel.setText("Generations: " + count++);
	}
}
