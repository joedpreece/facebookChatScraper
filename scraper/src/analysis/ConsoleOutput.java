package analysis;

import java.util.*;

import objects.*;

public class ConsoleOutput {

    /**
     * Prints the contributions users have made to a conversation to the console in descending order.
     * @param conversation the conversation to be checked
     */
    public static void printUserContributions(Conversation conversation) {
        ArrayList<String> output = new ArrayList<>();
        Set<Contribution> contributions = conversation.getContributions();
        Map<User, Integer> rankings = new HashMap<>();
        for (Contribution contribution : contributions) {
            User user = contribution.getUser();
            if (rankings.containsKey(user)) {
                rankings.put(user, rankings.get(user) + 1);
            } else {
                rankings.put(user, 1);
            }
        }
        for (Map.Entry<User, Integer> mapEntry : rankings.entrySet()) {
            User user = mapEntry.getKey();
            Integer integer = mapEntry.getValue();
            String outputLine = integer + "\t" + user.getName();
            output.add(outputLine);
        }
        Collections.sort(output);
        Collections.reverse(output);
        for (String string : output) {
            System.out.println(string);
        }
    }

    /**
     * Prints the total number of messages to the console.
     * @param conversation the conversation to be checked
     * @return the number of users, total contributions, and total messages
     */
    public static void printConversationInformation(Conversation conversation) {
        System.out.println("Conversation Information:");
        String format = "%-13s\t%5d\n";
        System.out.printf(format,"users", conversation.getUsers().size());
        System.out.printf(format,"contributions", conversation.getContributions().size());
        System.out.printf(format,"messages", conversation.getNumberOfMessages());
        System.out.println();
    }

    public static void printAllWordFrequency(Conversation conversation) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (Contribution contribution : conversation.getContributions()) {
            for (Message message : contribution.getMessages()) {
                if (message instanceof StringMessage) {
                    String[] words = ((StringMessage) message).getRawText().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                    for (String word : words) {
                        if (wordFrequency.containsKey(word)) {
                            wordFrequency.put(word, wordFrequency.get(word) + 1);
                        } else {
                            wordFrequency.put(word, 1);
                        }
                    }
                }
            }
        }
        String format = "%-20s\t%6d\n";
        wordFrequency.forEach((word, frequency) -> {
            System.out.printf(format, word, frequency);
        });
    }

    public static void printContributionPerDay(Conversation conversation) {
        Map<String, Integer> contributionTotal = new TreeMap<>();
        for (Contribution contribution : conversation.getContributions()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(contribution.getTimestamp());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String date = year+"-"+month+"-"+String.format("%02d", day);
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

}
