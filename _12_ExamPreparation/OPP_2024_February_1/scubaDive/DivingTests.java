package scubaDive;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class DivingTests {
    public static final String DEFAULT_NAME = "Test";
    private DeepWaterDiver diver;

    @Before
    public void init() {
        this.diver = new DeepWaterDiver("Test", 30);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDeeWaterDiverWithZeroCapacityShouldThrow() {
        Diving diving = new Diving(DEFAULT_NAME, 0);
        diving.addDeepWaterDiver(this.diver);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDeeWaterDiverWithExistingDiverShouldThrow() {
        Diving diving = new Diving(DEFAULT_NAME, 1);
        diving.addDeepWaterDiver(this.diver);
        diving.addDeepWaterDiver(this.diver);
    }

    @Test
    public void addDeeWaterDiverShouldAddCorrect() {
        Diving diving = new Diving(DEFAULT_NAME, 1);
        diving.addDeepWaterDiver(this.diver);
        assertEquals(1, diving.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCapacityShouldThrowWithNegativeValue() throws Throwable {
        Diving diving = new Diving("Test", 1);
        final Method setCapacityMethod = diving.getClass().getDeclaredMethod("setCapacity", int.class);
        setCapacityMethod.setAccessible(Boolean.TRUE);
        try {
            setCapacityMethod.invoke(diving, -10);
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                throw ex.getCause();
            }
        }
    }

    @Test(expected = NullPointerException.class)
    public void setNameWithNullShouldThrow() {
        Diving diving = new Diving(null, 1);
    }

}
