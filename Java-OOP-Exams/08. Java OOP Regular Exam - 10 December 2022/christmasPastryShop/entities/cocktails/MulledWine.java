package christmasPastryShop.entities.cocktails;

public class MulledWine extends BaseCocktail {

    private static final double mulledWinePrice = 3.50;

    public MulledWine(String name, int size, String brand) {
        super(name, size, mulledWinePrice, brand);
    }
}
