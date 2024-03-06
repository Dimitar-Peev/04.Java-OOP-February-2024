package _01_Lab._02_Shapes;

public class Main {
    public static void main(String[] args) {

        Shape rectangle = new Rectangle(2.0, 4.0);
        Shape circle = new Circle(3.0);

        System.out.println(rectangle.getArea() + "%" + rectangle.getPerimeter());
        System.out.println(circle.getArea() + "@" + circle.getPerimeter());
    }
}
