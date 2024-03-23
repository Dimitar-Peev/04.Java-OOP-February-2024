package goldDigger.models.discoverer;

public class Archaeologist extends BaseDiscoverer{
    private static final double STARTING_ENERGY = 60;
    public Archaeologist(String name) {
        super(name, STARTING_ENERGY);
    }
}
