package objects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
        updateUserData();
    }

    private Set<User> determineUsers() {
        Set<User> users = new HashSet<>();
        for (Contribution contribution : this.contributions) {
            users.add(contribution.getUser());
        }
        return users;
    }

    /**
     * Updates the number of contribution and messages data for all users within the conversation.
     */
    private void updateUserData() {
        //TODO Write this method
    }

    public Integer getNumberOfMessages() {
        int runningTotal = 0;
        for (Contribution contribution : this.contributions) {
            runningTotal = runningTotal + contribution.getMessages().size();
        }
        return runningTotal;
    }

}
