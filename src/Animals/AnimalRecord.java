package Animals;

import Interfaces.AnimalObservers;
import Interfaces.AnimalSubject;
import Photos.AnimalPhotos;
import TreatmentRecord.VetTreatmentRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AnimalRecord implements AnimalSubject {
    private static AtomicInteger animalIdGenerator = new AtomicInteger(0);
    private final int animalID;
    private String name;
    private Date DOB; //Date of Birth or best estimate
    private String description;
    private final Species species; //species is final, as this physically cannot change
    private Sex sex; //left variable, as mis-gendering of animals is common
    private boolean isFosterNeeded;
    private boolean isReadyForAdoption;
    private ArrayList<AnimalPhotos> photoAlbum = new ArrayList<>();
    private ArrayList<VetTreatmentRecord> vtr = new ArrayList<>();//vtr acronym for vet treatment record
    private List<AnimalObservers> animalObservers = new ArrayList<>();

    public AnimalRecord(String name, Date DOB, String description, Species species, Sex sex, boolean isFosterNeeded, boolean isReadyForAdoption) throws Exception {
        if(name.equals("")){
            throw new Exception("Invalid name");
        } else if(DOB.after(new Date(System.currentTimeMillis()))){
            throw new Exception("Invalid DOB");
        } else if(species != Species.Cat && species != Species.Dog && species != Species.Other && species != Species.Rabbit){
            throw new Exception("Invalid species");

        }
        this.animalID = animalIdGenerator.getAndIncrement();
        this.name = name;
        this.DOB = DOB;
        this.description = description;
        this.species = species;
        this.sex = sex;
        this.isFosterNeeded = isFosterNeeded;
        this.isReadyForAdoption = isReadyForAdoption;
    }


//Photo Album Methods
    public ArrayList<AnimalPhotos> getPhotoAlbum() {
        return photoAlbum;
    }

//VTR Methods
    public ArrayList<VetTreatmentRecord> getVrt() {
        return vtr;
    }

    public int numberOfRecordsInVTR(){
        return vtr.size();
    }

    public void createVetTreatmentRecord(String vetName, Date dateOfVisit, String reasonForVisit, String treatmentGiven) throws Exception {
        VetTreatmentRecord newRecord = new VetTreatmentRecord(vetName,dateOfVisit, reasonForVisit, treatmentGiven);
        vtr.add(newRecord);
        notifyObserver();
    }
    public void printVTR(){
        vtr.forEach(x -> x.printVetTreatmentRecord());
    }

//Getters & Setters

    public void setName(String name){
        this.name = name;
        notifyObserver();
    }

    public String getName(){
        return this.name;
    }

//Observer Methods

    @Override
    public void addObserver(AnimalObservers observer){
        this.animalObservers.add(observer);
    }

    @Override
    public void removeObserver(AnimalObservers observer){
        this.animalObservers.remove(observer);
    }

    @Override
    public void notifyObserver(){
        animalObservers.forEach(x -> x.update(this));
    }

    public int getAnimalID() {
        return animalID;
    }
}

