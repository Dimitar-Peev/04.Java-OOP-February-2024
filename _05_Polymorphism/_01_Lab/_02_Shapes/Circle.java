package _01_Lab._02_Shapes;

public
class Circle extends Shape {
    private Double radius;

    public Circle(Double radius) {
        setRadius(radius);
        this.calculateArea();
        this.calculatePerimeter();
    }

    private void setRadius(Double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return this.radius;
    }

    @Override
    protected double calculatePerimeter() {
        if (getPerimeter() == null) {
            setPerimeter(2 * Math.PI * this.radius);
        }
        return getPerimeter();
    }

    @Override
    protected double calculateArea() {
        if (getArea() == null) {
            setArea(Math.PI * Math.pow(this.radius, 2));
        }
        return getArea();
    }
}