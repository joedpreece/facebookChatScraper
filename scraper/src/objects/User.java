package objects;

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
        return this.url.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}