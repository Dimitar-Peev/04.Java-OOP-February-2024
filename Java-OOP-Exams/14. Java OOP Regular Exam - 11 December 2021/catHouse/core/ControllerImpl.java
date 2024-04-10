package catHouse.core;

import catHouse.entities.cat.*;
import catHouse.entities.houses.*;
import catHouse.entities.toys.*;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private ToyRepository toyRepository;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toyRepository = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {

        House house = null;

        switch (type) {
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
            default:
                throw new NullPointerException(INVALID_HOUSE_TYPE);
        }

        this.houses.add(house);

        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {

        Toy toy = null;

        switch (type) {
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }

        this.toyRepository.buyToy(toy);

        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {

        Toy toy = this.toyRepository.findFirst(toyType);

        if (toy == null) {
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
        }

        this.houses
                .stream()
                .filter(e -> e.getName().equals(houseName))
                .findFirst()
                .get()
                .buyToy(toy);

        this.toyRepository.removeToy(toy);

        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {

        Cat cat = null;

        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }

        House house = this.houses
                .stream()
                .filter(e -> e.getName().equals(houseName))
                .findFirst()
                .get();

        if (house.getClass().getSimpleName().contains("Short") && cat.getClass().getSimpleName().contains("Short")) {
            house.addCat(cat);
        } else if (house.getClass().getSimpleName().contains("Long") && cat.getClass().getSimpleName().contains("Long")) {
            house.addCat(cat);
        } else {
            return String.format(UNSUITABLE_HOUSE);
        }

        return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {

        House house = this.houses
                .stream()
                .filter(e -> e.getName().equals(houseName))
                .findFirst()
                .get();

        house.feeding();

        return String.format(FEEDING_CAT, house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {

        House house = this.houses
                .stream()
                .filter(e -> e.getName().equals(houseName))
                .findFirst()
                .get();

        double catsTotalPrice = house.getCats()
                .stream()
                .mapToDouble(Cat::getPrice)
                .sum();

        double toysTotalPrice = house.getToys()
                .stream()
                .mapToDouble(Toy::getPrice)
                .sum();

        return String.format(VALUE_HOUSE, houseName, catsTotalPrice + toysTotalPrice);
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();
        this.houses.forEach(e -> sb.append(e.getStatistics()).append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
