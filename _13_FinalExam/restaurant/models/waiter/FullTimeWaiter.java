package restaurant.models.waiter;

public class FullTimeWaiter extends BaseWaiter {

    public FullTimeWaiter(String name) {
        super(name, 8);
    }

    @Override
    public void work() {
        setEfficiency(Math.max(0, getEfficiency() - 1));
    }
}
