package fairyShop.models.present;

public interface Present {
    String getName();

    int getEnergyRequired();

    boolean isDone();

    void getCrafted();
}
