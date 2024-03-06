package _01_Lab._02_Shapes;

public abstract class Shape {
    private Double perimeter;
    private Double area;

    protected abstract double calculatePerimeter();

    protected abstract double calculateArea();

    protected void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public Double getPerimeter() {
        return this.perimeter;
    }

    protected void setArea(Double area) {
        this.area = area;
    }

    public Double getArea() {
        return this.area;
    }
}
