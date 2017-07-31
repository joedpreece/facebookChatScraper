package objects;

public class Message {

	private String htmlMessage;
	public String getHtmlMessage() {return this.htmlMessage;}
	
	private String imageUrl;
	
	
	public Message(String htmlMessage, String imageUrl) {
		this.htmlMessage = htmlMessage;
		this.imageUrl = imageUrl;
	}
	
}
