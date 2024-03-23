package bank;

import org.junit.*;

import static org.junit.Assert.*;

public class BankTests {
    private static final String NAME = "test_bank";
    private static final int CAPACITY = 10;
    private Bank bank;
    private Client client;

    @Before
    public void setUp() {
        this.bank = new Bank(NAME, CAPACITY);
        this.client = new Client("test_client");
    }

    @Test(expected = NullPointerException.class)
    public void test_NullName() {
        new Bank(null, CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_negativeCapacity() {
        new Bank(NAME, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_fullCapacity() {
        Bank bank1 = new Bank(NAME, 0);
        bank1.addClient(client);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeMissingClient() {
        bank.removeClient(client.getName());
    }

    @Test
    public void test_notApproved() {
        bank.addClient(client);
        bank.loanWithdrawal(client.getName());
        assertFalse(client.isApprovedForLoan());
    }
}
