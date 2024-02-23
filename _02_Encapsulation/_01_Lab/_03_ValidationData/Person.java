package _02_Encapsulation._01_Lab._03_ValidationData;

public class Person {
    private final static String TO_STRING_TEMPLATE = "%s %s gets %f leva";
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.setSalary(salary);
    }

    public void setSalary(double salary) {
        if (salary < 460) {
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }
        this.salary = salary;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age cannot be zero or negative integer");
        }
        this.age = age;
    }

    public void setLastName(String lastName) {
        if (lastName.length() < 3) {
            throw new IllegalArgumentException("Last name cannot be less than 3 symbols");
        }
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() < 3) {
            throw new IllegalArgumentException("First name cannot be less than 3 symbols");
        }
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_TEMPLATE, this.getFirstName(), this.getLastName(), this.getSalary());
    }

    public void increaseSalary(double bonus) {
        bonus = this.getAge() < 30 ? bonus / 2 : bonus;
        this.setSalary(this.getSalary() + (this.getSalary() * bonus) / 100);
    }

}
