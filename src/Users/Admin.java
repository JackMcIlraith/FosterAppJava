package Users;

import Animals.AnimalRecord;
import Animals.Sex;
import Animals.Species;
import Interfaces.AnimalObservers;
import TreatmentRecord.VetTreatmentRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Admin extends Fosterer implements AnimalObservers {
    private static AtomicInteger adminIdGenerator = new AtomicInteger(0);
    private final int adminID;

    public Admin(String name, String email, String address) {
        super(name, email, address);
        this.adminID = adminIdGenerator.getAndIncrement();
    }

    public AnimalRecord createAnimalRecord(String name, Date DOB, String description, Species species, Sex sex, boolean isFosterNeeded, boolean isReadyForAdoption){
        AnimalRecord newAnimal = new AnimalRecord(name, DOB, description, species, sex, isFosterNeeded, isReadyForAdoption);
        newAnimal.addObserver(this);
        newAnimal.setName(name); //confirm animal has been added successfully via observer notification
        return newAnimal;
    }

    //secondary method to allow for incomplete data
    public AnimalRecord createAnimalRecord(String name, Date DOB, String description, Species species, Sex sex){
        AnimalRecord newAnimal = new AnimalRecord(name, DOB, description, species, sex, false, false);
        newAnimal.addObserver(this);
        newAnimal.notifyObserver();
        return newAnimal;
    }

    public void createNewVetTreatmentRecord(int targetAnimalID, ArrayList<AnimalRecord> animalArray, String vetName, Date dateOfVisit, String reasonForVisit, String treatmentGiven) throws Exception {
        animalArray.get(targetAnimalID).createVetTreatmentRecord(vetName,dateOfVisit, reasonForVisit, treatmentGiven);
    }


    @Override
    public void update(AnimalRecord animal) {
        System.out.println("User " + this.name + " has been notified: There has been a change made to " + animal.getName());
    }
}
