package katas.exercises;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static katas.exercises.WordCounter.countWords;


public class WordCounterTest {

    @Test
    public void NormalSuccessCase(){
        assertEquals(4, countWords("my name is moslem"));
    }

    @Test
    public void manyConsecutiveSpacesCase(){
        assertEquals(4, countWords("     my                             name     is moslem"));
    }

    @Test
    public void emptySentence(){
        assertEquals(0, countWords(""));
    }
    @Test
    public void whiteSpacesOnlySentence(){
        assertEquals(0, countWords("             "));
    }

    @Test
    void testSpecialCharacters() {
        assertEquals(4,  WordCounter.countWords("Hello, How's it going?"));
    }
}
