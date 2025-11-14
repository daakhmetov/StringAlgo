import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RollingHashSearch using JUnit 5.
 * Each test checks a different input size or edge case.
 */
public class RollingHashSearchTest {
    @Test
    void testShortString() {
        assertTrue(
                RollingHashSearch.contains("hello", "lo")
        );
    }

    @Test
    void testMediumString() {
        assertTrue(
                RollingHashSearch.contains("ababcababcab", "abc"),
                "Pattern 'abc' should be found multiple times"
        );
    }

    @Test
    void testLongString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            sb.append("abc");
        }
        sb.append("xyz");

        String longText = sb.toString();

        assertTrue(
                RollingHashSearch.contains(longText, "xyz"),
                "Pattern 'xyz' should be found at the end of long text"
        );
    }
    @Test
    void testPatternNotFound() {
        assertFalse(
                RollingHashSearch.contains("abcdef", "zzz"),
                "Pattern 'zzz' does not exist in 'abcdef'"
        );
    }
    @Test
    void testPatternEqualsText() {
        assertTrue(
                RollingHashSearch.contains("pattern", "pattern"),
                "Identical pattern and text must match"
        );
    }
    @Test
    void testPatternLongerThanText() {
        assertFalse(
                RollingHashSearch.contains("short", "muchlongerpattern"),
                "Pattern longer than text cannot match"
        );
    }
}
