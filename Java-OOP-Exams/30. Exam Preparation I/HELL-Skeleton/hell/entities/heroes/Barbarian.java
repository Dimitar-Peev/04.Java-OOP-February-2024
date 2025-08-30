package hell.entities.heroes;

public class Barbarian extends Heroes {
    public static final int STRENGTH = 90;
    public static final int AGILITY = 25;
    public static final int INTELLIGENCE = 10;
    public static final int HIT_POINTS = 350;
    public static final int DAMAGE = 150;

    public Barbarian(String name) {
        super(name, STRENGTH, AGILITY, INTELLIGENCE, HIT_POINTS, DAMAGE);
    }
}
