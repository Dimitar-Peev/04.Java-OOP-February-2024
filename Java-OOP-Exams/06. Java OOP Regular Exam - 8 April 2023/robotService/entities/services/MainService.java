package robotService.entities.services;

import robotService.entities.robot.MaleRobot;

public class MainService extends BaseService<MaleRobot> {
    private static final int CAPACITY = 30;

    public MainService(String name) {
        super(name, CAPACITY);
    }

}
