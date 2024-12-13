package katas.exercises;

import java.util.ArrayList;
import java.util.List;

public class ListFlatten {

    /**
     * Flattens a nested list into a single list of integers.
     *
     * @param nestedList the input nested list
     * @return a flat list containing all integers from the nested structure
     */
    public static List<Integer> flattenList(List<Object> nestedList) {
        if(nestedList == null){
            throw new NullPointerException("nestedList can't be null");
        }
        // hint: instanceof
        List<Integer> res = new ArrayList<>();
        for(Object ob: nestedList){
            if(ob instanceof List){
                res.addAll(flattenList((List<Object>) ob));
            }else if (ob instanceof Integer){
                res.add((Integer) ob);
            }else{
                throw new ClassCastException("The items should be numbers");
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Object> nestedList = new ArrayList<>();
        nestedList.add(1);
        nestedList.add(List.of(2, 3));
        nestedList.add(List.of(4, List.of(5, 6)));
        nestedList.add(7);
        System.out.println(nestedList.toString());

        List<Integer> flatList = flattenList(nestedList);

        System.out.println("Flattened list: " + flatList);
    }
}
