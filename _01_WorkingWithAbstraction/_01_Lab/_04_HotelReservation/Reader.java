package _01_WorkingWithAbstraction._01_Lab._04_HotelReservation;

import java.util.Arrays;
import java.util.Scanner;

public class Reader {
    public static Scanner scanner = new Scanner(System.in);

    public static int [] readIntArray(String delimiter) {
        return Arrays.stream(scanner.nextLine().split(delimiter))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static String [] readStringArray(String delimiter) {
        return scanner.nextLine().split(delimiter);
    }
}
