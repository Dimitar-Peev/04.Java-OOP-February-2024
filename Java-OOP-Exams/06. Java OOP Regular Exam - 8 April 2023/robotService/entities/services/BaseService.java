package robotService.entities.services;

import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;
import robotService.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static robotService.common.ConstantMessages.*;
import static robotService.common.ExceptionMessages.*;

public abstract class BaseService<T extends Robot> implements Service {
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Robot> robots;

    protected BaseService(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (StringUtils.isNullOrEmpty(name)) {
            throw new IllegalArgumentException(SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Robot> getRobots() {
        return this.robots;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

//    // TODO addRobot ver.1
//    @Override
//    public void addRobot(Robot robot) {
//        // TODO check logic
//        if (this.getRobots().size() == this.capacity) {
//            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_ROBOT);
//        }
//        this.robots.add(robot);
//    }


    // TODO addRobot ver.2
    @Override
    public void addRobot(Robot robot) {
        Type genericSuperType = this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperType).getActualTypeArguments();
        boolean isInstance = false;

        if (actualTypeArguments[0] instanceof Class) {
            isInstance = ((Class) actualTypeArguments[0]).isInstance(robot);
        }

        if (isInstance) {
            if (this.getRobots().size() < this.capacity) {
                this.robots.add(robot);
            } else {
                throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_ROBOT);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void removeRobot(Robot robot) {
        this.robots.remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void feeding() {
        this.robots.forEach(Robot::eating);
    }

    @Override
    public int sumHardness() {
        return this.getSupplements().stream().mapToInt(Supplement::getHardness).sum();
    }

    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public String getStatistics() {
        String template = "%s %s:" + System.lineSeparator()
                + "Robots: %s" + System.lineSeparator()
                + "Supplements: %d Hardness: %d";

        return String.format(template,
                this.getName(), this.getClass().getSimpleName(),
                this.getRobots().isEmpty() ? "none"
                        : this.getRobots().stream().map(Robot::getName).collect(Collectors.joining(" ")),
                this.getSupplements().size(), this.sumHardness());
    }
}
