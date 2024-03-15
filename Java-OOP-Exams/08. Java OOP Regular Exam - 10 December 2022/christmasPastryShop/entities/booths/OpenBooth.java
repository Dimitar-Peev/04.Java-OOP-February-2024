package christmasPastryShop.entities.booths;

import christmasPastryShop.entities.booths.BaseBooth;

public class OpenBooth extends BaseBooth {

    private static final double pricePerPerson = 2.50;

    public OpenBooth(int boothNumber, int capacity) {
        super(boothNumber, capacity, pricePerPerson);
    }
}