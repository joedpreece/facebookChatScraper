package com.joedpreece.analysis;

import java.util.*;

import com.joedpreece.objects.*;

public class ConsoleOutput {

    /**
     * Prints the contributions users have made to a conversation to the console in descending order.
     *
     * @param conversation the conversation to be checked
     */
    public static void printUserContributions(Conversation conversation) {
        System.out.println("User Contributions:");
        Map<String, Integer> contributions = Queries.determineUserContributions(conversation);
        String format = "%-20s\t%6d\n";
        contributions.forEach((k, v) -> {
            System.out.printf(format, k, v);
        });
        System.out.println();
    }

    /**
     * Prints the total number of messages to the console.
     *
     * @param conversation the conversation to be checked
     * @return the number of users, total contributions, and total messages
     */
    public static void printConversationInformation(Conversation conversation) {
        System.out.println("Conversation Information:");
        String format = "%-13s\t%5d\n";
        System.out.printf(format, "users", conversation.getUsers().size());
        System.out.printf(format, "contributions", conversation.getContributions().size());
        System.out.printf(format, "messages", conversation.getNumberOfMessages());
        System.out.println();
    }

    public static void printWordFrequency(Conversation conversation, String... words) {
        System.out.println("Word Frequencies:");
        Map<String, Integer> wordFrequency = Queries.determineWordFrequencies(conversation, words);
        String format = "%-20s\t%6d\n";
        wordFrequency.forEach((word, frequency) -> {
            System.out.printf(format, word, frequency);
        });
        System.out.println();
    }

    public static void printContributionPerDay(Conversation conversation) {
        Map<String, Integer> contributionTotal = new TreeMap<>();
        for (Contribution contribution : conversation.getContributions()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(contribution.getTimestamp());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String date = year + "-" + month + "-" + String.format("%02d", day);
            if (contributionTotal.containsKey(date)) {
                contributionTotal.put(date, contributionTotal.get(date) + 1);
            } else {
                contributionTotal.put(date, 1);
            }
        }
        String format = "%-10s\t%6d\n";
        contributionTotal.forEach((date, contributions) -> {
            System.out.printf(format, date, contributions);
        });
    }

    public static void printEmojiFrequency(Conversation conversation) {
        System.out.println("Emoji Frequencies:");
        Map<String, Integer> emojiFrequencies = Queries.getEmojiFrequencies(conversation);
        String format = "%-6s\t%6d\n";
        emojiFrequencies.forEach((emoji, frequency) -> {
            System.out.printf(format, emoji, frequency);
        });
        System.out.println();
    }

}
