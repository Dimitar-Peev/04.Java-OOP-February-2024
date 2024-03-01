package animals;

public abstract class Animal {
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

    public abstract String produceSound();


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
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    public void setGender(String gender) {
        validateString(gender);
        this.gender = gender;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(System.lineSeparator());
        sb.append(String.format("%s %d %s", this.getName(), this.getAge(), this.getGender())).append(System.lineSeparator());
        sb.append(produceSound()).append(System.lineSeparator());

        return sb.toString().trim();
    }
}
