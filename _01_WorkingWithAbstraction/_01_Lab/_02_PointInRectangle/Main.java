package _01_WorkingWithAbstraction._01_Lab._02_PointInRectangle;

import _01_WorkingWithAbstraction._01_Lab._02_PointInRectangle.Point;
import _01_WorkingWithAbstraction._01_Lab._02_PointInRectangle.Rectangle;
import input.Reader;

public class Main {
    public static void main(String[] args) {
        int[] rectaglePoints = Reader.readArray("\\s+");
        Point A = new Point(rectaglePoints[0], rectaglePoints[1]);
        Point B = new Point(rectaglePoints[2], rectaglePoints[3]);

        Rectangle rectangle = new Rectangle(A, B);

        int count = Reader.readArray("\\s+")[0];

        while (count-- > 0) {
            int[] singlePoint = Reader.readArray("\\s+");
            Point p = new Point(singlePoint[0], singlePoint[1]);

            boolean isWithin = rectangle.contains(p);

            System.out.println(isWithin);
        }
    }
}
