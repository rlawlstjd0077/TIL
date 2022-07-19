package leetcode.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupAnagramTest {
    @Test
    void test() {
        assertEquals(new GroupAnagram().groupAnagrams(new String[] { "abc", "cab", "bbb" }).toString(), "[[bbb], [abc, cab]]");
    }
}