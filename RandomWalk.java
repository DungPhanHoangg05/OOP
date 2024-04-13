public class RandomWalk {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdDraw.setScale(-n - 0.5, n + 0.5);
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.enableDoubleBuffering();

        int x = 0, y = 0;
        int steps = 0;
        int direction = 0; // Hướng ban đầu
        int sideLength = 1; // Độ dài mỗi bước trong mỗi hướng
        int stepsInDirection = 0; // Số bước đã đi trong mỗi hướng
        while (Math.abs(x) < n && Math.abs(y) < n) {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledSquare(x, y, 0.45);
            switch (direction) {
                case 0: // Di chuyển sang phải
                    x++;
                    break;
                case 1: // Di chuyển lên trên
                    y++;
                    break;
                case 2: // Di chuyển sang trái
                    x--;
                    break;
                case 3: // Di chuyển xuống dưới
                    y--;
                    break;
            }
            steps++;
            stepsInDirection++;
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledSquare(x, y, 0.45);
            StdDraw.show();
            StdDraw.pause(40);

            // Nếu đã đi đủ số bước trong một hướng thì thay đổi hướng và cập nhật số bước
            if (stepsInDirection == sideLength) {
                direction = (direction + 1) % 4;
                stepsInDirection = 0;
                // Nếu đã đi 2 bước ở mỗi hướng, tăng độ dài bước
                if (direction % 2 == 0) {
                    sideLength++;
                }
            }
        }
        StdOut.println("Total steps = " + steps);
    }

}
