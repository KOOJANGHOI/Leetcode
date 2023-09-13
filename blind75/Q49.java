import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Q49 {
    /**
     * return anagram array from array of string
     * anagram은 character 정렬시 같은 값이군! 난 멍청한 방법으로 풀었다
     */
    // TODO(simon): hashmap > 20m > time 12, space 13
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str: strs) {
            int[] arr = new int[26];

            for (char c : str.toCharArray())
                arr[c - 'a']++;

            String key = "";

            for (int cnt : arr)
                key += cnt + ".";

            List<String> anagrams = map.getOrDefault(key, new ArrayList<>());
            anagrams.add(str);

            map.put(key, anagrams);
        }

        return new ArrayList<>(map.values());
    }

    // TODO(simon): sorting approach > 5m > time 82, space 70
    public List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str: strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);

            String key = new String(charArray);

            List<String> anagrams = map.getOrDefault(key, new ArrayList<>());
            anagrams.add(str);

            map.put(key, anagrams);
        }

        return new ArrayList<>(map.values());
    }

    public void solve() {
        String[] strs = {"bdddddddddd","bbbbbbbbbbc"};
        System.out.println(groupAnagrams(strs));
    }
}
