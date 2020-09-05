import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 *
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Otherwise, we define that this word doesn't use capitals in a right way.
 *
 *
 * Example 1:
 *
 * Input: "USA"
 * Output: True
 *
 *
 * Example 2:
 *
 * Input: "FlaG"
 * Output: False
 */

public class DetectCapital {
    public boolean detectCapitalUse(String word) {
        int cnt = 0;

        for(final char c : word.toCharArray()) {
            if(Character.isUpperCase(c)) {
                ++cnt;
            }
        }

        return ((cnt == 0 || cnt == word.length()) || (cnt == 1 && Character.isUpperCase(word.charAt(0))));
    }

    public static void main(String[] args) {
        DetectCapital detectCapital = new DetectCapital();
        System.out.println(detectCapital.detectCapitalUse("USA"));
        System.out.println(detectCapital.detectCapitalUse("FlaG"));
    }


}
