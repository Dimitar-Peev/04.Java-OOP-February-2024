package football;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FootballTeamTests {
    private static final int VACANT_POSITIONS = 12;
    private static final String PLAYER_NAME = "Dimitar";
    private static final String TEAM_NAME = "Dimitar's Team";
    private Footballer footballer;
    private FootballTeam footballTeam;

    @Before
    public void setUp() {
        this.footballer = new Footballer(PLAYER_NAME);
        this.footballTeam = new FootballTeam(TEAM_NAME, VACANT_POSITIONS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatingTeamWithNoPositions() {
        new FootballTeam(TEAM_NAME, -1);
    }

    @Test
    public void testCreatingTeamWithActualPositions() {
        assertEquals(VACANT_POSITIONS, footballTeam.getVacantPositions());
    }

    @Test(expected = NullPointerException.class)
    public void testCreatingTeamWithNullName() {
        new FootballTeam(null, 1);
    }

    @Test(expected = NullPointerException.class)
    public void testCreatingTeamWithEmptyName() {
        new FootballTeam("   ", 1);
    }

    @Test
    public void testCreatingTeamWithName() {
        assertEquals(TEAM_NAME, footballTeam.getName());
    }

    @Test
    public void testAddPlayer() {
        footballTeam.addFootballer(footballer);
        assertEquals(1, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPlayerWhenTeamIsFull() {
        FootballTeam team = new FootballTeam(TEAM_NAME, 0);
        team.addFootballer(footballer);
    }

    @Test
    public void testRemovePlayer() {
        footballTeam.addFootballer(footballer);
        assertEquals(1, footballTeam.getCount());
        footballTeam.removeFootballer(footballer.getName());
        assertEquals(0, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovePlayerWhenNoSuchPlayer() {
        footballTeam.removeFootballer("not_added");
    }

    @Test
    public void testFootballerForSaleIsActive(){
        footballTeam.addFootballer(footballer);
        footballTeam.footballerForSale(footballer.getName());
        assertFalse(footballer.isActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFootballerForSaleIsMissing(){
        footballTeam.removeFootballer("not_added");
    }
}
