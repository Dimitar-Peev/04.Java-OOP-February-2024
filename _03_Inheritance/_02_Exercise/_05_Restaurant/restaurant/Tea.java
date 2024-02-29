package _03_Inheritance._02_Exercise._05_Restaurant.restaurant;

import java.math.BigDecimal;

public class Tea extends HotBeverage {
    public Tea(String name, BigDecimal price, double milliliters) {
        super(name, price, milliliters);
    }
}