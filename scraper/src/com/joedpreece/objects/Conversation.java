package com.joedpreece.objects;

import com.joedpreece.analysis.Queries;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Joe on 31/07/17.
 */
public class Conversation {

    /**
     * The contributions in this conversation.
     */
    private Set<Contribution> contributions;

    /**
     * The users in this conversation.
     */
    private Set<User> users;

    /**
     * Constructs a new conversation from a set of contributions.
     * @param contributions the contributions associated with this conversation
     */
    public Conversation(Set<Contribution> contributions) {
        this.contributions = contributions;
        this.users = determineUsers();
        Queries.dataCache.put(this, new HashMap<>());
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

    public Set<Contribution> getContributions() {
        return contributions;
    }

    public Set<User> getUsers() {
        return users;
    }

}
