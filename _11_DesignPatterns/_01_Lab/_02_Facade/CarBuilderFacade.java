package _01_Lab._02_Facade;

public class CarBuilderFacade {
    protected Car car;

    public CarBuilderFacade(){
        this.car = new Car();
    }

    public Car build(){
        return this.car;
    }

    public CarInfoBuilder info(){
        return new CarInfoBuilder(this.car);
    }

    public CarAddressBuilder built(){
        return new CarAddressBuilder(car);
    }
}
