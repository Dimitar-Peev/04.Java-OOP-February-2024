package christmasPastryShop.entities.booths;

import christmasPastryShop.entities.booths.BaseBooth;

public class PrivateBooth extends BaseBooth {

    private static final double pricePerPerson = 3.50;

    public PrivateBooth(int boothNumber, int capacity) {
        super(boothNumber, capacity, pricePerPerson);
    }
}