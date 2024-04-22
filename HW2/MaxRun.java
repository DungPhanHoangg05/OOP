import java.util.Scanner;

public class MaxRun {
    public static int maxRun (String str) {
        int max = 1, current = 1;
        for(int i = 1; i < str.length(); ++i) {
            if(str.charAt(i) == str.charAt(i-1)) current++;
            else {
                max = Math.max(max, current);
                current = 1;
            }
        }
        max = Math.max(max, current);
        return max;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        int result = maxRun(s);
        System.out.println(result);
    }
}
