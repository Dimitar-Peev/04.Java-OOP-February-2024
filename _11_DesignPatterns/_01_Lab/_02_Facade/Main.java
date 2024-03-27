package _01_Lab._02_Facade;

public class Main {
    public static void main(String[] args) {

        Car car = new CarBuilderFacade()
                .info()
                .withType("BMW")
                .withColor("Black")
                .withNumberOfDoors(5)
                .built()
                .inCity("Leipzig")
                .atAddress("Some address 254")
                .build();
        System.out.println(car);
    }
}
