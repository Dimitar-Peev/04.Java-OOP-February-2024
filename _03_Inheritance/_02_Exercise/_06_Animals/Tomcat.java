package _03_Inheritance._02_Exercise._06_Animals;

public class Tomcat extends Cat {
    private static final String GENDER = "Male";

    public Tomcat(String name, int age) {
        super(name, age, GENDER);
    }

    @Override
    public String produceSound() {
        return "MEOW";
    }
}