package katas.exercises;

import java.util.PriorityQueue;

/**
 * find the median of a stream of integers.
 *
 * The numbers will be provided one at a time in a dynamic data stream, and after each new number is added,
 * your function should efficiently compute the median of all numbers seen so far.
 *
 * Adding a number: O(log n).
 * Finding the median: O(1) or O(log n).
 *
 * Hint: Consider using two heaps (min-heap and max-heap) to efficiently maintain the order of elements.
 */
public class MedianFinder {

    /**
     * Initializes the MedianFinder object.
     */
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a,b) -> b-a);
    }

    /**
     * Adds a number to the data stream.
     *
     * @param num the number to be added
     */
    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()){
            maxHeap.add(num);
        }else{
            minHeap.add(num);
        }
        
        if (minHeap.size()>maxHeap.size()){
            maxHeap.add(minHeap.poll());
        } else if (minHeap.size() + 1 < maxHeap.size()) {
            minHeap.add(maxHeap.poll());
        }
    }

    /**
     * Finds and returns the median of the data stream.
     *
     * @return the median as a double
     */
    public double findMedian() {
        if (maxHeap.isEmpty()){
            throw new IllegalStateException("no numbers entered");
        }
        return minHeap.size() == maxHeap.size() ? (double) (minHeap.peek() + maxHeap.peek()) / 2:maxHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Median: " + medianFinder.findMedian());

        medianFinder.addNum(3);
        System.out.println("Median: " + medianFinder.findMedian());

        medianFinder.addNum(5);
        System.out.println("Median: " + medianFinder.findMedian());
    }
}
