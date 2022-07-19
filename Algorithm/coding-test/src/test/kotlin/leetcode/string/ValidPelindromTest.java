package leetcode.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidPelindromTest {
    @Test
    void test() {
        assertTrue(new ValidPelindrom().isPalindrome("aaa"));

        assertTrue(new ValidPelindrom().isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(new ValidPelindrom().isPalindrome("race a car"));
    }
}