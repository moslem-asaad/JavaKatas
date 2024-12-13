package katas.exercises;

import org.springframework.boot.util.LambdaSafe;

import java.util.concurrent.Callable;

public class TimeMe {

    /**
     * Measures the time it takes to execute a given function.
     *
     * @param func the function to measure
     * @return the execution time in milliseconds
     */
    public static long measureExecutionTime(Runnable func) {
        //hint:  System.currentTimeMillis();
        if (func == null) {
            throw new IllegalArgumentException("Function cannot be null");
        }
        long startTime = System.currentTimeMillis();
        func.run();

        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        Runnable sampleFunction = () -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        long timeTaken = measureExecutionTime(sampleFunction);
        System.out.println("Time taken by sampleFunction: " + timeTaken + " ms");

        Runnable quickFunction = () -> System.out.println("Quick task done!");
        timeTaken = measureExecutionTime(quickFunction);
        System.out.println("Time taken by quickFunction: " + timeTaken + " ms");
    }
}
