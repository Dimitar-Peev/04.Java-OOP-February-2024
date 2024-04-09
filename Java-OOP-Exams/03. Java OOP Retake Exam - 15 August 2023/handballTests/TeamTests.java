package handball;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TeamTests {
    @Test
    public void testCreateSuccessfull() {
        Team team = new Team("test", 10);
        assertEquals("test", team.getName());
        assertEquals(10, team.getPosition());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateInvalidName() {
        new Team(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCapacity() {
        new Team("test", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNoCapacity() {
        Team team = new Team("test", 0);
        HandballPlayer peter = new HandballPlayer("Peter");
        team.add(peter);
    }

    @Test
    public void testRemoveExisting() {
        Team team = new Team("test", 10);
        HandballPlayer peter = new HandballPlayer("Peter");
        team.add(peter);
        assertEquals(1, team.getCount());
        team.remove("Peter");
        assertEquals(0, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void restRemoveNonExistingCat() {
        Team team = new Team("test", 10);
        team.remove("Silvester");
    }

    @Test
    public void playerForAnotherTeam() {
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetNonExistingPlayerFromAnotherTeam() {
        Team team = new Team("test", 10);
        team.playerForAnotherTeam("Ivan");
    }
    @Test
    public void testPlayerForAnotherTeam() {
        Team team = new Team("Sparta", 10);
        HandballPlayer peter = new HandballPlayer("Peter");
        team.add(peter);
        HandballPlayer returned = team.playerForAnotherTeam("Peter");
        assertFalse(returned.isActive());
    }

    @Test
    public void testStatistics() {
        Team team = new Team("Sparta", 10);
        HandballPlayer peter = new HandballPlayer("Peter");
        team.add(peter);
        assertEquals("The player Peter is in the team Sparta.", team.getStatistics());
    }

}