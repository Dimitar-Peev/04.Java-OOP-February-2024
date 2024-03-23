package bank.entities.client;

public class Adult extends BaseClient {
    public Adult(String name, String ID, double income) {
        super(name, ID, 4, income);
    }

    @Override
    public void increase() {
        setInterest(getInterest() + 2);
    }
}
