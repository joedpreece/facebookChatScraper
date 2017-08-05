package com.joedpreece.analysis;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import com.joedpreece.objects.*;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;

/**
 * A class of methods to query the data stored in conversation objects.
 *
 * @author J. Preece
 */
public class Queries {

    /**
     * A cache of data that has already been checked, in order to improve performance.
     */
    public static Map<Conversation, Map<String, Object>> dataCache = new HashMap<>();

    /**
     * Determines the number of contributions each user of a conversation has made.
     *
     * @param conversation the conversation to be checked
     * @return a map of the users' names against the number of contributions
     */
    public static Map<User, Integer> userContributions(Conversation conversation) {
        String methodSerialNumber = "N9TT-9G0A-B7FQ-RANC";
        if (dataCache.get(conversation).containsKey(methodSerialNumber)) {
            return (Map<User, Integer>) dataCache.get(conversation).get(methodSerialNumber);
        }
        Map<User, Integer> rankings = new HashMap<>();
        Map<User, Integer> output = new HashMap<>();
        for (Contribution contribution : conversation.getContributions()) {
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
            output.put(user, integer);
        }
        Map<User, Integer> sorted = sortMapByValue(output);
        dataCache.get(conversation).put(methodSerialNumber, sorted);
        return sorted;
    }

    /**
     * Retrieves the frequencies of all words used in a conversation.
     *
     * @param conversation the conversation to be checked
     * @return a map of each word against the number of times it appears
     */
    private static Map<String, Integer> wordFrequencyAll(Conversation conversation) {
        String methodSerialNumber = "QK6A-JI6S-7ETR-0A6C";
        if (dataCache.get(conversation).containsKey(methodSerialNumber)) {
            return (Map<String, Integer>) dataCache.get(conversation).get(methodSerialNumber);
        }
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (Contribution contribution : conversation.getContributions()) {
            for (Message message : contribution.getMessages()) {
                if (message instanceof StringMessage) {
                    String[] messageWords = ((StringMessage) message).getText().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                    for (String word : messageWords) {
                        if (wordFrequency.containsKey(word)) {
                            wordFrequency.put(word, wordFrequency.get(word) + 1);
                        } else {
                            wordFrequency.put(word, 1);
                        }
                    }
                }
            }
        }
        Map<String, Integer> sortedMap = sortMapByValue(wordFrequency);
        dataCache.get(conversation).put(methodSerialNumber, sortedMap);
        return sortedMap;
    }

    /**
     * Determines the frequency of specific words, or all words if argument is left blank.
     *
     * @param conversation the conversation to be checked
     * @param words the words to be checked
     * @return a map of the words against the number of times they appears
     */
    public static Map<String, Integer> wordFrequencySpecific(Conversation conversation, String... words) {
        Map<String, Integer> allWordFrequencies = wordFrequencyAll(conversation);
        Map<String, Integer> wordFrequencies = new HashMap<>();
        if (words.length == 0) {
            return allWordFrequencies;
        } else {
            for (String word : words) {
                if (allWordFrequencies.containsKey(word)) {
                    wordFrequencies.put(word, allWordFrequencies.get(word));
                }
            }
            return wordFrequencies;
        }
    }

    /**
     * Determines the frequency of emojies in a conversation.
     *
     * @param conversation the conversation to be checked
     * @return a map of the string emoji against the number of times it has been used
     */
    public static Map<String, Integer> emojiFrequency(Conversation conversation) {
        String methodSerialNumber = "M5R5-GSSR-73U8-HP5M";
        if (dataCache.get(conversation).containsKey(methodSerialNumber)) {
            return (Map<String, Integer>) dataCache.get(conversation).get(methodSerialNumber);
        }
        Map<String, Integer> emojis = new HashMap<>();
        for (Contribution contribution : conversation.getContributions()) {
            for (Message message : contribution.getMessages()) {
                if (message instanceof StringMessage) {
                    Map<String, Integer> messageEmojis = ((StringMessage) message).getEmojis();
                    messageEmojis.forEach((k,v) -> {
                        if (emojis.containsKey(k)) {
                            emojis.put(k, emojis.get(k) + v);
                        } else {
                            emojis.put(k, v);
                        }
                    });
                }
            }
        }
        Map<String, Integer> sortedMap = sortMapByValue(emojis);
        dataCache.get(conversation).put(methodSerialNumber, sortedMap);
        return sortedMap;
    }

