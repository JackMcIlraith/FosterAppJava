package tests;

import Animals.AnimalRecord;
import Animals.Sex;
import Animals.Species;
import Users.Admin;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;


public class AdminTest {

    //Animal database for current instance of application
    ArrayList<AnimalRecord> createAnimalRecordTestArray = new ArrayList<>();
    ArrayList<AnimalRecord> createVTRTestArray = new ArrayList<>();

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Date date = new Date(System.currentTimeMillis());
    private AnimalRecord testAnimal = new AnimalRecord("Testy", date, "This is a test animal", Species.Other, Sex.Unknown, false, false);
    private Admin testAdmin = new Admin("Testo", "test@test.com", "This House");

    public AdminTest() throws Exception {
    }

    @Before //allows for comparison of system prints
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


//tests: Creating Animal Records
    @Test
    public void createAnimalRecord() {
        //Act:
        createAnimalRecordTestArray.add(testAdmin.createAnimalRecord("TestAnimalName", date, "TestAnimal", Species.Other, Sex.Unknown, false, false));
        //Assert:
        System.out.println("As there is only one animal currently live on the array, we can simply compare the name of the animal on array index 0 to the created animal");
        Assert.assertEquals(createAnimalRecordTestArray.get(0).getName(), "TestAnimalName");
        Assert.assertNotEquals(createAnimalRecordTestArray.get(0).getName(), "TotallyDifferentNameJustToBeSure");
    }

//tests: Vet Treatment Records
    @Test
    public void createNewVetTreatmentRecord() {
        createVTRTestArray.add(testAdmin.createAnimalRecord("TestAnimalName", date, "TestAnimal", Species.Other, Sex.Unknown, false, false));

    }


//tests: Observer Notification
    @Test
    public void update() {
        //Arrange:
        testAnimal.addObserver(testAdmin);
        //Act:
        testAnimal.notifyObserver();
        //Assert:
        assertEquals("User Testo has been notified: There has been a change made to Testy", outputStreamCaptor.toString().trim());
    }
}