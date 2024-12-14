package katas.exercises;

public class DoNTimes {

    /**
     * Executes the given function n times.
     *
     * @param func the function to execute
     * @param n    the number of times to execute the function
     */
    public static void doNTimes(Runnable func, int n) {
        if (func == null) {
            throw new IllegalArgumentException("Function cannot be null");
        }
        if (n < 0) {
            throw new IllegalArgumentException("Number of times cannot be negative");
        }

        while(n > 0) {
            func.run();
            n--;
        }
    }

    public static void main(String[] args) {
        Runnable sayHello = () -> System.out.println("Hello!");
        System.out.println("Calling function 3 times:");
        doNTimes(sayHello, 3);

        Runnable printMessage = () -> System.out.println("Java is fun!");
        System.out.println("Calling another function 5 times:");
        doNTimes(printMessage, 5);
    }
}

