package GameLogic;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Algorithm {
	private int row;
	private int col;
	private int notBarrier = 0;
	private int[][] matrix;

	public Algorithm(int row, int col) {
		this.row = row;
		this.col = col;
		createMatrix();
	}

	private boolean checkLineX(int y1, int y2, int x) {
		int min = Math.min(y1, y2);
		int max = Math.max(y1, y2);

		for (int y = min + 1; y < max; y++) {
			if (matrix[x][y] > notBarrier) {
				return false;
			}
		}

		return true;
	}

	private boolean checkLineY(int x1, int x2, int y) {
		int min = Math.min(x1, x2);
		int max = Math.max(x1, x2);
		for (int x = min + 1; x < max; x++) {
			if (matrix[x][y] > notBarrier) {
				return false;
			}
		}
		return true;
	}

	private int checkRectX(Point p1, Point p2) {
		Point pMinY = p1, pMaxY = p2;
		if (p1.y > p2.y) {
			pMinY = p2;
			pMaxY = p1;
		}
		for (int y = pMinY.y; y <= pMaxY.y; y++) {
			if (y > pMinY.y && matrix[pMinY.x][y] > notBarrier) {
				return -1;
			}
			if ((matrix[pMaxY.x][y] == notBarrier || y == pMaxY.y)
					&& checkLineY(pMinY.x, pMaxY.x, y)
					&& checkLineX(y, pMaxY.y, pMaxY.x)) {

				return y;
			}
		}
		return -1;
	}

	private int checkRectY(Point p1, Point p2) {
		Point pMinX = p1, pMaxX = p2;
		if (p1.x > p2.x) {
			pMinX = p2;
			pMaxX = p1;
		}
		for (int x = pMinX.x; x <= pMaxX.x; x++) {
			if (x > pMinX.x && matrix[x][pMinX.y] > notBarrier) {
				return -1;
			}
			if ((matrix[x][pMaxX.y] == notBarrier || x == pMaxX.x)
					&& checkLineX(pMinX.y, pMaxX.y, x)
					&& checkLineY(x, pMaxX.x, pMaxX.y)) {
				return x;
			}
		}
		return -1;
	}

	private int checkMoreLineX(Point p1, Point p2, int type) {
		Point pMinY = p1, pMaxY = p2;
		if (p1.y > p2.y) {
			pMinY = p2;
			pMaxY = p1;
		}

		int y = pMaxY.y + type;
		int row = pMinY.x;
		int colFinish = pMaxY.y;
		if (type == -1) {
			colFinish = pMinY.y;
			y = pMinY.y + type;
			row = pMaxY.x;
		}

		if ((matrix[row][colFinish] == notBarrier || pMinY.y == pMaxY.y)
				&& checkLineX(pMinY.y, pMaxY.y, row)) {
			while (matrix[pMinY.x][y] == notBarrier
					&& matrix[pMaxY.x][y] == notBarrier) {
				if (checkLineY(pMinY.x, pMaxY.x, y)) {
					return y;
				}
				y += type;
			}
		}
		return -1;
	}

	private int checkMoreLineY(Point p1, Point p2, int type) {
		Point pMinX = p1, pMaxX = p2;
		if (p1.x > p2.x) {
			pMinX = p2;
			pMaxX = p1;
		}
		int x = pMaxX.x + type;
		int col = pMinX.y;
		int rowFinish = pMaxX.x;
		if (type == -1) {
			rowFinish = pMinX.x;
			x = pMinX.x + type;
			col = pMaxX.y;
		}
		if ((matrix[rowFinish][col] == notBarrier || pMinX.x == pMaxX.x)
				&& checkLineY(pMinX.x, pMaxX.x, col)) {
			while (matrix[x][pMinX.y] == notBarrier
					&& matrix[x][pMaxX.y] == notBarrier) {
				if (checkLineX(pMinX.y, pMaxX.y, x)) {
					return x;
				}
				x += type;
			}
		}
		return -1;
	}

	public Line checkTwoPoint(Point p1, Point p2) {
		if (!p1.equals(p2) && matrix[p1.x][p1.y] == matrix[p2.x][p2.y]) {
			if (p1.x == p2.x) {
				if (checkLineX(p1.y, p2.y, p1.x)) {
					return new Line(p1, p2);
				}
			}

			if (p1.y == p2.y) {
				if (checkLineY(p1.x, p2.x, p1.y)) {
					return new Line(p1, p2);
				}
			}

			int t = -1;

			if ((t = checkRectX(p1, p2)) != -1) {
				return new Line(new Point(p1.x, t), new Point(p2.x, t));
			}

			if ((t = checkRectY(p1, p2)) != -1) {
				return new Line(new Point(t, p1.y), new Point(t, p2.y));
			}

			if ((t = checkMoreLineX(p1, p2, 1)) != -1) {
				return new Line(new Point(p1.x, t), new Point(p2.x, t));
			}

			if ((t = checkMoreLineX(p1, p2, -1)) != -1) {
				return new Line(new Point(p1.x, t), new Point(p2.x, t));
			}

			if ((t = checkMoreLineY(p1, p2, 1)) != -1) {
				return new Line(new Point(t, p1.y), new Point(t, p2.y));
			}

			if ((t = checkMoreLineY(p1, p2, -1)) != -1) {
				return new Line(new Point(t, p1.y), new Point(t, p2.y));
			}
		}
		return null;
	}

	private void createMatrix() {

		matrix = new int[row][col];
		for (int i = 0; i < col; i++) {
			matrix[0][i] = matrix[row - 1][i] = 0;
		}
		for (int i = 0; i < row; i++) {
			matrix[i][0] = matrix[i][col - 1] = 0;
		}

		Random rand = new Random();
		int imgNumber = 28;
		int maxDouble = imgNumber / 4;
		int imgArr[] = new int[imgNumber + 1];
		ArrayList<Point> listPoint = new ArrayList<Point>();
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < col - 1; j++) {
				listPoint.add(new Point(i, j));
			}
		}
		int i = 0;
		do {
			int imgIndex = rand.nextInt(imgNumber) + 1;
			if (imgArr[imgIndex] < maxDouble) {
				imgArr[imgIndex] += 2;
				for (int j = 0; j < 2; j++) {
					try {
						int size = listPoint.size();
						int pointIndex = rand.nextInt(size);
						matrix[listPoint.get(pointIndex).x][listPoint.get(pointIndex).y] = imgIndex;
						listPoint.remove(pointIndex);
					} catch (Exception e) {

					}
				}
				i++;
			}
		} while (i < row * col / 2);
	}

	public Point[] findHint() {
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < col - 1; j++) {
				if (matrix[i][j] != 0) {
					for (int x = 1; x < row - 1; x++) {
						for (int y = 1; y < col - 1; y++) {
							if ((i != x || j != y) && matrix[x][y] != 0 && matrix[i][j] == matrix[x][y]) {
								if (checkTwoPoint(new Point(i, j), new Point(x, y)) != null) {
									return new Point[]{new Point(i, j), new Point(x, y)};
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
}
