package katas.exercises;

import java.util.HashMap;
import java.util.Map;

public class URLShortener {
    /**
     * A URL Shortener is a service that converts a long URL into a shorter, more manageable URL.
     * Implement a simple URL shortener system with the following functionality:
     *
     *  - Convert a long URL into a short URL.
     *  - Retrieve the original long URL from a given short URL.
     *  - The system should handle the cases where multiple long URLs may share the same short URL, such as through hash collisions (you can assume no collisions for simplicity in this exercise).
     *  - The class should use a hash map to store the mapping between short and long URLs.
     *  - The short URL should be a base62 string (characters A-Z, a-z, 0-9).
     */

    private Map<String, String> urlMap;
    private static final String BASE_URL = "http://short.ly/";

    private String currStr;

    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final int BASE = CHARACTERS.length;

    /**
     * Constructor to initialize the URL shortener system.
     */
    public URLShortener() {
        urlMap = new HashMap<>();
        currStr = "";
    }



    /**
     * Shortens the provided long URL.
     *
     * @param longUrl the long URL to shorten
     * @return the shortened URL
     */
    public String shorten(String longUrl) {
        if (longUrl.isEmpty()){
            throw new IllegalArgumentException("URL Not Found - " + longUrl);
        }
        if (longUrl == null){
            throw new NullPointerException("URL Is Null");
        }
        String nextStr = generateNext(currStr);
        String nextURL = BASE_URL+nextStr;
        urlMap.put(nextURL,longUrl);
        currStr = nextStr;
        return nextURL;
    }

    /**
     * Retrieves the original long URL from the shortened URL.
     *
     * @param shortUrl the shortened URL
     * @return the original long URL
     */
    public String retrieve(String shortUrl) {
        if(!urlMap.containsKey(shortUrl)) throw new IllegalArgumentException("URL Not Found - " + shortUrl);
        return urlMap.get(shortUrl); // Implement logic to retrieve long URL
    }

    public String generateNext(String prev){
        if (prev == null || prev.isEmpty()) {
            return "a";
        }
        StringBuilder prevSTR = new StringBuilder(prev);
        int i = prevSTR.length()-1;
        while(i>=0){
            int currIndex = getCharacterIndex(prevSTR.charAt(i));
            if(currIndex < BASE -1){
                prevSTR.setCharAt(i,CHARACTERS[currIndex+1]);
                return prevSTR.toString();
            }else{
                prevSTR.setCharAt(i, CHARACTERS[0]);
                i--;
            }
        }
        return CHARACTERS[0] + prevSTR.toString();
    }

    private  int getCharacterIndex(char c) {
        for (int i = 0; i < CHARACTERS.length; i++) {
            if (CHARACTERS[i] == c) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid character: " + c);
    }
    public static void main(String[] args) {
        URLShortener shortener = new URLShortener();

        String longUrl = "https://www.example.com/some/really/long/url";
        String shortUrl = shortener.shorten(longUrl);

        System.out.println("Shortened URL: " + shortUrl);
        System.out.println("Original URL: " + shortener.retrieve(shortUrl));

        System.out.println(shortener.generateNext("99"));
        System.out.println(shortener.generateNext(""));
        for(int i = 0;i<10000;i++){
            System.out.println("-----------------");
            String shorturl = shortener.shorten(longUrl+"/a" + i);
            System.out.println("Shortened URL: " + shorturl);
            System.out.println("Original URL: " + shortener.retrieve(shorturl));
        }
    }
}

