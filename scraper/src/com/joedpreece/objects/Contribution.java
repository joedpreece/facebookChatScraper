package com.joedpreece.objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * A contribution (sequential batch of messages) within a conversation.
 * @author J. Preece
 */
public class Contribution {

    /**
     * A list of individual messages in this contribution.
     */
    private ArrayList<Message> messages;

    /**
     * The timestamp of this contribution.
     */
    private Date timestamp;

    /**
     * The user of this contribution.
     */
    private User user;

    /**
     * Constructs a new contribution.
     * @param timestamp the timestamp of the contribution
     * @param user the user who made the contribution
     * @param messages the messages within the contribution
     */
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
