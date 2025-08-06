package aquarium.core;

import java.util.ArrayList;
import java.util.Collection;

import aquarium.models.aquariums.*;
import aquarium.models.decorations.*;
import aquarium.models.fish.*;
import aquarium.repositories.*;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium;

        if ("FreshwaterAquarium".equals(aquariumType)) {
            aquarium = new FreshwaterAquarium(aquariumName);
        } else if ("SaltwaterAquarium".equals(aquariumType)) {
            aquarium = new SaltwaterAquarium(aquariumName);
        } else {
            throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }

        this.aquariums.add(aquarium);
        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;

        if ("Ornament".equals(type)) {
            decoration = new Ornament();
        } else if ("Plant".equals(type)) {
            decoration = new Plant();
        } else {
            throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }

        this.decorations.add(decoration);
        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = this.decorations.findByType(decorationType);

        if (decoration == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }

        Aquarium aquarium = findAquarium(aquariumName);
        aquarium.addDecoration(decoration);
        this.decorations.remove(decoration);
        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish;

        if ("FreshwaterFish".equals(fishType)) {
            fish = new FreshwaterFish(fishName, fishSpecies, price);
        } else if ("SaltwaterFish".equals(fishType)) {
            fish = new SaltwaterFish(fishName, fishSpecies, price);
        } else {
            throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }

        Aquarium aquarium = findAquarium(aquariumName);

        String aquariumClassName = aquarium.getClass().getSimpleName().substring(0, 5);
        String fishTypeSubstring = fishType.substring(0, 5);

        if (!aquariumClassName.equals(fishTypeSubstring)) {
            return WATER_NOT_SUITABLE;
        }

        aquarium.addFish(fish);
        return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = findAquarium(aquariumName);
        aquarium.feed();
        int fedCount = aquarium.getFish().size();
        return String.format(FISH_FED, fedCount);
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium = findAquarium(aquariumName);

        double value = 0;

        for (Fish fish : aquarium.getFish()) {
            value += fish.getPrice();
        }

        for (Decoration decoration : aquarium.getDecorations()) {
            value += decoration.getPrice();
        }

        return String.format(VALUE_AQUARIUM, aquariumName, value);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

        for (Aquarium aquarium : aquariums) {
            sb.append(aquarium.getInfo()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    private Aquarium findAquarium(String aquariumName) {
        return this.aquariums.stream().filter(a -> a.getName().equals(aquariumName)).findFirst().orElse(null);
    }
}
