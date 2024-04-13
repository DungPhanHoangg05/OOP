public class Spiral {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int [][] arr = new int[n][n];

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                arr[i][j] = 1 + n*i + j;
            }
        }

        for(int i=0; i<n; ++i) {
            for(int j=0; j<n; ++j) {
                System.out.printf("%2d", arr[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();

        int top = 0, bottom = n - 1, left = 0, right = n - 1;

        while(top <= bottom && left <= right) {
            for(int i = left; i <= right; ++i) {
                System.out.print(arr[top][i] + " ");
            }
            top++;

            for(int i = top; i <= bottom; ++i) {
                System.out.print(arr[i][right] + " ");
            }
            right--;

            for(int i = right; i >= left; --i) {
                System.out.print(arr[bottom][i] + " ");
            }
            bottom--;

            for(int i = bottom; i >= top; --i) {
                System.out.print(arr[i][left] + " ");
            }
            left++;
        }
    }
}
