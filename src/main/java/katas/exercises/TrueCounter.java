package katas.exercises;

public class TrueCounter {

    /**
     * Counts the number of true values in the given boolean array.
     *
     * @param array the boolean array to check
     * @return the count of true values in the array
     */
    public static int countTrueValues(boolean[] array) {
        int count = 0;
        for(boolean val : array){
            if (val) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        boolean[] sampleArray = {true, false, true, true, false};
        int trueCount = countTrueValues(sampleArray);
        System.out.println(trueCount);
    }
}
