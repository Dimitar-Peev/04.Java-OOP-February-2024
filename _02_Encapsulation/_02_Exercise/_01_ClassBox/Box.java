package _01_ClassBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setLength(double length) {
        if (isPositive(length)) {
            this.length = length;
        }
    }

    private void setWidth(double width) {
        if (isPositive(width)) {
            this.width = width;
        }
    }

    private void setHeight(double height) {
        if (isPositive(height)) {
            this.height = height;
        }
    }

    private boolean isPositive(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be bigger than zero");
        }
        return true;
    }

    public double calculateSurfaceArea() {
        return (2 * this.length * this.width) + (2 * this.length * this.height) + (2 * this.width * this.height);
    }

    public double calculateLateralSurfaceArea() {
        return (2 * this.length * this.height) + (2 * this.width * this.height);
    }

    public double calculateVolume() {
        return this.length * this.width * this.height;
    }
}
