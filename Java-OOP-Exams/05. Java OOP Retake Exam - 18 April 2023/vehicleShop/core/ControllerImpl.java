package vehicleShop.core;

import vehicleShop.models.shop.Shop;
import vehicleShop.models.shop.ShopImpl;
import vehicleShop.models.tool.Tool;
import vehicleShop.models.tool.ToolImpl;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.vehicle.VehicleImpl;
import vehicleShop.models.worker.*;
import vehicleShop.repositories.VehicleRepository;
import vehicleShop.repositories.WorkerRepository;

import java.util.List;
import java.util.stream.Collectors;

import static vehicleShop.common.ConstantMessages.*;
import static vehicleShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private final WorkerRepository workerRepository; 
    private final VehicleRepository vehicleRepository; 
    private int countMadeVehicle; 

    public ControllerImpl() {
        this.workerRepository = new WorkerRepository();
        this.vehicleRepository = new VehicleRepository();
        this.countMadeVehicle = 0;
    }

    @Override
    public String addWorker(String type, String workerName) {
        Worker worker = null;

        switch (type) {
            case "FirstShift":
                worker = new FirstShift(workerName);
                break;
            case "SecondShift":
                worker = new SecondShift(workerName);
                break;
            default:
                throw new IllegalArgumentException(WORKER_TYPE_DOESNT_EXIST);
        }
		
        this.workerRepository.add(worker); 
        return String.format(ADDED_WORKER, type, workerName);
    }

    @Override
    public String addVehicle(String vehicleName, int strengthRequired) {
        Vehicle vehicle = new VehicleImpl(vehicleName, strengthRequired);
        this.vehicleRepository.add(vehicle);
        return String.format(SUCCESSFULLY_ADDED_VEHICLE, vehicleName);
    }

    @Override
    public String addToolToWorker(String workerName, int power) {
        Worker worker = this.workerRepository.findByName(workerName);
		
        if (worker == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }
		
        Tool tool = new ToolImpl(power); 
        worker.addTool(tool);

        return String.format(SUCCESSFULLY_ADDED_TOOL_TO_WORKER, power, workerName);
    }

    @Override
    public String makingVehicle(String vehicleName) {
        List<Worker> availableWorkers = this.workerRepository.getWorkers().stream()
                .filter(worker -> worker.getStrength() > 70)
                .collect(Collectors.toList()); 

        if (availableWorkers.isEmpty()) {
            throw new IllegalArgumentException(NO_WORKER_READY);
        }

        Vehicle vehicle = vehicleRepository.findByName(vehicleName); 

        Shop shop = new ShopImpl(); 
        long brokenTools = 0; 
		
        while (!availableWorkers.isEmpty() && !vehicle.reached()) {
            Worker worker = availableWorkers.get(0);
            shop.make(vehicle, worker);
            brokenTools += worker.getTools().stream().filter(Tool::isUnfit).count();

            if (!worker.canWork() || worker.getTools().stream().noneMatch(t -> !t.isUnfit())) {
                availableWorkers.remove(worker);
            }
        }

        if (vehicle.reached()) {
            countMadeVehicle++;
            return String.format(VEHICLE_DONE, vehicle.getName(), "done")
                    + String.format(COUNT_BROKEN_INSTRUMENTS, brokenTools);
        } else {
            return String.format(VEHICLE_DONE, vehicle, "not done")
                    + String.format(COUNT_BROKEN_INSTRUMENTS, brokenTools);
        }
    }

    @Override
    public String statistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d vehicles are ready!", countMadeVehicle)).append(System.lineSeparator());
        sb.append("Info for workers:").append(System.lineSeparator());
        this.workerRepository.getWorkers().forEach(worker -> {
            sb.append(worker.toString()).append(System.lineSeparator());
        });
        return sb.toString().trim();
    }
}
