package robotService.models.procedures;

import robotService.models.robots.interfaces.Robot;

import static robotService.common.ExceptionMessages.*;

public class Repair extends BaseProcedure{

    private static final int HAPPINESS = 5;

    @Override
    public void doService(Robot robot, int procedureTime) {

        super.doService(robot, procedureTime);

        int newHappiness = robot.getHappiness() - HAPPINESS;
        robot.setHappiness(newHappiness);

        if (robot.isRepaired()) {
            throw new IllegalArgumentException(String.format(ALREADY_REPAIRED, robot.getName()));
        }

        robot.setRepaired(true);
    }
}
