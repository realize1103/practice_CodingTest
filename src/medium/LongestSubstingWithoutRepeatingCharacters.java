package medium;

import java.util.*;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Example 4:
 *
 * Input: s = ""
 * Output: 0
 */
public class LongestSubstingWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++){
                System.out.println(n+" "+j+" "+i);
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
                System.out.println(ans);
            }

        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            System.out.println(ch);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        System.out.println(set.toString());
        return true;
    }
        /*
    public int lengthOfLongestSubstsring(String s) {
        int left = -1, intResult = 0;
        int[] table = new int[256];
        Arrays.fill(table, -1);

        final int len = s.length();

        for(int i = 0; i < len; i++) {
            left = Math.max(left, table[s.charAt(i)]);
            table[s.charAt(i)] = i;
            System.out.println(i+" "+left+"  "+table[s.charAt(i)]+"  "+s.charAt(i));
            intResult = Math.max(intResult, i - left);
        }

        return intResult;

    }
        */
    public static void main(String[] args) {
        LongestSubstingWithoutRepeatingCharacters longestSubstingWithoutRepeatingCharacters = new LongestSubstingWithoutRepeatingCharacters();
        System.out.println(longestSubstingWithoutRepeatingCharacters.lengthOfLongestSubstring("annnaaanab"));
    }
}
