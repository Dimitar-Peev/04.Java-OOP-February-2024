package UnitTesting_Exercises.p03_IteratorTest;

import org.junit.*;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest { 

    @Test(expected = OperationNotSupportedException.class)
    public void when_nullElementsPassedToConstructor_then_throwException() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    @Test
    public void when_validElementsPassedToConstructor_then_moveReturnCorrectBoolean() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator("wood", "steel", "gold");
        assertTrue(listIterator.move());
        assertTrue(listIterator.move());
        assertFalse(listIterator.move());
    }
    @Test
    public void when_validElementsPassedToConstructor_then_hasNextReturnCorrectBoolean() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator("wood", "steel", "gold");
        assertTrue(listIterator.hasNext());
        assertTrue(listIterator.hasNext());
        assertTrue(listIterator.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void when_emptyListIterator_then_ThrowException() throws OperationNotSupportedException {
        new ListIterator().print();
    }

    @Test
    public void when_elementsInListIterator_then_print() throws OperationNotSupportedException {
        String[] elements = new String[]{"wood", "steel", "gold"};
        ListIterator listIterator = new ListIterator(elements);
        for (int i = 0; listIterator.hasNext(); listIterator.move(), i++) {
            assertEquals(elements[i],listIterator.print());
        }
    }
}