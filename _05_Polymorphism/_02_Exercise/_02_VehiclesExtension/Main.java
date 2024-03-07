package _02_Exercise._02_VehiclesExtension;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] carInfo = scanner.nextLine().split("\\s+");
        String[] truckInfo = scanner.nextLine().split("\\s+");
        String[] busInfo = scanner.nextLine().split("\\s+");

        Car car = new Car(Double.parseDouble(carInfo[1]), Double.parseDouble(carInfo[2]), Double.parseDouble(carInfo[3]));
        Truck truck = new Truck(Double.parseDouble(truckInfo[1]), Double.parseDouble(truckInfo[2]), Double.parseDouble(truckInfo[3]));
        Bus bus = new Bus(Double.parseDouble(busInfo[1]), Double.parseDouble(busInfo[2]), Double.parseDouble(busInfo[3]));

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] inputArr = scanner.nextLine().split("\\s+");
            try {


                switch (inputArr[0]) {
                    case "Drive":
                        double distance = Double.parseDouble(inputArr[2]);
                        if ("Car".equals(inputArr[1])) {
                            System.out.println(car.drive(distance));
                        } else if ("Truck".equals(inputArr[1])) {
                            System.out.println(truck.drive(distance));
                        } else {
                            System.out.println(bus.drive(distance));
                        }
                        break;
                    case "Refuel":
                        double liters = Double.parseDouble(inputArr[2]);
                        if ("Car".equals(inputArr[1])) {
                            car.refuel(liters);
                        } else if ("Truck".equals(inputArr[1])) {
                            truck.refuel(liters);
                        } else {
                            bus.refuel(liters);
                        }
                        break;
                    case "DriveEmpty":
                        System.out.println(bus.drive(Double.parseDouble(inputArr[2])));
                        break;
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

        }

        System.out.println(car);
        System.out.println(truck);
        System.out.println(bus);
    }
}
