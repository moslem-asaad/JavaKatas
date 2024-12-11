package katas.exercises;

public class ArrayDifference {

    /**
     * Finds the difference between the largest and smallest numbers in the array.
     *
     * @param numbers the array of integers
     * @return the difference between the largest and smallest numbers
     */
    public static int findDifference(int[] numbers) {
        if(numbers.length == 0) throw new IllegalArgumentException("the array's length should be positive");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int num : numbers){
            if(num < min) min = num;
            if(num > max) max = num;
        }
        return max - min;
    }

    public static void main(String[] args) {
        int[] sampleArray = {10, 3, 5, 6, 20, -2};
        int difference = findDifference(sampleArray);
        System.out.println(difference);
    }
}
