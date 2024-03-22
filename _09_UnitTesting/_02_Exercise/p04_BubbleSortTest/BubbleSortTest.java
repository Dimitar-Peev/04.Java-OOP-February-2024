package UnitTesting_Exercises.p04_BubbleSortTest;

import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleSortTest {
    
    @Test
    public void when_notSortedArrayPassedToSort_then_sortedArray() {
        int[] array = new int[]{13, 42, 0, -5, 69, 73};
        Bubble.sort(array);
        int[] expectedArray = new int[]{-5, 0, 13, 42, 69, 73};
        assertEquals(array.length, expectedArray.length);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    public void when_sortedArrayPassedToSort_then_sameArray() {
        int[] array = new int[]{-5, 0, 13, 42, 69, 73};
        Bubble.sort(array);
        int[] expectedArray = new int[]{-5, 0, 13, 42, 69, 73};
        assertArrayEquals(expectedArray, array);
    }

    @Test
    public void when_emptyArrayPassedToSort_then_emptyArray() {
        int[] array = new int[]{};
        Bubble.sort(array);
        assertEquals(array.length, 0);
    }
}
