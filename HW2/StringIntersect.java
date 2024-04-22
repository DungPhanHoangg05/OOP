import java.util.HashSet;
import java.util.Scanner;

public class StringIntersect {
    public static boolean stringIntersect(String a, String b, int len) {
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i <= a.length() - len; i++) {
            set.add(a.substring(i, i + len));
        }

        for (int i = 0; i <= b.length() - len; i++) {
            if (set.contains(b.substring(i, i + len))) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String a = scan.nextLine();
        String b = scan.nextLine();
        int len = scan.nextInt();
        Boolean result = stringIntersect(a, b, len);
        System.out.println(result);
    }
}
