package Users;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Fosterer extends User {
    //All class details set to private to prevent direct modification (encapsulation)
    private static AtomicInteger idGenerator = new AtomicInteger(0); //global id variable to increment as users are added
    private final int fostererID;
    private boolean isApprovedToFoster;
    private boolean isAvailableToFoster;

//Constructors:
    public Fosterer(String name, String email, String address) {
        //Construct with user defined values
        this.name = name;
        this.email= email;
        this.address = address;
        //Construct with default values
        this.dateJoined = new Date(System.currentTimeMillis());
        this.fostererID = idGenerator.getAndIncrement();
        this.isApprovedToFoster = false;
        this.isAvailableToFoster = false;
    }

//return or change foster related statuses:
    public boolean isApprovedToFoster() {
        return isApprovedToFoster;
    }

    public void setApprovedToFoster(boolean approvedToFoster) {
        isApprovedToFoster = approvedToFoster;
    }

    public boolean isAvailableToFoster() {
        return isAvailableToFoster;
    }

    public void setAvailableToFoster(boolean availableToFoster) {
        isAvailableToFoster = availableToFoster;
    }
}
