package heroRepository;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroRepositoryTests { 
    private static final String HERO_NAME = "Dimitar";
    private HeroRepository heroRepository;
    private Hero hero;

    @Before 
    public void setUp() throws Exception {
        this.heroRepository = new HeroRepository();
        this.hero = new Hero(HERO_NAME, 10);
    }

    @Test(expected = NullPointerException.class) 
    public void testCreateHeroWithNameNull() {
        this.heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class) 
    public void testCreateHeroWithDuplicateName() {
        this.heroRepository.create(this.hero);
        this.heroRepository.create(this.hero);
    }

    @Test
    public void testCreateHeroSuccessfully() { 
        assertEquals(0,this.heroRepository.getCount());
        this.heroRepository.create(this.hero);
        assertEquals(1, this.heroRepository.getCount());
        Hero createdHero = this.heroRepository.getHero(HERO_NAME);
        assertEquals(createdHero.getName(), this.hero.getName());
        assertEquals(createdHero.getLevel(), this.hero.getLevel());
    }

    @Test
    public void testRemoveHeroSuccessfully() { 
        assertEquals(0,this.heroRepository.getCount());
        this.heroRepository.create(this.hero);
        assertEquals(1,this.heroRepository.getCount());
        this.heroRepository.remove(HERO_NAME);
        assertEquals(0,this.heroRepository.getCount());
        Hero removedHero = this.heroRepository.getHero(HERO_NAME); 
        assertNull(removedHero);
    }

    @Test
    public void testGetHeroWithHighestLevel() {
        Hero hero1 = new Hero("Martin", 3);
        Hero hero2 = new Hero("Ivan", 5);
        Hero hero3 = new Hero("Georgi", 2);

        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
        this.heroRepository.create(hero3);
        assertEquals(3,this.heroRepository.getHeroes().size()); 
        Hero highestHero = this.heroRepository.getHeroWithHighestLevel(); 
        assertEquals(highestHero.getName(),hero2.getName());
        assertEquals(highestHero.getLevel(),hero2.getLevel());       
    }
}