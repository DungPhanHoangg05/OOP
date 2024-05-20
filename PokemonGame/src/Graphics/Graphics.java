package Graphics;

import GameLogic.Algorithm;
import GameLogic.Line;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graphics extends JPanel implements ActionListener {
	private int row;
	private int col;
	private int bound = 3;
	private int size = 50;
	private int score = 0;
	private JButton[][] btn;
	private Point p1 = null;
	private Point p2 = null;
	private Algorithm algorithm;
	private Line line;
	private Frame frame;
	private Color backGroundColor = Color.gray;
	private int item;

	public Graphics(Frame frame, int row, int col) {
		this.frame = frame;
		this.row = row + 2;
		this.col = col + 2;
		item = row * col / 2;

		setLayout(new GridLayout(row, col, bound, bound));
		setBackground(backGroundColor);
		setPreferredSize(new Dimension((size + bound) * col, (size + bound) * row));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setAlignmentY(JPanel.CENTER_ALIGNMENT);

		newGame();
	}

	public void newGame() {
		algorithm = new Algorithm(this.row, this.col);
		addArrayButton();
	}

	private void addArrayButton() {
		btn = new JButton[row][col];
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < col - 1; j++) {
				btn[i][j] = createButton(i + "," + j);
				Icon icon = getIcon(algorithm.getMatrix()[i][j]);
				btn[i][j].setIcon(icon);
				add(btn[i][j]);
			}
		}
	}

	private Icon getIcon(int index) {
		int width = 50, height = 50;
		Image image = new ImageIcon(getClass().getResource("/image/" + index + ".png")).getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		return icon;
	}

	private JButton createButton(String action) {
		JButton btn = new JButton();
		btn.setActionCommand(action);
		btn.setBorder(null);
		btn.addActionListener(this);
		return btn;
	}

	public void execute(Point p1, Point p2) {
		setDisable(btn[p1.x][p1.y]);
		setDisable(btn[p2.x][p2.y]);
	}

	private void setDisable(JButton btn) {
		btn.setIcon(null);
		btn.setBackground(backGroundColor);
		btn.setEnabled(false);
	}

	public void shuffle() {
		List<Integer> icons = new ArrayList<>();
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < col - 1; j++) {
				if (algorithm.getMatrix()[i][j] != 0) {
					icons.add(algorithm.getMatrix()[i][j]);
				}
			}
		}
		Collections.shuffle(icons);
		int index = 0;
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < col - 1; j++) {
				if (algorithm.getMatrix()[i][j] != 0) {
					algorithm.getMatrix()[i][j] = icons.get(index++);
					btn[i][j].setIcon(getIcon(algorithm.getMatrix()[i][j]));
				}
			}
		}
	}

	public void showHint() {
		Point[] hint = algorithm.findHint();
		if (hint != null) {
			btn[hint[0].x][hint[0].y].setBorder(new LineBorder(Color.red, 3));
			btn[hint[1].x][hint[1].y].setBorder(new LineBorder(Color.red, 3));
			Timer timer = new Timer(1000, e -> {
				btn[hint[0].x][hint[0].y].setBorder(null);
				btn[hint[1].x][hint[1].y].setBorder(null);
			});
			timer.setRepeats(false);
			timer.start();
		}
	}

	public boolean checkGameOver() {
		if (item>0) return false;
		else return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnIndex = e.getActionCommand();
		int indexDot = btnIndex.lastIndexOf(",");
		int x = Integer.parseInt(btnIndex.substring(0, indexDot));
		int y = Integer.parseInt(btnIndex.substring(indexDot + 1));

		PlayAudio.play("src/audio/selectsound.wav", false);

		if (p1 == null) {
			p1 = new Point(x, y);
			btn[p1.x][p1.y].setBorder(new LineBorder(Color.red, 3));
		} else {
			p2 = new Point(x, y);
			line = algorithm.checkTwoPoint(p1, p2);
			if (line != null) {
				algorithm.getMatrix()[p1.x][p1.y] = 0;
				algorithm.getMatrix()[p2.x][p2.y] = 0;
				execute(p1, p2);
				line = null;
				score += 10;
				item--;
				frame.time++;
				frame.getScore().setText(score + "");
			}
			btn[p1.x][p1.y].setBorder(null);
			p1 = null;
			p2 = null;
			if (item == 0) {
				PlayAudio.play("src/audio/winsound.wav", false);
				frame.showDialogNewGame("You win!\nDo you want to play again?");
			}
		}
	}

	public Point[] findHint() {
		return algorithm.findHint();
	}
}
