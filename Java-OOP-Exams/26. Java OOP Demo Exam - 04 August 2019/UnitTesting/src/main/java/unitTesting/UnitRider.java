package unitTesting;

public class UnitRider {
    private String name;
    private UnitMotorcycle motorcycle;

    public UnitRider(String name, UnitMotorcycle motorcycle) {
        this.setName(name);
        this.setMotorcycle(motorcycle);
    }

    public String getName() {
        return this.name;
    }

    public UnitMotorcycle getMotorcycle() {
        return this.motorcycle;
    }

    private void setMotorcycle(UnitMotorcycle motorcycle) {
        this.motorcycle = motorcycle;
    }

    private void setName(String name) {
        this.name = name;
    }
}
