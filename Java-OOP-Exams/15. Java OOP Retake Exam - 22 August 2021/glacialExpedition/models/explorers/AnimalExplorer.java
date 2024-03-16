package glacialExpedition.models.explorers;

public class AnimalExplorer extends BaseExplorer{
    private static final double STARTING_ENERGY = 100.00;
    public AnimalExplorer(String name) {
        super(name, STARTING_ENERGY);
    }
}
