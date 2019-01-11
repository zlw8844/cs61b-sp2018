import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
     new Palindromes, or the autograder might be upset.
    */
    static Palindrome palindrome = new Palindrome();

    static CharacterComparator cc = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } /* Uncomment this class once you've created your Palindrome class. */

    @Test
    public void testIsPalindrome() {
        /* test first method */
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("x"));

        assertFalse(palindrome.isPalindrome("Ca"));
        assertTrue(palindrome.isPalindrome("CC"));

        assertTrue(palindrome.isPalindrome("boob"));
        assertTrue(palindrome.isPalindrome("hioih"));

        assertFalse(palindrome.isPalindrome("boxb"));
        assertFalse(palindrome.isPalindrome("hzoih"));

        /*test 2nd method */
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("x", cc));

        assertFalse(palindrome.isPalindrome("Ca", cc));
        assertTrue(palindrome.isPalindrome("BC", cc));

        assertTrue(palindrome.isPalindrome("xtsy", cc));
        assertTrue(palindrome.isPalindrome("heofg", cc));

        assertFalse(palindrome.isPalindrome("booc", cc));
        assertFalse(palindrome.isPalindrome("x9y8z", cc));

    }
}
