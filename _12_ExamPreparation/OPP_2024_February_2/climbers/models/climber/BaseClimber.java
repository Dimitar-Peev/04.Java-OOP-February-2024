package climbers.models.climber;

import climbers.models.roster.Roster;
import climbers.models.roster.RosterImpl;

import static climbers.common.ExceptionMessages.CLIMBER_NAME_NULL_OR_EMPTY;
import static climbers.common.ExceptionMessages.CLIMBER_STRENGTH_LESS_THAN_ZERO;

public abstract class BaseClimber implements Climber {

    private String name;
    private double strength;
    private Roster roster;

    public BaseClimber(String name, double strength) {
        this.setName(name);
        this.setStrength(strength);
        this.roster = new RosterImpl();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getStrength() {
        return strength;
    }

    @Override
    public Roster getRoster() {
        return roster;
    }

    @Override
    public boolean canClimb() {
        return this.strength > 0;
    }

    @Override
    public void climb() {
        double climbStrength = this.getClass().getSimpleName().equals("WallClimber") ?
                               30 : 60;

        //        try {
        //            this.setStrength(this.getStrength() - climbStrength);
        //        } catch (IllegalArgumentException ex) {
        //            this.setStrength(0);
        //        }

        this.setStrength(Math.max(0, this.strength - climbStrength));
    }

    private void setName(String name) {

        if (null == name || name.trim().isEmpty()) {
            throw new NullPointerException(CLIMBER_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    private void setStrength(double strength) {

        if (strength < 0) {
            throw new IllegalArgumentException(CLIMBER_STRENGTH_LESS_THAN_ZERO);
        }

        this.strength = strength;
    }
}
