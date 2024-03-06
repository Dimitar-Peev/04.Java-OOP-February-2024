package _01_Lab._02_Shapes;

public class Rectangle extends Shape {
    private Double width;
    private Double height;

    public Rectangle(Double width, Double height) {
        this.setWidth(width);
        this.setHeight(height);
        this.calculateArea();
        this.calculatePerimeter();
    }

    private void setWidth(Double width) {
        this.width = width;
    }

    private void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return this.width;
    }

    public Double getHeight() {
        return this.height;
    }

    @Override
    protected double calculatePerimeter() {
        if (getPerimeter() == null) {
            setPerimeter(this.width * 2 + this.height * 2);
        }
        return getPerimeter();
    }

    @Override
    public double calculateArea() {
        if (getArea() == null) {
            setArea(this.width * this.height);
        }
        return getArea();
    }
}