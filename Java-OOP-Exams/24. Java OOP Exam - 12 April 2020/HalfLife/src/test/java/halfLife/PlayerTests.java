package halfLife;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PlayerTests {

    @Test(expected = NullPointerException.class)
    public void test_usernameIsNullShouldThrowEx() {
        Player player = new Player(null, 100);
    }

    @Test(expected = NullPointerException.class)
    public void test_usernameIsEmptyShouldThrowEx() {
        Player player = new Player("", 100);
    }

    @Test
    public void test_usernameIsCorrectShouldReturnResult() {
        Player player = new Player("Dimitar", 100);
        assertEquals("Dimitar", player.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_healthIsNegativeShouldThrowEx() {
        Player player = new Player("Dimitar", -10);
    }

    @Test
    public void test_healthIsPositiveShouldReturnResult() {
        Player player = new Player("Dimitar", 100);
        assertEquals(100, player.getHealth());
    }

    @Test
    public void test_getGunsShouldWorkCorrectly() {
        Player player = new Player("Dimitar", 100);
        Gun gun1 = new Gun("Makarov", 12);
        Gun gun2 = new Gun("Remington", 50);
        Gun gun3 = new Gun("Glock", 10);

        player.addGun(gun1);
        player.addGun(gun2);
        player.addGun(gun3);

        assertEquals(3, player.getGuns().size());
    }

    @Test
    public void test_takeDamageShouldWorkCorrectly() {
        Player player = new Player("Dimitar", 100);
        player.takeDamage(50);
        assertEquals(50, player.getHealth());
    }

    @Test
    public void test_takeDamageShouldReturnZero() {
        Player player = new Player("Dimitar", 40);
        player.takeDamage(50);
        assertEquals(0, player.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void test_takeDamageShouldThrowEx() {
        Player player = new Player("Dimitar", 0);
        player.takeDamage(50);
    }

    @Test(expected = NullPointerException.class)
    public void test_addGunIsNullShouldThrowEx() {
        Player player = new Player("Dimitar", 100);
        Gun gun = null;
        player.addGun(gun);
    }

    @Test
    public void test_removeGunShouldWorkCorrectly() {
        Player player = new Player("Dimitar", 100);
        Gun gun1 = new Gun("Makarov", 12);
        Gun gun2 = new Gun("Remington", 50);

        player.addGun(gun1);
        player.addGun(gun2);

        player.removeGun(gun1);

        assertEquals(1, player.getGuns().size());
    }

    @Test
    public void test_getGunShouldReturnGun() {
        Player player = new Player("Dimitar", 100);
        Gun gun1 = new Gun("Makarov", 12);
        Gun gun2 = new Gun("Remington", 50);

        player.addGun(gun1);
        player.addGun(gun2);

        assertEquals(gun1, player.getGun("Makarov"));
    }

    @Test
    public void test_getGunShouldReturnNull() {
        Player player = new Player("Dimitar", 100);
        Gun gun2 = new Gun("Remington", 50);
        player.addGun(gun2);

        assertNull(player.getGun("Makarov"));
    }

    @Test
    public void test_getBulletsShouldReturnBullets() {
        Gun gun = new Gun("Makarov", 12);
        assertEquals(12, gun.getBullets());
    }
}
