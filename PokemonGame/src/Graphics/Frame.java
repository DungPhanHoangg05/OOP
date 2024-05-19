package Graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener, Runnable {
	private static final int MAX_SHUFFLES = 3;
	private static final int MAX_HINTS = 3;
	private int maxTime = 300;
	public int time = maxTime;
	private int row = 12;
	private int col = 12;
	private int width = 900;
	private int height = 700;
	private JLabel scoreLabel;
	private JProgressBar progressTime;
	private JButton btnNewGame;
	private JButton btnShuffle;
	private JButton btnHint;
	private JLabel shuffleCountLabel;
	private JLabel hintCountLabel;
	private Graphics graphicsPanel;
	private JPanel mainPanel;
	private int shuffleCount = 0;
	private int hintCount = 0;

	public Frame() {
		add(mainPanel = createMainPanel());
		setTitle("Pokemon Game");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(GraphicsPanel(), BorderLayout.CENTER);
		panel.add(ControlPanel(), BorderLayout.WEST);
		return panel;
	}

	private JPanel GraphicsPanel() {
		graphicsPanel = new Graphics(this, row, col);
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.gray);
		panel.add(graphicsPanel);
		return panel;
	}

	private JPanel ControlPanel() {
		scoreLabel = new JLabel("0");
		progressTime = new JProgressBar(0, 100);
		progressTime.setValue(100);

		JPanel panelLeft = new JPanel(new GridLayout(0, 1));
		panelLeft.add(new JLabel("Score:"));
		panelLeft.add(new JLabel("Time:"));

		JPanel panelRight = new JPanel(new GridLayout(0, 1));
		panelRight.add(scoreLabel);
		panelRight.add(progressTime);

		JPanel panelScoreAndTime = new JPanel(new BorderLayout(10, 10));
		panelScoreAndTime.add(panelLeft, BorderLayout.WEST);
		panelScoreAndTime.add(panelRight, BorderLayout.CENTER);

		shuffleCountLabel = new JLabel("Shuffles left: " + (MAX_SHUFFLES - shuffleCount));
		hintCountLabel = new JLabel("Hints left: " + (MAX_HINTS - hintCount));

		JPanel panelControl = new JPanel(new GridLayout(0, 1, 10, 10));
		panelControl.setBorder(new EmptyBorder(10, 5, 10, 5));
		panelControl.add(panelScoreAndTime);
		panelControl.add(btnNewGame = createButton("New Game"));
		panelControl.add(btnShuffle = createButton("Shuffle"));
		panelControl.add(shuffleCountLabel);
		panelControl.add(btnHint = createButton("Hint"));
		panelControl.add(hintCountLabel);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(panelControl, BorderLayout.NORTH);
		return panel;
	}

	public JButton createButton(String buttonName) {
		JButton btn = new JButton(buttonName);
		btn.addActionListener(this);
		return btn;
	}

	public void newGame() {
		time = maxTime;
		shuffleCount = 0;
		hintCount = 0;
		btnShuffle.setEnabled(true);
		btnHint.setEnabled(true);
		shuffleCountLabel.setText("Shuffles left: " + (MAX_SHUFFLES - shuffleCount));
		hintCountLabel.setText("Hints left: " + (MAX_HINTS - hintCount));
		graphicsPanel.removeAll();
		mainPanel.add(GraphicsPanel(), BorderLayout.CENTER);
		mainPanel.validate();
		mainPanel.setVisible(true);
		scoreLabel.setText("0");
	}

	public void showDialogNewGame(String message) {
		int select = JOptionPane.showOptionDialog(null, message, null,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (select == 0) {
			newGame();
		} else {
			System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewGame) {
			newGame();
		} else if (e.getSource() == btnShuffle) {
			if (shuffleCount < MAX_SHUFFLES) {
				graphicsPanel.shuffle();
				shuffleCount++;
				shuffleCountLabel.setText("Shuffles left: " + (MAX_SHUFFLES - shuffleCount));
				if (shuffleCount >= MAX_SHUFFLES) {
					btnShuffle.setEnabled(false);
				}
			}
		} else if (e.getSource() == btnHint) {
			if (hintCount < MAX_HINTS) {
				graphicsPanel.showHint();
				hintCount++;
				hintCountLabel.setText("Hints left: " + (MAX_HINTS - hintCount));
				if (hintCount >= MAX_HINTS) {
					btnHint.setEnabled(false);
				}
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			progressTime.setValue((int) ((double) time / maxTime * 100));
			checkForValidMoves();
		}
	}

	private void checkForValidMoves() {
		if (graphicsPanel.findHint() == null && !graphicsPanel.checkGameOver()) {
			JOptionPane.showMessageDialog(this, "No valid moves left, shuffling the board!");
			graphicsPanel.shuffle();
		}
	}

	public JLabel getScore() {
		return scoreLabel;
	}

	public void setScore(JLabel scoreLabel) {
		this.scoreLabel = scoreLabel;
	}

	public JProgressBar getProgressTime() {
		return progressTime;
	}

	public void setProgressTime(JProgressBar progressTime) {
		this.progressTime = progressTime;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTime() {
		return time;
	}
}
