package stuntClimb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

// Rename it to singular in order to run tests in maven build
// ClimbingTests -> ClimbingTest
// https://stackoverflow.com/questions/6178583/maven-does-not-find-junit-tests-to-run
public class ClimbingTests {

    private static final String INVALID_MOUNTAIN = "Invalid mountain!";

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    private Climbing climbing;

    @Before
    public void setUp() {
        climbing = new Climbing("Everest", 3);

        RockClimber rockClimber1 = new RockClimber("Peter", 250);
        RockClimber rockClimber2 = new RockClimber("Stefan", 350);
        RockClimber rockClimber3 = new RockClimber("Ivan", 500);

        climbing.addRockClimber(rockClimber1);
        climbing.addRockClimber(rockClimber2);
        climbing.addRockClimber(rockClimber3);
    }

    @Test(expected = NullPointerException.class)
    public void test_Constructor_ShouldThrow_When_Name_Null() {

        climbing = new Climbing(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_Constructor_ShouldThrow_When_Name_Empty() {
        climbing = new Climbing("", 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_Constructor_ShouldThrow_When_Name_Blank() {
        climbing = new Climbing("         ", 10);
    }

    @Test
    public void test_Constructor_ShouldThrow_When_Name_Blank_Or_Empty() {

        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage(INVALID_MOUNTAIN);
        climbing = new Climbing("         ", 10);

        climbing = new Climbing("", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Constructor_ShouldThrow_When_Capacity_Negative() {
        climbing = new Climbing("Everest", -10);
    }

    @Test
    public void test_Constructor_Should_Create_Correct_Object() {

        Assert.assertEquals("Everest", climbing.getName());
        Assert.assertEquals(3, climbing.getCapacity());
    }


    @Test(expected = IllegalArgumentException.class)
    public void test_AddRockClimber_ShouldThrow_When_NoCapacity() {

        climbing.addRockClimber(new RockClimber("Kaloyan", 100));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddRockClimber_ShouldThrow_When_Climber_Duplicated() {

        climbing.addRockClimber(new RockClimber("Ivan", 100));
    }

    @Test
    public void test_AddRockClimber_Should_Add_Correct() {

        Assert.assertEquals(3, climbing.getCount());
    }

    @Test
    public void test_RemoveRockClimber_Remove_Success() {

        boolean isRemoved = climbing.removeRockClimber("Peter");

        Assert.assertTrue(isRemoved);
        Assert.assertEquals(2, climbing.getCount());
    }

    @Test
    public void test_RemoveRockClimber_Remove_Not_Success() {

        boolean isRemoved = climbing.removeRockClimber("Ivaylo");

        Assert.assertFalse(isRemoved);
        Assert.assertEquals(3, climbing.getCount());
    }
}
