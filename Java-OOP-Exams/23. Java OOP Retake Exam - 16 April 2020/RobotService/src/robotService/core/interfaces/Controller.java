package robotService.core.interfaces;

public interface Controller {

    String manufacture(String robotType, String name, int energy, int happiness, int procedureTime);

    String repair(String robotName, int procedureTime);

    String work(String robotName, int procedureTime);

    String charge(String robotName, int procedureTime);

    String sell(String robotName, String ownerName);

    String history(String procedureType);

}
