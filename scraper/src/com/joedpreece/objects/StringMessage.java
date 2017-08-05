package com.joedpreece.objects;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

/**
 * A text based message that contains words and emojis.
 */
public class StringMessage implements Message {

    /**
     * The HTML element of the message.
     */
    private Element messageElement;

    /**
     * The emojis in this message mapped to how many times they appear.
     */
    private Map<String, Integer> emojis;

    /**
     * The text in this message.
     */
    private String text;

    /**
     * Contructs a new string message.
     *
     * @param messageElement the
     */
    public StringMessage(Element messageElement) {
        this.messageElement = messageElement;
        this.text = messageElement.text();
        this.emojis = determineEmojis();
    }

    public Element getMessageElement() {
        return messageElement;
    }

    public Map<String, Integer> getEmojis() {
        return emojis;
    }

    public String getText() {
        return text;
    }

    /**
     * Determines the emojis in the message.
     *
     * @return the emojis in this massage mapped to the number of appearances
     */
    private Map<String, Integer> determineEmojis() {
        Elements emojiElements = messageElement.getElementsByClass("_emReg");
        Map<String, Integer> emojis = new HashMap<>();
        for (Element emojiElement : emojiElements) {
            String emoji = emojiElement.attr("data-emoji");
            if (emoji.equals(":p")) {
                emoji = ":P";
            }
            if (emojis.containsKey(emoji)) {
                emojis.put(emoji, emojis.get(emoji) + 1);
            } else {
                emojis.put(emoji, 1);
            }
        }
        return emojis;
    }

}
