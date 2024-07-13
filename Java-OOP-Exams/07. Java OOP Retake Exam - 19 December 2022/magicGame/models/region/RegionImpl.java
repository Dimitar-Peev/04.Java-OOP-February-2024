package magicGame.models.region;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;

import java.util.*;

public class RegionImpl implements Region {

    @Override
    public String start(Collection<Magician> magicians) {

        Deque<Magician> wizards = new ArrayDeque<>();
        Deque<Magician> blackWidows = new ArrayDeque<>();

        for (Magician magician : magicians) {
            if (magician instanceof Wizard) {
                wizards.offer(magician);
            } else if (magician instanceof BlackWidow) {
                blackWidows.offer(magician);
            } else {
                throw new IllegalStateException("Unexpected magician type " + magician.getClass().getSimpleName());
            }
        }

        while (!wizards.isEmpty() && !blackWidows.isEmpty()) {
            for (Magician wizard : wizards) {
                for (Magician blackWidow : blackWidows) {
                    blackWidow.takeDamage(wizard.getMagic().fire());
                    if (!blackWidow.isAlive()) {
                        blackWidows.remove(blackWidow);
                    }
                }
            }

            for (Magician blackWidow : blackWidows) {
                for (Magician wizard : wizards) {
                    wizard.takeDamage(blackWidow.getMagic().fire());
                    if (!wizard.isAlive()) {
                        wizards.remove(wizard);
                    }
                }
            }
        }

        return wizards.isEmpty() ? "Black widows win!" : "Wizards win!";
    }
}
