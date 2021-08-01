package tests;

import TreatmentRecord.VetTreatmentRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class VetTreatmentRecordTest {

    @Before
    public void setUp() throws Exception {
        Date date = new Date(System.currentTimeMillis());
        VetTreatmentRecord testRecord = new VetTreatmentRecord("Test Vet", date, "Test visit", "We tested this animal for tests");
    }
    //test for invalid name:
    @Test(expected = Exception.class)
    public void VetTreatmentRecord_InvalidVetName_Test() throws Exception {
        //Arrange
        Date date = new Date(System.currentTimeMillis());
        //Act
        VetTreatmentRecord newRecord = new VetTreatmentRecord("", date, "Test visit", "We tested this animal for tests");
        //Assert
            //Assertion done in @test decorator; expected result of test is an Exception
    }

    //test for valid name:
    @Test
    public void VetTreatmentRecord_ValidVetName_Test() throws Exception {
        //Arrange
        Date date = new Date(System.currentTimeMillis());
        //Act
        VetTreatmentRecord newRecord = new VetTreatmentRecord("Testo-Vet", date, "Test visit", "We tested this animal for tests");
        //Assert
        //Assertion done in @test decorator; expected result of test is an Exception
        Assert.assertEquals(newRecord.getVetTreatmentRecord(),("Vet: Testo-Vet" + "\n" +
                "Date of visit: " + date + "\n" +
                "Reason of visit: Test visit" + "\n" +
                "Treatment given: We tested this animal for tests"));
        //print of visual debug:
        System.out.print("Original text:" +"\n");
        System.out.println(newRecord.getVetTreatmentRecord());
        System.out.println();
        System.out.print("Expected text:" +"\n");
        System.out.println("Vet: Testo-Vet" + "\n" +
                "Date of visit: " + date + "\n" +
                "Reason of visit: Test visit" + "\n" +
                "Treatment given: We tested this animal for tests");
    }

    //test for invalid date of treatment:
    @Test(expected = Exception.class)
    public void VetTreatmentRecord_InvalidVetDate_Test() throws Exception {
        //Arrange
        Date dateOfVisit = new Date(System.currentTimeMillis() + 100000000);
        Date actualDate = new Date(System.currentTimeMillis());
        //Act
        System.out.println("date of test:" + dateOfVisit);
        System.out.println("actual date:" + actualDate); 
        VetTreatmentRecord newRecord = new VetTreatmentRecord("Good Times Vet", dateOfVisit, "Test visit", "We tested this animal for tests");
        //Assert
        //Assertion done in @test decorator; expected result of test is an Exception
    }

    @Test
    public void getVetTreatmentRecord() {
    }

    @Test
    public void printVetTreatmentRecord() {
    }
}