package robotService.core;

import robotService.entities.robot.*;
import robotService.entities.services.*;
import robotService.entities.supplements.*;
import robotService.repositories.Repository;
import robotService.repositories.SupplementRepository;
import robotService.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

import static robotService.common.ConstantMessages.*;
import static robotService.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private final Repository supplementRepository;
    private final Collection<Service> services;

    public ControllerImpl() {
        this.supplementRepository = new SupplementRepository();
        this.services = new ArrayList<>();
    }

    @Override
    public String addService(String type, String name) {
        ServiceType serviceType = null;

        try {
            serviceType = ServiceType.valueOf(StringUtils.toUnderscoreString(type));
        } catch (IllegalArgumentException ex) {
            throw new NullPointerException(INVALID_SERVICE_TYPE);
        }

        switch (serviceType) {
            case MAIN_SERVICE:
                this.services.add(new MainService(name));
                break;
            case SECONDARY_SERVICE:
                this.services.add(new SecondaryService(name));
                break;
            default:
                throw new NullPointerException(INVALID_SERVICE_TYPE);
        }

        return String.format(SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {
        SupplementType supplementType = null;

        try {
            supplementType = SupplementType.valueOf(StringUtils.toUnderscoreString(type));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }

        switch (supplementType) {
            case PLASTIC_ARMOR:
                this.supplementRepository.addSupplement(new PlasticArmor());
                break;
            case METAL_ARMOR:
                this.supplementRepository.addSupplement(new MetalArmor());
                break;
            default:
                throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplement = this.supplementRepository.findFirst(supplementType);

        Service service = this.getServiceByName(serviceName);

        if (supplement == null || service == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }

        service.addSupplement(supplement);
        this.supplementRepository.removeSupplement(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        RobotType rtype = null;

        try {
            rtype = RobotType.valueOf(StringUtils.toUnderscoreString(robotType));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(INVALID_ROBOT_TYPE);
        }

        Robot robot = null;

        switch (rtype) {
            case FEMALE_ROBOT:
                robot = new FemaleRobot(robotName, robotKind, price);
                break;
            case MALE_ROBOT:
                robot = new MaleRobot(robotName, robotKind, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_ROBOT_TYPE);
        }

        Service service = this.getServiceByName(serviceName);

        try {
            service.addRobot(robot);
        } catch (IllegalArgumentException ignored) {
            return UNSUITABLE_SERVICE;
        }

        return String.format(SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
    }

    @Override
     public String feedingRobot(String serviceName) {
        Service service = this.getServiceByName(serviceName);

        if (service != null) {
            service.feeding();
            return String.format(FEEDING_ROBOT, service.getRobots().size());
        }

        return null;

    }

    @Override
    public String sumOfAll(String serviceName) {

        Service service = this.getServiceByName(serviceName);

        if (service != null) {
            double robotPriceSum = service.getRobots().stream().mapToDouble(Robot::getPrice).sum();
            double supplementPriceSum = service.getSupplements().stream().mapToDouble(Supplement::getPrice).sum();
            return String.format(VALUE_SERVICE, serviceName, robotPriceSum + supplementPriceSum);
        }

        return null;
    }

    @Override
    public String getStatistics() {
        StringBuilder text = new StringBuilder();

        this.services.forEach(s -> {
            text.append(s.getStatistics()).append(System.lineSeparator());
        });

        return text.toString().trim();
    }

    private Service getServiceByName(String serviceName) {
        return this.services.stream().filter(s -> s.getName().equals(serviceName))
                .findFirst().orElse(null);
    }
}
