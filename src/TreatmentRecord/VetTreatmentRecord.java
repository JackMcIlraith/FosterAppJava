package TreatmentRecord;

import java.util.Date;

public class VetTreatmentRecord {
    private String vetName;
    private Date dateOfVisit;
    private final Date dateEntered = new Date(System.currentTimeMillis()); //time/date when the entry was created, for audit purposes
    private String reasonForVisit;
    private String treatmentGiven;

    public VetTreatmentRecord(String vetName, Date dateOfVisit, String reasonForVisit, String treatmentGiven) throws Exception {
        if(vetName.equals("")){
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

//Getters and Setters:

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public String getTreatmentGiven() {
        return treatmentGiven;
    }

    public void setTreatmentGiven(String treatmentGiven) {
        this.treatmentGiven = treatmentGiven;
    }
}
