package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer {
    private static final double STARTING_ENERGY = 60.00;

    public NaturalExplorer(String name) {
        super(name, STARTING_ENERGY);
    }

    @Override
    public void search() {
        setEnergy(Math.max(0, getEnergy() - 7));
    }
}
