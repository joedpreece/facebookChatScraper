package com.joedpreece.objects;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joe on 31/07/17.
 */
public class StringMessage extends Message {

    private Element messageElement;
    private Map<String, Integer> emojis;
    private String text;

    public StringMessage(Element messageElement) {
        this.messageElement = messageElement;
        this.text = messageElement.text();
        this.emojis = listEmojis();
    }

    private Map<String, Integer> listEmojis() {
        Elements emojiElements = messageElement.getElementsByClass("_emReg");
        Map<String, Integer> emojis = new HashMap<>();
        for (Element emojiElement : emojiElements) {
            String emoji = emojiElement.attr("data-emoji");
            if (emojis.containsKey(emoji)) {
                emojis.put(emoji, emojis.get(emoji) + 1);
            } else {
                emojis.put(emoji, 1);
            }
        }
        return emojis;
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
}
