package archeologicalExcavations;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class ExcavationTests {
    private static final String EXPECTED_NAME = "Pesho";
    private static final int EXPECTED_CAPACITY = 10;
    private Excavation excavation;

    @Before
    public void setUp() {
        excavation = new Excavation(EXPECTED_NAME, EXPECTED_CAPACITY);
    }

    @Test(expected = NullPointerException.class) 
    public void test_Constructor_nameIsEmpty() {
        excavation = new Excavation("", EXPECTED_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class) 
    public void test_Constructor_NegativeCapacity() {
        excavation = new Excavation(EXPECTED_NAME, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_NoCapacity() {          
        excavation = new Excavation(EXPECTED_NAME, 1);
        Archaeologist archaeologist1 = new Archaeologist("Dimitar", 100);
        Archaeologist archaeologist2 = new Archaeologist("Ivan", 80);

        excavation.addArchaeologist(archaeologist1);
        excavation.addArchaeologist(archaeologist2);
    }

    @Test
    public void test_Remove_Succefully() { 
        excavation.addArchaeologist(new Archaeologist(EXPECTED_NAME, EXPECTED_CAPACITY));
        boolean isRemoved = excavation.removeArchaeologist("Pesho");
        assertTrue(isRemoved);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_Duplicate() { 
        excavation.addArchaeologist(new Archaeologist(EXPECTED_NAME, EXPECTED_CAPACITY));
        excavation.addArchaeologist(new Archaeologist(EXPECTED_NAME, EXPECTED_CAPACITY));
    }
}
