package _01_Lab._03_Animals;

public abstract class Animal {
    private String name;
    private String favouriteFood;

    protected Animal(String name, String favouriteFood) {
        this.name = name;
        this.favouriteFood = favouriteFood;
    }

    protected String getName() {
        return this.name;
    }

    protected String getFavouriteFood() {
        return this.favouriteFood;
    }

  //  public abstract String explainSelf();

    public String explainSelf() {
        return String.format("I am %s and my favourite food is %s", this.getName(), this.getFavouriteFood());
    }
}
