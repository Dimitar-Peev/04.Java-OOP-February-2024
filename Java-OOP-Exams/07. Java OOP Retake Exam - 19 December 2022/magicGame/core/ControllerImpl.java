package magicGame.core;

import magicGame.models.magicians.*;
import magicGame.models.magics.*;
import magicGame.models.region.*;
import magicGame.repositories.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import static magicGame.common.ExceptionMessages.*;
import static magicGame.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private MagicRepositoryImpl magics;
    private MagicianRepositoryImpl magicians;
    private Region region;

    public ControllerImpl() {
        this.magics = new MagicRepositoryImpl();
        this.magicians = new MagicianRepositoryImpl();
        this.region = new RegionImpl();
    }

    @Override
    public String addMagic(String type, String name, int bulletsCount) {

        Magic magic = null;
        switch (type) {
            case "RedMagic":
                magic = new RedMagic(name, bulletsCount);
                break;
            case "BlackMagic":
                magic = new BlackMagic(name, bulletsCount);
                break;
            default:
                throw new IllegalArgumentException(INVALID_MAGIC_TYPE);
        }

        this.magics.addMagic(magic);
        return String.format(SUCCESSFULLY_ADDED_MAGIC, magic.getName());
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {

        Magic magic = magics.findByName(magicName);
        if (magic == null) {
            throw new NullPointerException(MAGIC_CANNOT_BE_FOUND);
        }

        Magician magician = null;
        switch (type) {
            case "Wizard":
                magician = new Wizard(username, health, protection, magic);
                break;
            case "BlackWidow":
                magician = new BlackWidow(username, health, protection, magic);
                break;
            default:
                throw new IllegalArgumentException(INVALID_MAGICIAN_TYPE);
        }

        this.magicians.addMagician(magician);
        return String.format(SUCCESSFULLY_ADDED_MAGICIAN, magician.getUsername());
    }

    @Override
    public String startGame() {
        Collection<Magician> magiciansAlive = this.magicians.getData()
                .stream()
                .filter(Magician::isAlive)
                .collect(Collectors.toList());
        return this.region.start(magiciansAlive);
    }

    @Override
    public String report() {
        return this.magicians.getData().stream()
                .sorted(Comparator.comparing(Magician::getHealth)
                        .thenComparing(Magician::getUsername)
                        .thenComparing(m -> m.getClass().getSimpleName()))
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
