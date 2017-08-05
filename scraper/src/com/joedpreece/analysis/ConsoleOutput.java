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
        Map<User, Integer> contributions = Queries.userContributions(conversation);
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
        Map<String, Integer> wordFrequency = Queries.wordFrequencySpecific(conversation, words);
        String format = "%-20s\t%6d\n";
        wordFrequency.forEach((word, frequency) -> {
            System.out.printf(format, word, frequency);
        });
        System.out.println();
    }

    public static void printUserContributionAgainstMonth(Conversation conversation) {
        System.out.println("User Contributions Over Time:");
        Table<YearMonth, User, Integer> table = Queries.userContributionAgainstMonth(conversation);
        ArrayList<User> users = new ArrayList<>();
        printTable(table);
        System.out.println();
    }

    public static void printEmojiFrequency(Conversation conversation) {
        System.out.println("Emoji Frequencies:");
        Map<String, Integer> emojiFrequencies = Queries.emojiFrequency(conversation);
        String format = "%-6s\t%6d\n";
        emojiFrequencies.forEach((emoji, frequency) -> {
            System.out.printf(format, emoji, frequency);
        });
        System.out.println();
    }

    public static void printEmojiAgainstUser(Conversation conversation) {
        System.out.println("Emoji Usage Per User:");
        Table<User, String, Integer> table = Queries.emojiAgainstUser(conversation, 10, 10);
        printTable(table);
        System.out.println();
    }

    /**
     * Prints a table.
     *
     * @param table
     * @param <R>
     * @param <C>
     * @param <V>
     */
    public static <R, C, V> void printTable(Table<R, C, V> table) {
        for (Object R : table.columnKeySet()) {
            System.out.print("\t" + R);
        }
        System.out.println();
        for (Map.Entry<R, Map<C, V>> row : table.rowMap().entrySet()){
            Map<C, V> cells = row.getValue();
            System.out.print(row.getKey());
            for (Map.Entry<C, V> cell : cells.entrySet()) {
                System.out.print("\t" + cell.getValue());
            }
            System.out.println();
        }
    }

}
