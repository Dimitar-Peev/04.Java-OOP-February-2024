package _01_Lab._05_BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Identifiable> identifiableList = new ArrayList<>();
        Identifiable identifiable = null;

        String input = scanner.nextLine();
        while (!"End".equals(input)) {
            String[] inputArr = input.split("\\s+");

            if (inputArr.length == 2) {
                String model = inputArr[0];
                String id = inputArr[1];
                identifiable = new Robot(model, id);
            } else {
                String name = inputArr[0];
                int age = Integer.parseInt(inputArr[1]);
                String id = inputArr[2];
                identifiable = new Citizen(name, age, id);
            }

            identifiableList.add(identifiable);

            input = scanner.nextLine();
        }

        String fakeId = scanner.nextLine();
        identifiableList.stream()
                .map(Identifiable::getId)
                .filter(id -> id.endsWith(fakeId))
                .forEach(System.out::println);

//        String postFix = scanner.nextLine ();
//        for (Identifiable id : identifiableList) {
//            if (id.getId ().endsWith (postFix)){
//                System.out.println (id.getId ());
//            }
//        }
    }
}
