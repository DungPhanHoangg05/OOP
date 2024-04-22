public class CharArea {
    public static int charArea(char ch, char[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        int minRow = numRows;
        int maxRow = -1;
        int minCol = numCols;
        int maxCol = -1;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] == ch) {
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }

        int width = maxCol - minCol + 1;
        int height = maxRow - minRow + 1;

        if(maxCol==-1) return 0;
        else return width * height;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'a', 'b', 'c', 'd'},
                {'a', ' ', 'c', 'b'},
                {'x', 'b', 'c', 'a'}
        };
        char ch = 'a';

        int area = charArea(ch, grid);
        System.out.println(area);
    }
}
