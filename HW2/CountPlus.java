public class CountPlus {

    // Phương thức để kiểm tra xem có thể tạo thành một hình chữ thập từ một ô cụ thể
    private static boolean isPlus(char[][] grid, int row, int col, int size) {
        char centerChar = grid[row][col];

        // Kiểm tra hướng lên
        for (int i = row - 1; i >= row - size; i--) {
            if (i < 0 || grid[i][col] != centerChar) {
                return false;
            }
        }

        // Kiểm tra hướng xuống
        for (int i = row + 1; i <= row + size; i++) {
            if (i >= grid.length || grid[i][col] != centerChar) {
                return false;
            }
        }

        // Kiểm tra hướng trái
        for (int j = col - 1; j >= col - size; j--) {
            if (j < 0 || grid[row][j] != centerChar) {
                return false;
            }
        }

        // Kiểm tra hướng phải
        for (int j = col + 1; j <= col + size; j++) {
            if (j >= grid[0].length || grid[row][j] != centerChar) {
                return false;
            }
        }

        if(centerChar == ' ') return false;
        // Nếu các điều kiện đều được thỏa mãn, trả về true
        return true;
    }

    // Phương thức để đếm số hình chữ thập trong bảng
    public static int countPlus(char[][] grid) {
        int count = 0;
        int numRows = grid.length;
        int numCols = grid[0].length;

        // Duyệt qua từng ô trong bảng
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // Kiểm tra xem ô hiện tại có thể tạo thành một hình chữ thập không
                for (int size = 1; size <= Math.min(i, Math.min(j, Math.min(numRows - i - 1, numCols - j - 1))); size++) {
                    if (isPlus(grid, i, j, size)) {
                        count++;
                    } else {
                        break; // Đã không thể tạo hình chữ thập với kích thước lớn hơn
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ', 'x', ' '},
                {'p', 'p', 'p', 'p', 'p', ' ', ' ', 'x', 'x', 'x'},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ', 'x', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'y', 'y', 'y', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'y', ' ', ' ', ' ', ' ', ' '},
                {'z', 'z', ' ', 'z', 'z', 'y', 'z', 'z', 'z', 'z'},
                {' ', ' ', 'x', 'x', ' ', 'y', ' ', ' ', ' ', ' '}
        };
        int result = countPlus(grid);
        System.out.println(result);
    }
}
