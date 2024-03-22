package UnitTesting_Exercises.p02_ExtendedDatabase;

import org.junit.*;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ExtendedDatabaseTest {
    private static final Person[] PEOPLE = new Person[]{
            new Person(1, "A"),
            new Person(2, "B"),
            new Person(3, "C"),
    };
    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(PEOPLE);
    }

    @Test
    public void when_correctElementsArePassed_then_createDatabaseInstance() {
        assertEquals(PEOPLE.length, database.getElements().length);
        assertArrayEquals(PEOPLE, database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsMoreThan16_then_exceptionThrown() throws OperationNotSupportedException {
        database = new Database(new Person[17]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsLessThan1_then_exceptionThrown() throws OperationNotSupportedException {
        new Database();
    }

    // add()
    @Test
    public void when_validElementsPassedToAdd_then_elementsIsAddedOnLastPosition() throws OperationNotSupportedException {
        Person expectedPerson = new Person(4, "D");
        database.add(expectedPerson);
        Person[] databaseElements = database.getElements();
        assertEquals(PEOPLE.length + 1, databaseElements.length);
        Person actualPerson = databaseElements[databaseElements.length - 1];
        assertEquals(expectedPerson.getId(), actualPerson.getId());
        assertEquals(expectedPerson.getUsername(), actualPerson.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_invalidElementPassedtoAdd_then_exceptionIsThrown() throws OperationNotSupportedException {
        database.add(null);
    }

    // remove()
    @Test
    public void when_remove_then_lastElementIsRemoved() throws OperationNotSupportedException {
        database.remove();
        Person[] databaseElements = database.getElements();
        assertEquals(PEOPLE.length - 1, databaseElements.length);
        Person expectedPerson = PEOPLE[PEOPLE.length - 2];
        Person actualPerson = databaseElements[databaseElements.length - 1];
        assertEquals(expectedPerson.getId(), actualPerson.getId());
        assertEquals(expectedPerson.getUsername(), actualPerson.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsIsEmptyAndRemove_then_exceptionIdThrown() throws OperationNotSupportedException {
        database = new Database(new Person(4, "G"));
        database.remove();
        database.remove();
    }

    // findByUsername
    @Test(expected = OperationNotSupportedException.class)
    public void when_usernameIsNull() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test
    public void when_usernameIsValid() throws OperationNotSupportedException {
        Person actualPerson = database.findByUsername("B");
        assertEquals(PEOPLE[1].getId(), actualPerson.getId());
        assertEquals(PEOPLE[1].getUsername(), actualPerson.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_findByUsernameOnEmptyArray_then_throwException() throws OperationNotSupportedException {
        database.remove();
        database.remove();
        database.remove();
        database.findByUsername("A");
    }

    // findById
    @Test(expected = OperationNotSupportedException.class)
    public void when_findByIdOnEmptyArray_then_throwException() throws OperationNotSupportedException {
        database.remove();
        database.remove();
        database.remove();
        database.findById(2);
    }

    @Test
    public void when_validIdPassed_then_returnPerson() throws OperationNotSupportedException {
        Person actualPerson = database.findById(2);
        assertEquals(PEOPLE[1].getId(),actualPerson.getId());
        assertEquals(PEOPLE[1].getUsername(),actualPerson.getUsername());
    }
}
