package christmasPastryShop.entities.cocktails;

public class Hibernation extends BaseCocktail {

    private static final double hibernationPrice = 4.50;

    public Hibernation(String name, int size, String brand) {
        super(name, size, hibernationPrice, brand);
    }
}
