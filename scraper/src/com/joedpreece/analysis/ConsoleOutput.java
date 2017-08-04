package com.joedpreece.analysis;

import java.time.YearMonth;
import java.util.*;

import com.google.common.collect.Table;
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
        String format = "%-30s\t%6d\n";
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

    public static void printUserContributionAgainstTime(Conversation conversation) {
        System.out.println("User Contributions Over Time:");
        Table<YearMonth, User, Integer> table = Queries.userContributionAgainstTime(conversation);
        ArrayList<User> users = new ArrayList<>();
        for (User user : table.columnKeySet()) {
            System.out.print("\t" + user);
            users.add(user);
        }
        System.out.println();
        for (Map.Entry<YearMonth, Map<User, Integer>> row : table.rowMap().entrySet()){
            Map<User, Integer> userStats = row.getValue();
            YearMonth month = row.getKey();
            System.out.print(month);
            int i = 0;
            for (Map.Entry<User, Integer> userStat : userStats.entrySet()) {
                while (!users.get(i).equals(userStat.getKey())) {
                    System.out.print("\t" + 0);
                    i++;
                }
                System.out.print("\t" + userStat.getValue());
                i++;
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printEmojiFrequency(Conversation conversation) {
        System.out.println("Emoji Frequencies:");
        Map<String, Integer> emojiFrequencies = Queries.determineEmojiFrequencies(conversation);
        String format = "%-6s\t%6d\n";
        emojiFrequencies.forEach((emoji, frequency) -> {
            System.out.printf(format, emoji, frequency);
        });
        System.out.println();
    }



}
