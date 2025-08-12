package unitTesting;

public class UnitMotorcycle {
    private String model;
    private int horsePower;
    private double cubicCentimeters;


    public UnitMotorcycle(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsePower(horsePower);
        this.setCubicCentimeters(cubicCentimeters);
    }

    public String getModel() {
        return this.model;
    }

    public int getHorsePower() {
        return this.horsePower;
    }

    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    private void setModel(String model) {
        this.model = model;
    }

    private void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    private void setCubicCentimeters(double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }
}
