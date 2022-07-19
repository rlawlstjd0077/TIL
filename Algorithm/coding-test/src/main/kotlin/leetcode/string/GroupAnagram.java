package leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 49. Group Anagrams (https://leetcode.com/problems/group-anagrams/)
 */
public class GroupAnagram {
    private Map<Map<Character, Integer>, List<String>> resultMap = new HashMap<>();

    public List<List<String>> groupAnagrams(String[] strs) {
        for (String str : strs) {
            final Map<Character, Integer> anagramMap = toAnagramMap(str);
            final List<String> strings = resultMap.computeIfAbsent(anagramMap, (s) -> new ArrayList<>());
            strings.add(str);
        }

        return resultMap
                .values()
                .stream()
                .collect(Collectors.toList());

    }

    private Map<Character, Integer> toAnagramMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char s : str.toCharArray()) {
            Integer value = map.getOrDefault(s, 0);
            map.put(s, value + 1);
        }

        return map;
    }
}