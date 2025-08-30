package hell.entities.heroes;

public class Assassin extends Heroes {
    public static final int STRENGTH = 25;
    public static final int AGILITY = 100;
    public static final int INTELLIGENCE = 15;
    public static final int HIT_POINTS = 150;
    public static final int DAMAGE = 300;

    public Assassin(String name) {
        super(name, STRENGTH, AGILITY, INTELLIGENCE, HIT_POINTS, DAMAGE);
    }
}
