package com.joedpreece.objects;

/**
 * An image sent as a message.
 * @author J. Preece
 */
public class ImageMessage implements Message {

    /**
     * The url of this image.
     */
    private String imageUrl;

    /**
     * Constructs a new image message.
     * @param imageUrl the url of the image
     */
    public ImageMessage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {return this.imageUrl;}

}
