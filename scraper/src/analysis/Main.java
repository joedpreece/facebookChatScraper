package analysis;

import java.io.IOException;
import java.util.Set;

import objects.Contribution;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Set<Contribution> contributions = HTMLParser.parseHTMLFile("scraper/src/files/india.html");
		
		System.out.println("Total contributions: " + Methods.totalContributions(contributions));
		System.out.println("Total messages: " + Methods.totalMessages(contributions));
		
		System.out.println(Methods.listNumberOfContributionsPerUser(contributions)); 
		
		
	}
	
}
