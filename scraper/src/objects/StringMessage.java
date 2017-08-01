package objects;

/**
 * Created by Joe on 31/07/17.
 */
public class StringMessage extends Message {

    private String htmlText;
    private String rawText;

    public StringMessage(String htmlText, String rawText) {
        this.htmlText = htmlText;
        this.rawText = rawText;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public String getRawText() {
        return rawText;
    }
}
