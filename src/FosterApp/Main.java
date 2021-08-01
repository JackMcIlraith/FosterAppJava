package FosterApp;
import java.util.ArrayList;
import java.util.Date;

import Animals.AnimalRecord;
import Animals.Sex;
import Animals.Species;
import Users.Admin;
import Users.Fosterer;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        Date date = new Date(System.currentTimeMillis());
        ArrayList<AnimalRecord> currentAnimals = new ArrayList<>();

        Admin Jack = new Admin("Jack", "jack@test.com", "this place");
        Admin Cat = new Admin("Cat", "cat@test.com", "this is Cat's place");

        currentAnimals.add(Jack.createAnimalRecord("New Kitty", date, "this is my new cat", Species.Cat, Sex.Male));
        currentAnimals.add(Jack.createAnimalRecord("Old Kitty", date, "this is my old cat", Species.Cat, Sex.Male));
        Jack.createNewVetTreatmentRecord(0,currentAnimals,"Village Vets",date , "This cat is the new one, and needs a check-up", "This is a healthy cat");
        currentAnimals.get(0).printVTR();
        Jack.createNewVetTreatmentRecord(0,currentAnimals,"City Vets",date , "The new cat is sick", "oh noe");
        currentAnimals.get(0).printVTR();

    }
}
