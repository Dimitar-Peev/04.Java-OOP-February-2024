package hell;

import hell.entities.miscellaneous.ManagerImpl;
import hell.interfaces.Manager;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Manager manager = new ManagerImpl();

        Scanner scanner = new Scanner(System.in);

        String commandLine = scanner.nextLine();

        while (!"Quit".equals(commandLine)) {
            String[] commandArgs = commandLine.split("\\s+");
            String command = commandArgs[0];

            String result = "";
            switch (command) {
                case "Hero":
                    result = manager.addHero(Arrays.asList(commandArgs));
                    break;
                case "Item":
                    result = manager.addItem(Arrays.asList(commandArgs));
                    break;
                case "Recipe":
                    result = manager.addRecipe(Arrays.asList(commandArgs));
                    break;
                case "Inspect":
                    result = manager.inspect(Arrays.asList(commandArgs));
                    break;
            }

            System.out.println(result);
            commandLine = scanner.nextLine();
        }

        System.out.println(manager.quit());
    }
}