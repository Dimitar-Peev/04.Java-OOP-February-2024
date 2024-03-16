package football.entities.player;

public class Women extends BasePlayer {
    private static final double KG = 60;

    public Women(String name, String nationality, int strength) {
        super(name, nationality, KG, strength);
    }

    @Override
    public void stimulation() {
        int newStrength = getStrength() + 115;
        setStrength(newStrength);
    }
}