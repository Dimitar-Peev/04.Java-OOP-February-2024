package petStore;

import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class PetStoreTests { 
    private PetStore petStore;
    private Animal animal;

    @Before
    public void setUp() {
        this.petStore = new PetStore();
        this.animal = new Animal("Dog", 10, 200);
    }

    @Test(expected = UnsupportedOperationException.class) 
    public void test_GetAnimals_ShouldReturn_UnmodifiableList() {
        List<Animal> animals = petStore.getAnimals();
        animals.remove(1);
    }

    @Test
    public void test_GetCount_ShouldReturnSize() { 
        assertEquals(0, petStore.getCount());
        petStore.addAnimal(animal);
        assertEquals(1, this.petStore.getCount());
    }

    @Test
    public void test3() {
        petStore.addAnimal(animal);
        List<Animal> animals = petStore.findAllAnimalsWithMaxKilograms(10 + 10);
        assertTrue(animals.isEmpty());
    }

    @Test
    public void test4() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("Cat", 8, 50));
        List<Animal> animals = petStore.findAllAnimalsWithMaxKilograms(10 - 1);
        assertEquals(1, animals.size());
        assertEquals(animal.getSpecie(), animals.get(0).getSpecie());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddAnimal_ShouldThrow_WhenAnimallIsNull() {
        petStore.addAnimal(null);
    }

    @Test
    public void test_AddAnimal_ShouldIncreaseCount() {
        petStore.addAnimal(animal);
        assertEquals(1, petStore.getCount());
    }

    @Test
    public void test_getTheMostExpensiveAnimal_ShouldReturn_Null_WhenEmpty() {
        Animal animal = petStore.getTheMostExpensiveAnimal();
        assertNull(animal);
    }
 
    @Test
    public void test_getTheMostExpensiveAnimal_ShouldReturn_MostExpensiveAnimal() { 
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("Cat", 2, 20));
        Animal actualAnimal = petStore.getTheMostExpensiveAnimal();
        assertEquals(animal.getPrice(), actualAnimal.getPrice(), 0.00);
    }

    @Test
    public void test_findAllAnimalBySpecie_ShouldReturn_EmptyList(){
        List<Animal> animals = petStore.findAllAnimalBySpecie("Horse");
        assertTrue(animals.isEmpty());
    }
}

