package gifts;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GiftFactoryTests {
    private GiftFactory factory;
    private Gift gift;

    @Before
    public void setUp() {
        this.factory = new GiftFactory();
        this.gift = new Gift("test", 10);
    }

    @Test
    public void testConstructorGift() {
        assertEquals("test", gift.getType());
        assertEquals(10, gift.getMagic(), 0);
    }

    @Test
    public void testCreateGift() {
        factory.createGift(gift);
    }

    @Test
    public void testCreateGiftFactoryCorrect() {
        factory.createGift(gift);
        assertEquals(1, factory.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateGift_ThrowException_WhenAlreadyCreated() {
        factory.createGift(gift);
        factory.createGift(gift);
    }

    @Test
    public void testRemoveGiftFactoryCorrect() {
        factory.createGift(gift);
        assertEquals(1, factory.getCount());
        factory.removeGift(gift.getType());
        assertEquals(0, factory.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGift_ThrowException_WhenNameIsNull() {
        factory.removeGift(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGift_ThrowException_WhenNameIsEmpty() {
        factory.removeGift("   ");
    }

    @Test
    public void testGetPresentWithLeastMagic() {
        Gift gift1 = new Gift("gift1", 20);
        Gift gift2 = new Gift("gift2", 5);
        Gift gift3 = new Gift("gift3", 10);
        factory.createGift(gift1);
        factory.createGift(gift2);
        factory.createGift(gift3);

        assertEquals(gift2, factory.getPresentWithLeastMagic());
    }

    @Test
    public void testGetPresentWithLeastMagic_WhenEmpty() {
        assertNull(factory.getPresentWithLeastMagic());
    }

    @Test
    public void testGetPresent() {
        Gift gift1 = new Gift("gift1", 20);
        Gift gift2 = new Gift("gift2", 5);
        factory.createGift(gift1);
        factory.createGift(gift2);

        assertEquals(gift1, factory.getPresent("gift1"));
    }

    @Test
    public void testGetPresent_WhenNotFound() {
        assertNull(factory.getPresent("nonexistent"));
    }

    @Test
    public void testGetPresents() {
        Gift gift1 = new Gift("gift1", 20);
        Gift gift2 = new Gift("gift2", 5);
        factory.createGift(gift1);
        factory.createGift(gift2);

        Collection<Gift> presents = factory.getPresents();
        assertEquals(2, presents.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetPresents_Unmodifiable() {
        Gift gift1 = new Gift("gift1", 20);
        factory.createGift(gift1);

        Collection<Gift> presents = factory.getPresents();
        presents.add(new Gift("gift2", 5));
    }

}
