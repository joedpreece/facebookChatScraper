package com.joedpreece.analysis;

import java.io.IOException;

import com.joedpreece.objects.Conversation;

public class Main {

    public static void main(String[] args) throws IOException {

        Conversation conversation = HTMLParser.parseHTMLFile("scraper/src/files/Archery.html");

        ConsoleOutput.printConversationInformation(conversation);
        ConsoleOutput.printUserContributions(conversation);
        ConsoleOutput.printWordFrequency(conversation, "morning");
        //ConsoleOutput.printContributionPerDay(conversation);
        ConsoleOutput.printEmojiFrequency(conversation);
    }

}
