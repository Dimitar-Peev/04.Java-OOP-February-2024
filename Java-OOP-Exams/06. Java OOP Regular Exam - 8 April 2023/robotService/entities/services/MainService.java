package robotService.entities.services;

import robotService.entities.robot.MaleRobot;

public class MainService extends BaseService<MaleRobot> {
    private static final int CAPACITY = 30;

    public MainService(String name) {
        super(name, CAPACITY);
    }

// part of addRobot ver.1
//    @Override
//    public void addRobot(Robot robot) {
//        if (robot instanceof MaleRobot) {
//            super.addRobot(robot);
//        }
//
//        throw  new IllegalArgumentException();
//    }
}
