package objects;

public class User {
	
	private String name;
	public String getName() {return this.name;}
	
	private String url;
	public String geturl() {return this.url;}
	
	public User(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof User) && (((User) o).geturl().equals(this.url));
	}
	
    @Override
    public int hashCode() {
        return this.url.hashCode();
    }
    
    @Override
    public String toString() {
    	return name;
    }
	
}
