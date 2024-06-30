package ru.job4j.algo.hash;

import java.util.LinkedHashMap;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        char[] charArray = str.toCharArray();
        String longestSubstring = "";
        int longestSubstringLength = 0;
        LinkedHashMap<Character, Integer> charPosMap = new LinkedHashMap<>();

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!charPosMap.containsKey(ch)) {
                charPosMap.put(ch, i);
            } else {
                i = charPosMap.get(ch);
                charPosMap.clear();
            }
            if (charPosMap.size() > longestSubstringLength) {
                longestSubstringLength = charPosMap.size();
                longestSubstring = charPosMap.keySet().toString();
                longestSubstring = longestSubstring.replaceAll("[^a-zA-Z]", "");
            }
        }
        return longestSubstring;
    }
}
