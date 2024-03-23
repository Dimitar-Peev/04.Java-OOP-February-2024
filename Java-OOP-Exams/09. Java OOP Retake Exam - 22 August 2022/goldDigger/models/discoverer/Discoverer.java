package goldDigger.models.discoverer;

import goldDigger.models.museum.Museum;

public interface Discoverer {
    String getName();

    double getEnergy();

    Museum getMuseum();

    void dig();

    boolean canDig();
}
