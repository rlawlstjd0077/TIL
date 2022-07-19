package leetcode.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 125. Valid Palindrome (https://leetcode.com/problems/valid-palindrome/)
 */
public class ValidPelindrom {
    public boolean isPalindrome(String s) {
        final Character[] characters = s
                .toLowerCase()
                .replaceAll(" ", "")
                .chars()
                .mapToObj(i -> (char) i)
                .filter(i -> isTarget(i))
                .toArray(Character[]::new);

        if (characters.length <= 1) {
            return true;
        }

        final int length = characters.length;

        for (int i = 0; i < length / 2; i++) {
            if (characters[i] != characters[(length - 1) - i]) {
                return false;
            }
        }

        return true;
    }

    private boolean isTarget(char c) {
        Pattern pattern = Pattern.compile("^[0-9a-z]*$");
        Matcher matcher = pattern.matcher(String.valueOf(c));
        return matcher.matches();
    }
}