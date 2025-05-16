package computers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Computer {

    private String name;
    private List<Part> parts;

    public Computer(String name) {
        this.setName(name);
        this.parts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public List<Part> getParts() {
        return Collections.unmodifiableList(this.parts);
    }

    public double getTotalPrice() {
        return this.parts.stream().mapToDouble(Part::getPrice).sum();
    }

    public void addPart(Part part) {
        if (part == null) {
            throw new IllegalArgumentException("Cannot be null!");
        }

        this.parts.add(part);
    }

    public boolean removePart(Part part) {
        return this.parts.remove(part);
    }

    public Part getPart(String partName) {
        return this.parts.stream().filter(p -> p.getName().equals(partName)).findFirst().orElse(null);
    }

}
