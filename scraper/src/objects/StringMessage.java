package objects;

import java.util.Map;

/**
 * Created by Joe on 31/07/17.
 */
public class StringMessage extends Message {

    private String htmlMessage;
    public String getHtmlMessage() {return this.htmlMessage;}

    public StringMessage(String htmlMessage) {
        this.htmlMessage = htmlMessage;
    }
}
