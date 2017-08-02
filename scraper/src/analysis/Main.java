package analysis;

import java.io.IOException;

import objects.Conversation;

public class Main {

    public static void main(String[] args) throws IOException {

        Conversation conversation = HTMLParser.parseHTMLFile("scraper/src/files/india.html");

        ConsoleOutput.printConversationInformation(conversation);
        ConsoleOutput.printUserContributions(conversation);
        //ConsoleOutput.printAllWordFrequency(conversation);
        ConsoleOutput.printContributionPerDay(conversation);
    }

}