    /**
     * Sorts a map by value.
     *
     * @param map the map to be sorted
     * @param <K> the key of the map
     * @param <V> the value of the map
     * @return a new map sorted by value
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
                return (e2.getValue()).compareTo(e1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    /**
     * Determines a table of integers with user contributions plotted against months.
     *
     * @param conversation the conversation to be checked
     * @return a table of months in the rows, users in the columns, and integer cells indicating the number of contributions the user has made that month
     */
    public static Table<YearMonth, User, Integer> userContributionAgainstMonth(Conversation conversation) {
        Table<YearMonth, User, Integer> table = TreeBasedTable.create();
        for (Contribution contribution : conversation.getContributions()) {
            Date timestamp = contribution.getTimestamp();
            YearMonth month = YearMonth.from(timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            conversation.getUsers().forEach((user) -> {
                table.put(month, user, 0);
            });
        }
        for (Contribution contribution : conversation.getContributions()) {
            User user = contribution.getUser();
            Date timestamp = contribution.getTimestamp();
            YearMonth month = YearMonth.from(timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            if (table.contains(month, user)) {
                table.put(month, user, table.get(month, user) + 1);
            } else {
                table.put(month, user, 1);
            }
        }

        return table;
    }

    public static Table<User, String, Integer> emojiAgainstUser(Conversation conversation, int userLimit, int emojiLimit) {
        Map<User, Integer> rankedUsers = userContributions(conversation);
        Map<String, Integer> rankedEmojis = emojiFrequency(conversation);
        ArrayList<User> usersInTable = new ArrayList<>();
        ArrayList<String> emojisInTable = new ArrayList<>();
        int userLimitInput = userLimit;
        for (Map.Entry<User, Integer> rankedUsersEntry : rankedUsers.entrySet()) {
            int emojiLimitInput = emojiLimit;
            emojisInTable.removeAll(emojisInTable);
            for (Map.Entry<String, Integer> rankedEmojiEntry : rankedEmojis.entrySet()) {
                //table.put(rankedUsersEntry.getKey(), rankedEmojiEntry.getKey(), 0);
                emojisInTable.add(rankedEmojiEntry.getKey());
                emojiLimitInput--;
                if (emojiLimitInput == 0) {
                    break;
                }
            }
            usersInTable.add(rankedUsersEntry.getKey());
            userLimitInput--;
            if (userLimitInput == 0) {
                break;
            }
        }
        Table<User, String, Integer> table = ArrayTable.create(usersInTable, emojisInTable);
        for (Table.Cell<User, String, Integer> cell : table.cellSet()) {
            table.put(cell.getRowKey(), cell.getColumnKey(), 0);
        }
        /*
        rankedUsers.forEach((user, integer) -> {
            rankedEmojis.forEach((string, integer2) -> {
                table.put(user, string, 0);
            });
        });
        */
        for (Contribution contribution : conversation.getContributions()) {
            User user = contribution.getUser();
            if (usersInTable.contains(user)) {
                ArrayList<Message> messages = contribution.getMessages();
                for (Message message : messages) {
                    if (message instanceof StringMessage) {
                        ((StringMessage) message).getEmojis().forEach((emoji, frequency) -> {
                            if (emojisInTable.contains(emoji)) {
                                table.put(user, emoji, table.get(user, emoji) + frequency);
                            }
                        });
                    }
                }
            }
        }
        return table;
    }


}