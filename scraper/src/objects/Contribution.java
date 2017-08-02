package objects;

import java.util.ArrayList;
import java.util.Date;

public class Contribution {

    private ArrayList<Message> messages;
    public ArrayList<Message> getMessages() {return this.messages;}

    private Date timestamp;
    public Date getTimestamp() {return this.timestamp;}

    private User user;
    public User getUser() {return this.user;}

    public Contribution(Date timestamp, User user, ArrayList<Message> messages) {
        this.timestamp = timestamp;
        this.user = user;
        this.messages = messages;
    }

    @Override
    public String toString() {
        return user.toString();
    }

}
