package TreatmentRecord;

import java.util.Date;

public class VetTreatmentRecord {
    private String vetName;
    private Date dateOfVisit;
    private final Date dateEntered = new Date(System.currentTimeMillis()); //time/date when the entry was created, for audit purposes
    private String reasonForVisit;
    private String treatmentGiven;

    public VetTreatmentRecord(String vetName, Date dateOfVisit, String reasonForVisit, String treatmentGiven) throws Exception {
        if(vetName == ""){
            throw new Exception("invalid name of Vet");
        }
        else if(dateEntered.before(dateOfVisit)){
            throw new Exception("Invalid date: are you from the future?");
        }
        else {
            this.vetName = vetName;
            this.dateOfVisit = dateOfVisit;
            this.reasonForVisit = reasonForVisit;
            this.treatmentGiven = treatmentGiven;
        }
    }

    public String getVetTreatmentRecord(){
        return "Vet: " + vetName + "\n" +
                "Date of visit: " + dateOfVisit + "\n" +
                "Reason of visit: " + reasonForVisit + "\n" +
                "Treatment given: " + treatmentGiven;
    }

    public void printVetTreatmentRecord(){
        System.out.println( "Vet: " + vetName + "\n" +
                "Date of visit: " + dateOfVisit + "\n" +
                "Reason of visit: " + reasonForVisit + "\n" +
                "Treatment given: " + treatmentGiven);
    }
}
