package com.joedpreece.objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * A contribution (sequential batch of messages) within a conversation.
 */
public class Contribution {

    private ArrayList<Message> messages;

    private Date timestamp;

    private User user;

    public Contribution(Date timestamp, User user, ArrayList<Message> messages) {
        this.timestamp = timestamp;
        this.user = user;
        this.messages = messages;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }



}
