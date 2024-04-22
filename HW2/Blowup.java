import java.util.Scanner;

public class Blowup {
    public static String blowup(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if (Character.isDigit(current)) {
                if (i + 1 < str.length()) {
                    int repeat = current - '0';
                    char nextChar = str.charAt(i + 1);
                    for (int j = 0; j < repeat; j++) {
                        result.append(nextChar);
                    }
                }
            }
            else {
                result.append(current);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        String output = blowup(input);
        System.out.println(output);
    }
}
