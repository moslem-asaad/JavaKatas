package katas.exercises;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a stack that supports standard stack operations (push, pop, top) and also retrieves
 * the minimum element in constant time.
 */
public class MinStack {


    /** Initialize your data structure here. */
    Map<Integer,Integer> stack;
    Map<Integer,Integer> min;

    public MinStack() {
        stack = new HashMap<>();
        min = new HashMap<>();
    }

    public void push(int val) {
        stack.put(stack.size(),val);
        if(min.isEmpty()){
            min.put(min.size(),val);
        }else{
            int m = min.get(min.size()-1);
            if(val <= m){
                min.put(min.size(),val);
            }
        }
    }

    public void pop() {
        if (top() == getMin()){
            min.remove(min.size()-1);
        }
        stack.remove(stack.size()-1);
    }

    public int top() {
        if (stack.isEmpty()){
            throw new IllegalStateException("stack is empty");
        }
        return stack.get(stack.size()-1);
    }

    public int getMin() {
        if (min.isEmpty()){
            throw new IllegalStateException("stack is empty");
        }
        return min.get(min.size()-1);
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());
    }
}
