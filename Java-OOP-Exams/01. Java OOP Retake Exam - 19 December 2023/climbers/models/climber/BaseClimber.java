package climbers.models.climber;

import climbers.models.roster.Roster;
import climbers.models.roster.RosterImpl;

import static climbers.common.ExceptionMessages.*;
import static climbers.utils.StringUtils.nullOrEmpty;


public abstract class BaseClimber implements Climber {

    private String name;
    private double strength;
    private Roster roster;

    public BaseClimber(String name, double strength) {
        this.setName(name);
        this.setStrength(strength);
        this.roster = new RosterImpl();
    }

    public void setName(String name) {
        nullOrEmpty(name, CLIMBER_NAME_NULL_OR_EMPTY);
        this.name = name;
    }

    public void setStrength(double strength) {
        if (strength < 0) {
            throw new IllegalArgumentException(CLIMBER_STRENGTH_LESS_THAN_ZERO);
        }
        this.strength = strength;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getStrength() {
        return this.strength;
    }

    @Override
    public boolean canClimb() {
        return this.strength > 0;
    }

    @Override
    public Roster getRoster() {
        return this.roster;
    }

}
