package _01_Lab._06_Ferrari;

public class Ferrari implements Car { 
    private final String driverName;
    private final String model;

    public Ferrari(String driverName) {
        this.driverName = driverName;
        this.model = "488-Spider";
    }

    @Override
    public String brakes() {
        return "Brakes!";
    }

    @Override
    public String gas() {
        return "brum-brum-brum-brrrrr";
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s/%s", this.model, this.brakes(), this.gas(), this.driverName);
    }
}