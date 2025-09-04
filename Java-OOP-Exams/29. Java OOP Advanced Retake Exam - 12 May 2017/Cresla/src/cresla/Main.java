package cresla;

import cresla.core.ManagerImpl;
import cresla.interfaces.Manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        Manager manager = new ManagerImpl();

        String input = scanner.nextLine();

        while (!"Exit".equals(input)) {
            String[] tokens = input.split("\\s+");

            String command = tokens[0];

            List<String> params = Arrays.stream(tokens).skip(1).collect(Collectors.toList());

            String output = "";

            switch (command) {
                case "Reactor":
                    output = manager.reactorCommand(params);
                    break;
                case "Module":
                    output = manager.moduleCommand(params);
                    break;
                case "Report":
                    output = manager.reportCommand(params);
                    break;
            }

            System.out.println(output);

            input = scanner.nextLine();
        }

        String output = manager.exitCommand(new ArrayList<String>());
        System.out.println(output);
    }
}