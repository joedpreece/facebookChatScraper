package com.joedpreece.objects;

public class User {

    private String name;

    private String url;

    public User(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof User) && (((User) o).getUrl().equals(this.url));
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
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

}