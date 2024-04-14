public class Stats5 {
    public static void main(String[] args) {
        double[] randomNumbers = new double[5];

        for (int i = 0; i < randomNumbers.length; ++i) {
            randomNumbers[i] = Math.random();
        }

        double sum = 0;
        for (double number : randomNumbers) {
            sum += number;
        }

        double min = randomNumbers[0];
        double max = randomNumbers[0];
        for (double number : randomNumbers) {
            min = Math.min(min, number);
            max = Math.max(max, number);
        }

        double average = sum / randomNumbers.length;

        for (double number : randomNumbers) {
            System.out.print(number + " ");
        }
        System.out.println();
        System.out.println(average + "\n" + min + "\n" + max);
    }
}
