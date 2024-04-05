package onlineShop.core;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.*;
import java.util.stream.Collectors;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Map<Integer, Computer> computers;
    private Map<Integer, Component> components;
    private Map<Integer, Peripheral> peripherals;

    public ControllerImpl() {
        this.computers = new LinkedHashMap<>();
        this.components = new LinkedHashMap<>();
        this.peripherals = new LinkedHashMap<>();

    }

    //NOTE: For each command, except for
    //"addComputer" and "buyBest",
    //you must check if a computer, with that id, exists in the collection of the computer.
    //If it doesn't, throw an IllegalArgumentException with the message
    //"Computer with this id does not exist.".

    private void checkComputerId(int id) {
        if (!this.computers.containsKey(id)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {

        if (computers.containsKey(id)) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }

        Computer computer = null;

        switch (computerType) {
            case "DesktopComputer":
                computer = new DesktopComputer(id, manufacturer, model, price);
                break;
            case "Laptop":
                computer = new Laptop(id, manufacturer, model, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }

        this.computers.put(id, computer); // (computer.getId(), computer)

        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer,
                               String model, double price, double overallPerformance, int generation) {

        checkComputerId(computerId);

        if (this.components.containsKey(id)) {
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }

        Component component = null;

        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }

        this.components.put(id, component);
        this.computers.get(computerId).addComponent(component);

        return String.format(ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer,
                                String model, double price, double overallPerformance, String connectionType) {
        checkComputerId(computerId);

        if (this.peripherals.containsKey(id)) {
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        }

        Peripheral peripheral = null;

        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);

        }

        this.peripherals.put(id, peripheral);
        this.computers.get(computerId).addPeripheral(peripheral);

        return String.format(ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        checkComputerId(computerId);
        Peripheral peripheral = computers.get(computerId).removePeripheral(peripheralType);
        this.components.remove(peripheral.getId());
        return String.format(REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
    }


    @Override
    public String removeComponent(String componentType, int computerId) {
        checkComputerId(computerId);
        Component component = computers.get(computerId).removeComponent(componentType);
        this.components.remove(component.getId());
        return String.format(REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String buyComputer(int id) {
        checkComputerId(id);
        Computer computer = computers.remove(id);
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        // TODO check again
//        Computer computer = computers.values().stream().filter(s -> s.getPrice() <= budget)
//                        .max(Comparator.comparingDouble(Computer::getOverallPerformance)).orElse(null);
//
//        if (computer == null) {
//            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
//        }
//
//        computers.remove(computer);

        List<Computer> filteredComputers = this.computers.values().stream()
                .filter(c -> c.getPrice() <= budget)
                .sorted(Comparator.comparing(Computer::getOverallPerformance).reversed())
                .collect(Collectors.toList());

        if (filteredComputers.isEmpty()) {
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
        }

        Computer computer = filteredComputers.get(0);

        this.computers.remove(computer.getId());

        return computer.toString();
    }

    @Override
    public String getComputerData(int id) {
        checkComputerId(id);
        return computers.get(id).toString();
    }
}
