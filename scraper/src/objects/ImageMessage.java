package objects;

/**
 * Created by joe on 31/07/17.
 */
public class ImageMessage extends Message {

    private String imageUrl;
    public String getImageUrl() {return this.imageUrl;}

    public ImageMessage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
