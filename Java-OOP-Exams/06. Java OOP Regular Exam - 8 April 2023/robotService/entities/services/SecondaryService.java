package robotService.entities.services;

import robotService.entities.robot.FemaleRobot;

public class SecondaryService extends BaseService<FemaleRobot>{
    private static final int CAPACITY = 15;
    public SecondaryService(String name) {
        super(name, CAPACITY);
    }

// part of addRobot ver.1
//    @Override
//    public void addRobot(Robot robot) {
//        if (robot instanceof FemaleRobot) {
//            super.addRobot(robot);
//        }
//
//        throw  new IllegalArgumentException();
//    }
}
