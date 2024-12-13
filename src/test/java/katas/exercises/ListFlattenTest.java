package katas.exercises;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ListFlattenTest {

    @Test
    void testSimpleNestedList() {
        List<Object> nestedList = List.of(1, List.of(2, 3), 4);
        List<Integer> result = ListFlatten.flattenList(nestedList);
        assertEquals(List.of(1, 2, 3, 4), result, "Should flatten a simple nested list");
    }

    @Test
    void testDeeplyNestedList() {
        List<Object> nestedList = List.of(1, List.of(2, List.of(3, List.of(4))));
        List<Integer> result = ListFlatten.flattenList(nestedList);
        assertEquals(List.of(1, 2, 3, 4), result, "Should flatten a deeply nested list");
    }

    @Test
    void testEmptyNestedList() {
        List<Object> nestedList = List.of(List.of(), List.of(List.of()));
        List<Integer> result = ListFlatten.flattenList(nestedList);
        assertEquals(List.of(), result, "Should return an empty list for an empty nested list");
    }

    @Test
    void testSingleElementList() {
        List<Object> nestedList = List.of(1);
        List<Integer> result = ListFlatten.flattenList(nestedList);
        assertEquals(List.of(1), result, "Should return a single-element list");
    }

    @Test
    void testListWithNonIntegerValues() {
        List<Object> nestedList = List.of(1, "string", List.of(2, true, List.of(3)));
        Exception exception = assertThrows(ClassCastException.class, () -> {
            ListFlatten.flattenList(nestedList);
        });
        assertTrue(exception instanceof ClassCastException, "Should throw a ClassCastException for non-integer values");
    }

    @Test
    void testNullInput() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            ListFlatten.flattenList(null);
        });
        assertEquals("nestedList can't be null", exception.getMessage(), "Should throw NullPointerException for null input");
    }
}
