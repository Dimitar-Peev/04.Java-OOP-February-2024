package _01_Lab;

import java.util.Arrays;
import java.util.Scanner;

public class _01_NumberInRange {
    private static int start;
    private static int end;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] range = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        start = range[0];
        end = range[1];

        System.out.println(String.format("Range: [%d...%d]", start, end));

        while (true) {
            String input = scanner.nextLine();
            try {
                int num = Integer.parseInt(input);
                if (isInRange(num)) {
                    System.out.println("Valid number: " + num);
                    return;
                } else {
                    System.out.println("Invalid number: " + input);
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number: " + input);
            }
        }
    }

    private static boolean isInRange(int num) {
        return start <= num && num <= end;
    }
}
