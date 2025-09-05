package bankAccount;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BankAccountTest {
    private static final String DEFAULT_NAME = "Dimitar";
    private BankAccount bankAccount;

    @Before
    public void setUp() {
        this.bankAccount = new BankAccount(DEFAULT_NAME, new BigDecimal("100.0"));
    }

    @Test
    public void getName() {
        assertEquals(DEFAULT_NAME, this.bankAccount.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNameShouldThrowExceptionWithLess3() {
        new BankAccount("AB", new BigDecimal(100));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNameShouldThrowExceptionWithMore25() {
        new BankAccount("TestTestTestTestTestTestTest", new BigDecimal(100));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setBalanceShouldThrowExceptionWithNegativeNumber() {
        new BankAccount("test", new BigDecimal(-10));
    }

    @Test
    public void getBalanced() {
        BigDecimal expected = new BigDecimal("100.0");
        assertEquals(expected, this.bankAccount.getBalance());
    }

    @Test
    public void deposit() {
        BigDecimal amount = new BigDecimal("100.0");
        this.bankAccount.deposit(amount);

        BigDecimal expected = new BigDecimal("200.0");
        assertEquals(expected, this.bankAccount.getBalance());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void depositShouldThrow() {
        this.bankAccount.deposit(new BigDecimal("-100.0"));
    }

    @Test
    public void withdrawShouldReturnCorrectly() {
        BigDecimal actual = this.bankAccount.withdraw(new BigDecimal(50));
        BigDecimal expected = new BigDecimal(50);
        assertEquals(expected, actual);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void withdrawShouldThrowIfNotEnoughMoney() {
        this.bankAccount.withdraw(new BigDecimal(200));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void withdrawShouldThrowIfNegativeMoney() {
        this.bankAccount.withdraw(new BigDecimal(-1));
    }

    @Test
    public void withdrawShouldSetCorrectly() {
        BigDecimal amount = new BigDecimal(50);
        this.bankAccount.withdraw(amount);
        BigDecimal expected = new BigDecimal("50.0");
        assertEquals(expected, this.bankAccount.getBalance());
    }
}