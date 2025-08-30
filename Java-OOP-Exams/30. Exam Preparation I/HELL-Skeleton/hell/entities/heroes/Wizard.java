package hell.entities.heroes;

public class Wizard extends Heroes {
    public static final int STRENGTH = 25;
    public static final int AGILITY = 25;
    public static final int INTELLIGENCE = 100;
    public static final int HIT_POINTS = 100;
    public static final int DAMAGE = 250;

    public Wizard(String name) {
        super(name, STRENGTH, AGILITY, INTELLIGENCE, HIT_POINTS, DAMAGE);
    }
}
