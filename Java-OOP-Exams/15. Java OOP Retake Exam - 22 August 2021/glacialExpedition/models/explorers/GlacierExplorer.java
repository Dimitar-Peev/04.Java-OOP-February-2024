package glacialExpedition.models.explorers;

public class GlacierExplorer extends BaseExplorer{
    private static final double STARTING_ENERGY = 40;
    public GlacierExplorer(String name) {
        super(name, STARTING_ENERGY);
    }

}
