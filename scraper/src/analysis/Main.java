package analysis;

import java.io.IOException;
import java.util.Set;

import objects.Contribution;
import objects.Conversation;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Conversation conversation = HTMLParser.parseHTMLFile("scraper/src/files/india.html");
		
		System.out.println("Total contributions: " + conversation.getContributions().size());
		System.out.println("Total messages: " + conversation.getNumberOfMessages());
		
		System.out.println(Methods.rankByUseContribution(conversation));
		
		
	}
	
}
