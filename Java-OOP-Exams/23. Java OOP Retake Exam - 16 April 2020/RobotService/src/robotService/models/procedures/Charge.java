package robotService.models.procedures;

import robotService.models.robots.interfaces.Robot;

public class Charge extends BaseProcedure{

    private static final int HAPPINESS = 12;

    private static final int ENERGY = 10;

    @Override
    public void doService(Robot robot, int procedureTime) {

        super.doService(robot, procedureTime);

        int newHappiness = robot.getHappiness() + HAPPINESS;
        robot.setHappiness(newHappiness);

        int newEnergy = robot.getEnergy() + ENERGY;
        robot.setEnergy(newEnergy);
    }
}
