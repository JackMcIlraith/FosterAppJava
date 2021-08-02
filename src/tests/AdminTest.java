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
import java.util.Observer;

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


    //TC1 - Creating Animal Records
    @Test
    public void Admin_createAnimalRecord_Test() throws Exception {
        //Act:
        createAnimalRecordTestArray.add(testAdmin.createAnimalRecord("TestAnimalName", date, "TestAnimal", Species.Other, Sex.Unknown, false, false));
        //Assert:
        System.out.println("As there is only one animal currently live on the array, we can simply compare the name of the animal on array index 0 to the created animal");
        Assert.assertEquals(createAnimalRecordTestArray.get(0).getName(), "TestAnimalName");
        Assert.assertNotEquals(createAnimalRecordTestArray.get(0).getName(), "TotallyDifferentNameJustToBeSure");
    }

    //TC2 - Create treatment record for Animal
    @Test
    public void Admin_createNewVetTreatmentRecord_Test() throws Exception {
        //Arrange
        createVTRTestArray.add(testAdmin.createAnimalRecord("TestAnimalName", date, "TestAnimal", Species.Other, Sex.Unknown, false, false));
        //Verify pre-condition (animal is present, but has 0 vet treatment records:
        System.out.println("There are currently " + createVTRTestArray.get(0).numberOfRecordsInVTR() + " records in the animals VTR");
        System.out.println("We expect zero, as this is a new animal");
        Assert.assertEquals(createVTRTestArray.get(0).numberOfRecordsInVTR(),0);
        //Act:
        System.out.println("Adding one vtr instance");
        testAdmin.createNewVetTreatmentRecord(0,createVTRTestArray, "VetName", date, "To Test", "Tests attempted");
        //Assert:
        System.out.println("There are currently " + createVTRTestArray.get(0).numberOfRecordsInVTR() + " records in the animals VTR");
        System.out.println("We expect 1, as this is a new animal");
        Assert.assertEquals(createVTRTestArray.get(0).numberOfRecordsInVTR(),1);
    }


    //TC3 - Test observer notification
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