package _01_Lab;

public class _04_Fixing {
    public static void main(String[] args) {

        String[] weekDays = new String[]{"Mon", "Tue", "Wed", "Thur", "Fri"};

        try {
            for (int i = 0; i <= weekDays.length; i++) {
                System.out.println(weekDays[i]);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
