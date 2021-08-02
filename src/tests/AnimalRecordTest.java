package tests;

import Animals.AnimalRecord;
import Animals.Sex;
import Animals.Species;
import TreatmentRecord.VetTreatmentRecord;
import Users.Admin;
import Users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.Assert.*;

public class AnimalRecordTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Date date = new Date(System.currentTimeMillis());
    private AnimalRecord testAnimal = new AnimalRecord("Testy", date, "This is a test animal", Species.Other, Sex.Unknown, false, false);
    private Admin testAdmin = new Admin("Testo", "test@test.com", "This House");
    private VetTreatmentRecord testRecord = new VetTreatmentRecord("TestVet", date, "This is a test", "a test treatment was recorded");

    public AnimalRecordTest() throws Exception {
    }

    @Before //allows for comparison of system prints
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void createVetTreatmentRecord() throws Exception {
        //Arrange:
        testAnimal.createVetTreatmentRecord("TestVet", date, "This is a test", "a test treatment was recorded");
        //Act:
        VetTreatmentRecord returnedRecord = testAnimal.getVrt().get(0);
        //Assert:
        Assert.assertEquals(returnedRecord.getVetName(), "TestVet");
        Assert.assertEquals(returnedRecord.getDateOfVisit(), date);
        Assert.assertEquals(returnedRecord.getReasonForVisit(), "This is a test");
        Assert.assertEquals(returnedRecord.getTreatmentGiven(), "a test treatment was recorded");
    }


    @Test
    public void numberOfRecordsInVTR() throws Exception {
        //Arrange:
        AnimalRecord newTestAnimal = new AnimalRecord("Testy2", date, "This is another test animal", Species.Other, Sex.Unknown, false, false);
        //Act:
        newTestAnimal.createVetTreatmentRecord("TestVet", date, "This is a test", "a test treatment was recorded");
        newTestAnimal.createVetTreatmentRecord("TestVet", date, "This is a second test", "a test treatment was recorded");
        newTestAnimal.createVetTreatmentRecord("TestVet", date, "This is a third test", "a test treatment was recorded");
        //Assert:
            //we added 3 records, so we assume that we will have 3 recordes returned:
        Assert.assertEquals(newTestAnimal.numberOfRecordsInVTR(),3);
    }



    @Test
    public void printVTR() throws Exception {
        //Arrange:
        AnimalRecord newTestAnimal = new AnimalRecord("Testy2", date, "This is another test animal", Species.Other, Sex.Unknown, false, false);
        //Act:
        newTestAnimal.createVetTreatmentRecord("TestVet", date, "This is a test", "a test treatment was recorded");
        newTestAnimal.printVTR();
        //Assert:
        assertEquals("Vet: TestVet\n" +
                "Date of visit: " + date + "\n" +
                "Reason of visit: This is a test\n" +
                "Treatment given: a test treatment was recorded", outputStreamCaptor.toString().trim());
    }


    @Test
    public void Animal_ObserverPattern_addObserver_notifyObserver_removeObserver_Test() throws Exception {
    //Arrange:
        Admin newTestAdmin = new Admin("Testo", "test@test.com", "This House");
        AnimalRecord newTestAnimal = new AnimalRecord("Testy", date, "This is a test animal", Species.Other, Sex.Unknown, false, false);
    //Test notification with no observers
    //Assert 1:
        //notify when there are no observers:
        newTestAnimal.notifyObserver();
        assertEquals("", outputStreamCaptor.toString().trim());
    //Test: addObserver and notifyObserver
    //Arrange:
        newTestAnimal.addObserver(newTestAdmin);
    //Act:
        newTestAnimal.notifyObserver();
    //Assert:
        assertEquals("User Testo has been notified: There has been a change made to Testy", outputStreamCaptor.toString().trim());
    //Test: Adding second Observer:
    //Arrange:

        Admin newTestAdmin2 = new Admin("Testo2", "test@test.com", "This House");
        newTestAnimal.addObserver(newTestAdmin2);
    //Act:
        newTestAnimal.notifyObserver();
    //Assert:
        Assert.assertEquals("User Testo has been notified: There has been a change made to Testy\r\n" +
        "User Testo has been notified: There has been a change made to Testy\r\n" +
        "User Testo2 has been notified: There has been a change made to Testy",
                outputStreamCaptor.toString().trim());

    }

    //test for invalid date of birth:
    @Test(expected = Exception.class)
    public void AnimalRecord_InvalidBirthDate_Test() throws Exception {
        //Arrange
        Date dateOfBirthAttempted = new Date(System.currentTimeMillis() + 100000000);
        Date actualDate = new Date(System.currentTimeMillis());
        //Act
        System.out.println("Input DOB" + dateOfBirthAttempted);
        System.out.println("actual date:" + actualDate);
        //Assert
        AnimalRecord testRecord = new AnimalRecord("Test", dateOfBirthAttempted, "Should fail", Species.Other, Sex.Unknown, false, false);
        //Assertion done in @test decorator; expected result of test is an Exception
    }

    //test for invalid Species:
    @Test(expected = Exception.class)
    public void AnimalRecord_InvalidSpecies_Test() throws Exception {
        //Arrange
        //Act

        //Assert
        AnimalRecord testRecord = new AnimalRecord("Test", date, "Should fail", null, null, false, false);
        //Assertion done in @test decorator; expected result of test is an Exception
    }

    //test for unique ID's:
    @Test
    public void AnimalRecord_UniqueID_Test() throws Exception {
        //Arrange
        AnimalRecord testRecord = new AnimalRecord("Test", date, "Animal", Species.Other, Sex.Unknown, false, false);
        AnimalRecord newTestRecord = new AnimalRecord("Test2", date, "Animal", Species.Other, Sex.Unknown, false, false);
        AnimalRecord newestTestRecord = new AnimalRecord("Test3", date, "Animal", Species.Other, Sex.Unknown, false, false);
        //Assert
        System.out.println(testRecord.getAnimalID());
        System.out.println(newTestRecord.getAnimalID());
        System.out.println(newestTestRecord.getAnimalID());
        Assert.assertNotEquals(newTestRecord.getAnimalID(),testRecord.getAnimalID());
        Assert.assertNotEquals(newTestRecord.getAnimalID(),newestTestRecord.getAnimalID());
        Assert.assertNotEquals(testRecord.getAnimalID(),newestTestRecord.getAnimalID());

    }
}