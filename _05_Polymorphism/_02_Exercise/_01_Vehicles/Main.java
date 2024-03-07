package _02_Exercise._01_Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] carInfo = scanner.nextLine().split("\\s+");
        String[] truckInfo = scanner.nextLine().split("\\s+");

        Car car = new Car(Double.parseDouble(carInfo[1]), Double.parseDouble(carInfo[2]));
        Truck truck = new Truck(Double.parseDouble(truckInfo[1]), Double.parseDouble(truckInfo[2]));

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] inputArr = scanner.nextLine().split("\\s+");
			
            switch (inputArr[0]) {
                case "Drive":
                    double distance = Double.parseDouble(inputArr[2]);
                    if ("Car".equals(inputArr[1])){
                        System.out.println(car.drive(distance));
                    } else {
                        System.out.println(truck.drive(distance));
                    }
                    break;
					
                case "Refuel":
                    double liters = Double.parseDouble(inputArr[2]);
                    if ("Car".equals(inputArr[1])){
                        car.refuel(liters);
                    } else {
                        truck.refuel(liters);
                    }
                    break;
            }
        }

        System.out.println(car.toString());
        System.out.println(truck.toString());
    }
}
