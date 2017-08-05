package com.joedpreece.objects;

/**
 * A user who can contribute to conversations.
 *
 * @author J. Preece
 */
public class User implements Comparable<User> {

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The url of the user.
     */
    private String url;

    /**
     * Constructs a new user.
     *
     * @param name the name of the user
     * @param url  the url of the user
     */
    public User(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int compareTo(User user) {
        String thisName = this.name;
        String otherName = user.getName();
        return thisName.compareTo(otherName);
    }

    @Override
    public int hashCode() {
        if (!url.isEmpty()) {
            return this.url.hashCode();
        } else {
            return this.name.hashCode();
        }
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof User) && (((User) o).getName().equals(this.name));
    }

    @Override
    public String toString() {
        return name;
    }
}