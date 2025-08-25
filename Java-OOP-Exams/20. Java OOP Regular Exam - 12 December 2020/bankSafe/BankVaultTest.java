package bankSafe;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

import static org.junit.Assert.*;

public class BankVaultTest {

    private BankVault bankVault;
    private Item item;

    @Before
    public void setUp() {
        this.bankVault = new BankVault();
        this.item = new Item("Owner", "ItemId");
    }

    @Test
    public void testConstructorInitializesAllCells() {
        Map<String, Item> cells = bankVault.getVaultCells();
        assertEquals(12, cells.size());
        assertNull(cells.get("A1"));
        assertNull(cells.get("C4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItem_ThrowsWhenCellDoesNotExist() throws OperationNotSupportedException {
        bankVault.addItem("D1", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItem_ThrowsWhenCellIsTaken() throws OperationNotSupportedException {
        bankVault.addItem("A1", item);
        Item item2 = new Item("Owner2", "Item2");
        bankVault.addItem("A1", item2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddItem_ThrowsWhenItemExists() throws OperationNotSupportedException {
        bankVault.addItem("A1", item);
        bankVault.addItem("A2", item);
    }

    @Test
    public void testAddItem_AddsSuccessfully() throws OperationNotSupportedException {
        String result = bankVault.addItem("A1", item);
        assertEquals("Item:ItemId saved successfully!", result);
        assertSame(item, bankVault.getVaultCells().get("A1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItem_ThrowsWhenCellDoesNotExist() {
        bankVault.removeItem("D1", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItem_ThrowsWhenItemDoesNotMatch() throws OperationNotSupportedException {
        bankVault.addItem("A1", item);
        Item item2 = new Item("Owner2", "Item2");
        bankVault.removeItem("A1", item2);
    }

    @Test
    public void testRemoveItem_RemovesSuccessfully() throws OperationNotSupportedException {
        bankVault.addItem("A1", item);
        String result = bankVault.removeItem("A1", item);
        assertEquals("Remove item:ItemId successfully!", result);
        assertNull(bankVault.getVaultCells().get("A1"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetVaultCells_ReturnsUnmodifiableMap() {
        Map<String, Item> cells = bankVault.getVaultCells();
        cells.remove("A1");
    }

    @Test
    public void testGetOwner() {
        assertEquals("Owner", item.getOwner());
    }
}