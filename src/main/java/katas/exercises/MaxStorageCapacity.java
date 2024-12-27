package katas.exercises;

import java.util.Map;
import java.util.Stack;

public class MaxStorageCapacity {

    /**
     *
     * Imagine a series of storage containers placed side by side, where the height of each container
     * is given by an integer in the array. Your task is to find the largest rectangular area that
     * can be formed using one or more of these containers.
     *
     * For example:
     * Input: containers = [2, 1, 5, 6, 2, 3]
     * Output: 10
     * Explanation: The largest rectangle is formed between containers at indices 2 and 3
     * with height 5 and width 2.
     *
     * @param containers an array of integers representing the heights of containers
     * @return the area of the largest rectangle formed between containers
     */
    public static int maxStorageArea(int[] containers) {
        if(containers.length == 0) return 0;
        // Hint for efficient implementation: stack
        Stack <Integer> indices = new Stack<>();
        int max = 0;
        indices.push(0);
        for(int i = 1;i< containers.length;i++){
            int prevHight = containers[indices.peek()];
            while (prevHight > containers[i]){
                int indx = indices.pop();
                if(indices.isEmpty()){
                    int area = prevHight * (i);
                    max = Math.max(max,area);
                    prevHight = containers[i]-1;
                }
                else{
                    int area = prevHight * (i - indices.peek() - 1);
                    max = Math.max(max,area);
                    prevHight = containers[indices.peek()];
                }
            }
            indices.push(i);
        }
        while (!indices.isEmpty()){
            int i = containers.length;
            int hight = containers[indices.pop()];
            if(indices.isEmpty()){
                int area = hight * i;
                max = Math.max(max,area);
            }else{
                int area = hight * (i - indices.peek() - 1);
                max = Math.max(max,area);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] containers = {2, 1, 5, 6, 2, 3};

        int result = maxStorageArea(containers);
        System.out.println("Max storage area: " + result); // Expected output: 10
    }
}
