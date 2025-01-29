package katas.exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedianFinderTest {

    @Test
    void testSingleElement() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(10);
        assertEquals(10.0, medianFinder.findMedian(), "Median of one element should be the element itself");
    }

    @Test
    void testEvenNumberOfElements() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(10);
        medianFinder.addNum(20);
        assertEquals(15.0, medianFinder.findMedian(), "Median of [10,20] should be (10+20)/2 = 15");
    }

    @Test
    void testOddNumberOfElements() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(10);
        medianFinder.addNum(20);
        medianFinder.addNum(30);
        assertEquals(20.0, medianFinder.findMedian(), "Median of [10,20,30] should be 20");
    }

    @Test
    void testNegativeNumbers() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-10);
        medianFinder.addNum(-20);
        medianFinder.addNum(-30);
        assertEquals(-20.0, medianFinder.findMedian(), "Median of [-10,-20,-30] should be -20");
    }

    @Test
    void testMixedNumbers() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(80);
        medianFinder.addNum(70);
        medianFinder.addNum(60);
        medianFinder.addNum(50);
        medianFinder.addNum(10);
        medianFinder.addNum(40);
        medianFinder.addNum(20);
        assertEquals(50.0, medianFinder.findMedian(), "Median of [80,70,60,50,10,40,20] should be 50");
    }

    @Test
    void testLargeNumberOfElements() {
        MedianFinder medianFinder = new MedianFinder();
        for (int i = 1; i <= 1000; i++) {
            medianFinder.addNum(i);
        }
        assertEquals(500.5, medianFinder.findMedian(), "Median of numbers 1 to 1000 should be (500+501)/2 = 500.5");
    }
}

