package robotService.models.procedures.interfaces;

import robotService.models.robots.interfaces.Robot;

public interface Procedure {

    String history();

    void doService(Robot robot, int procedureTime);

}
