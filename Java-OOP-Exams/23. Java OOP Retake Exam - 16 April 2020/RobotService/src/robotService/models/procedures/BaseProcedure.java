package robotService.models.procedures;

import robotService.models.procedures.interfaces.Procedure;
import robotService.models.robots.interfaces.Robot;

import java.util.ArrayList;
import java.util.Collection;

import static robotService.common.ExceptionMessages.INSUFFICIENT_PROCEDURE_TIME;

public abstract class BaseProcedure implements Procedure {

    protected Collection<Robot> robots;

    protected BaseProcedure() {
        this.robots = new ArrayList<>();
    }

    @Override
    public String history() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName()).append(System.lineSeparator());

        for (Robot robot : robots) {
            sb.append(robot.toString()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public void doService(Robot robot, int procedureTime) {
        if (robot.getProcedureTime() < procedureTime) {
            throw new IllegalArgumentException(INSUFFICIENT_PROCEDURE_TIME);
        }

        int newProcedureTime = robot.getProcedureTime() - procedureTime;
        robot.setProcedureTime(newProcedureTime);
        this.robots.add(robot);
    }

}
