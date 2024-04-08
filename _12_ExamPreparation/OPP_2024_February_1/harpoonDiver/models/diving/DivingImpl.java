package harpoonDiver.models.diving;

import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.divingSite.DivingSite;

import java.util.ArrayList;
import java.util.Collection;

public class DivingImpl implements Diving {
    @Override
    public void searching(DivingSite divingSite, Collection<Diver> divers) {

        final Collection<String> seaCreatures = divingSite.getSeaCreatures();

        divers.forEach(d -> {
            while (d.canDive() && seaCreatures.iterator().hasNext()) {
                d.shoot();
                final String creature = seaCreatures.iterator().next();
                d.getSeaCatch().getSeaCreatures().add(creature);
                seaCreatures.remove(creature);
            }
        });
    }
}
