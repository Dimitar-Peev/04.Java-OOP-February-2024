package magicGame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MagicianTests {
    private Magician magician;

    @Test(expected = NullPointerException.class)
    public void test_SetName_ShouldFail_when_nameIsNull() {
        magician = new Magician(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetName_ShouldFail_when_nameIsWhiteSpace() {
        magician = new Magician("    ", 10);
    }

    @Test
    public void test_SetName_ShouldSetCorrectName() {
        magician = new Magician("test", 10);
        assertEquals("test", magician.getUsername());
    }

    @Test
    public void test_SetHealth_ShouldSetCorrectHealth() {
        magician = new Magician("test", 10);
        assertEquals(10, magician.getHealth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_SetHealth_ShouldFail_when_Negative() {
        magician = new Magician("test", -1);
    }

    @Test(expected = NullPointerException.class)
    public void test_addMagic_ShouldFail_whenMagicIsNull() {
        magician = new Magician("test_name", 0);
        magician.addMagic(null);
    }

    @Test
    public void test_addMagic_ShouldAddCorrectMagic() {
        magician = new Magician("test_name", 0);
        magician.addMagic(new Magic("test", 15));
    }

    @Test
    public void test_removeMagic(){
        magician = new Magician("test_name", 0);
        Magic magic = new Magic("test", 15);
        magician.addMagic(magic);
        magician.removeMagic(magic);
        assertEquals(0, magician.getMagics().size());
    }

    @Test
    public void test_getMagic_ShouldAddCorrectMagic() {
        magician = new Magician("test_name", 0);
        Magic magic = new Magic("test", 15);
        magician.addMagic(magic);
        assertEquals(magic, magician.getMagic("test"));
    }

    @Test
    public void test_takeDamage_ShouldReduceHealth() {
        magician = new Magician("test_name", 10);
        magician.takeDamage(5);
        assertEquals(5, magician.getHealth());
    }

    @Test
    public void test_takeDamage_ShouldSetHealthZero_whenHealthIsZeroOrLess() {
        magician = new Magician("test_name", 10);
        magician.takeDamage(15);
        assertEquals(0, magician.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void test_takeDamage_ShouldThrowException_whenHealthIsZeroOrLess() {
        magician = new Magician("test_name", 10);
        magician.takeDamage(10);
        magician.takeDamage(10);
    }

    @Test
    public void test_getBullets(){
        Magic magic = new Magic("test", 15);
        assertEquals(15, magic.getBullets());
    }
}
