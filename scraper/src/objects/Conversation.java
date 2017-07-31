package objects;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Joe on 31/07/17.
 */
public class Conversation {

    private Set<Contribution> contributions;
    public Set<Contribution> getContributions() {return this.contributions;}

    private Set<User> users;
    public Set<User> getUsers() {return this.users;}

    public Conversation(Set<Contribution> contributions) {
        this.contributions = contributions;
        this.users = determineUsers();
    }

    /**
     * Determines the users in this conversation.
     * @return a set of all of the users in the conversation
     */
    private Set<User> determineUsers() {
        Set<User> users = new HashSet<>();
        for (Contribution contribution : this.contributions) {
            users.add(contribution.getUser());
        }
        return users;
    }

    /**
     * Gets the total number of messages in the conversation.
     * @return the total number of messages in the conversation.
     */
    public Integer getNumberOfMessages() {
        int runningTotal = 0;
        for (Contribution contribution : this.contributions) {
            runningTotal = runningTotal + contribution.getMessages().size();
        }
        return runningTotal;
    }

}
