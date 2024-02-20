package _01_WorkingWithAbstraction._01_Lab;

import java.util.Scanner;
import java.util.function.Predicate;

public class _01_RhombusOfStars {
    public static void main(String[] args) {
        int size = readInput();
        String rhombusOfStars = buildRhombsOfStarts(size);
        printOutput(rhombusOfStars);
    }


    private static int readInput() {
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }


    private static String buildRhombsOfStarts(int size) {
        return printMultipleRows(1, size, +1, size) +
                printMultipleRows(size - 1, 1, -1, size);
    }

    private static String printLine(int spaces, int stars) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < spaces; i++) {
            out.append(" ");
        }

        for (int i = 0; i < stars; i++) {
            out.append("* ");
        }

        return out.toString();
    }

    private static String printMultipleRows(int start, int end, int step, int size) {
        StringBuilder out = new StringBuilder();

        Predicate<Integer> loopCondition = iter -> {
            if (step > 0) {
                return iter <= end;
            }
            return iter >= end;
        };

        for (int r = start; loopCondition.test(r); r+= step) {
            out.append(printLine(size - r, r)).append(System.lineSeparator());
        }

        return out.toString();
    }

    private static void printOutput(String rombOfStars) {
        System.out.println(rombOfStars);
    }
}
