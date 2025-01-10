package katas.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class URLShortenerTest {

    private URLShortener shortener;

    @BeforeEach
    public void setUp() {
        shortener = new URLShortener();
    }

    @Test
    public void testShortenAndRetrieveSingleURL() {
        String longUrl = "https://www.example.com/some/really/long/url";
        String shortUrl = shortener.shorten(longUrl);

        assertNotNull(shortUrl);
        assertEquals(longUrl, shortener.retrieve(shortUrl));
    }

    @Test
    public void testShortenMultipleURLs() {
        String longUrl1 = "https://www.example.com/page1";
        String longUrl2 = "https://www.example.com/page2";
        String longUrl3 = "https://www.example.com/page3";

        String shortUrl1 = shortener.shorten(longUrl1);
        String shortUrl2 = shortener.shorten(longUrl2);
        String shortUrl3 = shortener.shorten(longUrl3);

        assertNotEquals(shortUrl1, shortUrl2);
        assertNotEquals(shortUrl2, shortUrl3);
        assertNotEquals(shortUrl1, shortUrl3);

        assertEquals(longUrl1, shortener.retrieve(shortUrl1));
        assertEquals(longUrl2, shortener.retrieve(shortUrl2));
        assertEquals(longUrl3, shortener.retrieve(shortUrl3));
    }

    @Test
    public void testRetrieveNonExistentURL() {
        String invalidShortUrl = "http://short.ly/invalid";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> shortener.retrieve(invalidShortUrl));
        assertEquals("URL Not Found - " + invalidShortUrl, exception.getMessage());
    }

    @Test
    public void testGenerateNext() {
        assertEquals("a", shortener.generateNext(""));
        assertEquals("b", shortener.generateNext("a"));
        assertEquals("z", shortener.generateNext("y"));
        assertEquals("A", shortener.generateNext("z"));
        assertEquals("B", shortener.generateNext("A"));
        assertEquals("0", shortener.generateNext("Z"));
        assertEquals("1", shortener.generateNext("0"));
        assertEquals("aa", shortener.generateNext("9"));
        assertEquals("ab", shortener.generateNext("aa"));
        assertEquals("aA", shortener.generateNext("az"));
        assertEquals("aaa", shortener.generateNext("99"));
    }

    @Test
    public void testGenerateNextForLargeStrings() {
        assertEquals("a", shortener.generateNext(""));
        assertEquals("aa", shortener.generateNext("9"));
        assertEquals("zA", shortener.generateNext("zz"));
        assertEquals("aaA", shortener.generateNext("aaz"));
        assertEquals("azA", shortener.generateNext("azz"));
        assertEquals("aaaa", shortener.generateNext("999"));
    }

    @Test
    public void testShortenAndRetrieveWithLargeURLs() {
        for (int i = 0; i < 1000; i++) {
            String longUrl = "https://www.example.com/page/" + i;
            String shortUrl = shortener.shorten(longUrl);
            assertNotNull(shortUrl);
            assertEquals(longUrl, shortener.retrieve(shortUrl));
        }
    }

    @Test
    public void testShortenDuplicateURLs() {
        String longUrl = "https://www.example.com/same/url";
        String shortUrl1 = shortener.shorten(longUrl);
        String shortUrl2 = shortener.shorten(longUrl);
        assertNotEquals(shortUrl1, shortUrl2);
        assertEquals(longUrl, shortener.retrieve(shortUrl1));
        assertEquals(longUrl, shortener.retrieve(shortUrl2));
    }

    @Test
    public void testEdgeCases() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> shortener.shorten(""));
        assertEquals("URL Not Found - ", exception1.getMessage());

        Exception exception2 = assertThrows(NullPointerException.class, () -> shortener.shorten(null));
    }

    @Test
    public void testCollisionAvoidance() {
        String longUrl1 = "https://www.example1.com";
        String longUrl2 = "https://www.example2.com";

        String shortUrl1 = shortener.shorten(longUrl1);
        String shortUrl2 = shortener.shorten(longUrl2);

        assertNotEquals(shortUrl1, shortUrl2);
    }
}

