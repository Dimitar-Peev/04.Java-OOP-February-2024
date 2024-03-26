package spaceStation.models.planets;

import java.util.ArrayList;
import java.util.List;

import static spaceStation.common.ExceptionMessages.*;

public class PlanetImpl implements Planet {
    private String name;
    private List<String> items;

    public PlanetImpl(String name) {
        this.setName(name);
        this.items = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.isBlank()) { 
            throw new NullPointerException(PLANET_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<String> getItems() {
        return this.items;
    }
}
