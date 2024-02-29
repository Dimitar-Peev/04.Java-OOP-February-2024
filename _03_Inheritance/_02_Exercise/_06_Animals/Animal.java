package _03_Inheritance._02_Exercise._06_Animals;

public class Animal {
    private String name;
    private int age;
    private String gender;

    protected Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String produceSound() {
        return null;
    }

    private void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }

    private void setName(String name) {
        validateString(name);
        this.name = name;
    }

    private void validateString(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    public void setGender(String gender) {
        validateString(gender);
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("%s%n%s %d %s%n%s", this.getClass().getSimpleName(),
                this.getName(), this.getAge(), this.getGender(), this.produceSound());
    }
}
