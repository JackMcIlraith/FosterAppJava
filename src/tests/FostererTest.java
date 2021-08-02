package tests;

import Users.Fosterer;
import org.junit.Assert;
import org.junit.Test;

public class FostererTest {

    //TC1 - test for invalid name:
    @Test(expected = Exception.class)
    public void TC1_Fosterer_InvalidUserName_Test() throws Exception {
        //Arrange
        //Act
        Fosterer jimmy = new Fosterer("", "testemail@test.com", "Little House on the Prairie");
        //Assert
            //Assertion done in @test decorator; expected result of test is an Exception
    }

    //TC2 - test for invalid email:
    @Test(expected = Exception.class)
    public void TC2_Fosterer_InvalidEmail_Test() throws Exception {
        //Arrange
        //Act
        Fosterer jimmy = new Fosterer("Jimmy", "", "Little House on the Prairie");
        //Assert
        //Assertion done in @test decorator; expected result of test is an Exception
    }

    //TC3 - id incremental and unique
    @Test
    public void TC3_Fosterer_UniqueIncrementalID_Test() throws Exception {
        //Arrange
        Fosterer jimmy = new Fosterer("Jimmy", "testemail@test.com", "Little House on the Prairie");
        Fosterer timmy = new Fosterer("Timmy", "testemail2@test.com", "Nextdoor");
        //Act
        int jimmyID = jimmy.getFostererID();
        int timmyID = timmy.getFostererID();
            //debug check:
            System.out.println("Jimmy ID:" + jimmyID);
            System.out.println("Timmy ID:" + timmyID);
        //Assert
        Assert.assertNotEquals(timmyID,jimmyID); //ensure that they are both unique
        Assert.assertEquals(timmyID,jimmyID+1);//expected timmy ID
    }
}