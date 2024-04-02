package harpoonDiver.models.seaCatch;

import java.util.ArrayList;
import java.util.Collection;

public class BaseSeaCatch implements SeaCatch {
    private Collection<String> seaCreatures;

    public BaseSeaCatch() {

    }

    @Override  // lazy loading
    public Collection<String> getSeaCreatures() {
        if (this.seaCreatures == null) {
            this.seaCreatures = new ArrayList<>();
        }
        return this.seaCreatures;
    }
}
