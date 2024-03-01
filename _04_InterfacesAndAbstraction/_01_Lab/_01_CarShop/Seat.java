package _04_InterfacesAndAbstraction._01_Lab._01_CarShop;

import java.io.Serializable;

public class Seat implements Car, Serializable {
	private static final long SERIAL_VERSION_UID = 1L;
    private String model;
    private String color;
    private Integer horsePower;
    private String countryProduced;

    public Seat(String model, String color, Integer horsePower, String countryProduced) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.countryProduced = countryProduced;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Integer getHorsePower() {
        return this.horsePower;
    }

    @Override
    public String countryProduced() {
        return this.countryProduced;
    }


    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires",
                this.getModel(), this.countryProduced(), this.TIRES);
    }
}