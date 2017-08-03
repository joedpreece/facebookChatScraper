package com.joedpreece.analysis;

import com.joedpreece.objects.*;

import java.util.*;

/**
 * Created by Joe on 02/08/17.
 */
public class Queries {

    public static Map<Conversation, Map<String, Object>> dataCache = new HashMap<>();

    public static Map<String, Integer> determineUserContributions(Conversation conversation) {
        String methodSerialNumber = "N9TT-9G0A-B7FQ-RANC";
        if (dataCache.get(conversation).containsKey(methodSerialNumber)) {
            return (Map<String, Integer>) dataCache.get(conversation).get(methodSerialNumber);
        }
        Map<User, Integer> rankings = new HashMap<>();
        Map<String, Integer> output = new HashMap<>();
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
            output.put(user.getName(), integer);
        }
        Map<String, Integer> sorted = sortByValue(output);
        dataCache.get(conversation).put(methodSerialNumber, sorted);
        return sorted;
    }

    private static Map<String, Integer> determineAllWordFrequencies(Conversation conversation) {
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
        Map<String, Integer> sortedMap = sortByValue(wordFrequency);
        dataCache.get(conversation).put(methodSerialNumber, sortedMap);
        return sortedMap;
    }

    public static Map<String, Integer> determineWordFrequencies(Conversation conversation, String... words) {
        Map<String, Integer> allWordFrequencies = determineAllWordFrequencies(conversation);
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

    public static Map<String, Integer> determineEmojiFrequencies(Conversation conversation) {
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
        Map<String, Integer> sortedMap = sortByValue(emojis);
        dataCache.get(conversation).put(methodSerialNumber, sortedMap);
        return sortedMap;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
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

    public static Map<String, Map<User, Integer>> getUserContributionsOverTime(Conversation conversation) {
        Map<String, Map<User, Integer>> contributionTotal = new TreeMap<>();

        return contributionTotal;
    }

}