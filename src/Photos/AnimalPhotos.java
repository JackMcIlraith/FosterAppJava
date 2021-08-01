package Photos;

import java.awt.*;
import java.util.Date;

public class AnimalPhotos {
    private final Date uploadDate = new Date(System.currentTimeMillis());
    private Image photo; //placeholder for photo data member
    private String description;

    public AnimalPhotos(Image photo) {
        this.photo = photo;
    }

    public void addDescription(String description){
        this.description = description;
    }


}
