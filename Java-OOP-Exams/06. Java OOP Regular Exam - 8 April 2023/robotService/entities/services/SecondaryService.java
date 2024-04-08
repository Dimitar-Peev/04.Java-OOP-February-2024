package robotService.entities.services;

import robotService.entities.robot.FemaleRobot;

public class SecondaryService extends BaseService<FemaleRobot>{
    private static final int CAPACITY = 15;
    public SecondaryService(String name) {
        super(name, CAPACITY);
    }

}
