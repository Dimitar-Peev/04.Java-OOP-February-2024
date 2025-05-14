package robotService.models.robots.interfaces;

public interface Robot {

    String getName();

    int getHappiness();

    void setHappiness(int happiness);

    int getEnergy();

    void setEnergy(int energy);

    int getProcedureTime();

    void setProcedureTime(int procedureTime);

    void setOwner(String owner);

    void setBought(boolean bought);

    boolean isRepaired();

    void setRepaired(boolean repaired);

}
