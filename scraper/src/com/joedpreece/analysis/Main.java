package com.joedpreece.analysis;

import java.io.IOException;
import java.sql.Connection;

import com.joedpreece.database.Connect;
import com.joedpreece.objects.Conversation;

public class Main {

    public static void main(String[] args) throws IOException {

        Connection databaseConnection = Connect.connectToDatabase();

        Conversation conversation = HTMLParser.parseHTMLFile("scraper/src/files/Claudia.html");

        ConsoleOutput.printConversationInformation(conversation);
        //ConsoleOutput.printUserContributions(conversation);
        //ConsoleOutput.printWordFrequency(conversation);
        ConsoleOutput.printEmojiFrequency(conversation);
        //ConsoleOutput.printUserContributionAgainstTime(conversation);
    }

}
