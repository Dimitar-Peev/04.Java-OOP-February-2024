package _01_WorkingWithAbstraction._02_Exercise._04_TrafficLights;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] initialTrafficLightsStates = scanner.nextLine().split("\\s+");
        int numberOfUpdates = Integer.parseInt(scanner.nextLine());

        List<TrafficLight> trafficLightsList = new ArrayList<>();

        for (String string : initialTrafficLightsStates) {
            TrafficLight trafficLight = new TrafficLight(TrafficLightState.valueOf(string));
            trafficLightsList.add(trafficLight);
        }

        for (int i = 0; i < numberOfUpdates; i++) {
            for (TrafficLight trafficLight : trafficLightsList) {
                trafficLight.update();
                System.out.print(trafficLight + " ");
            }
            System.out.println();
        }
    }
}
