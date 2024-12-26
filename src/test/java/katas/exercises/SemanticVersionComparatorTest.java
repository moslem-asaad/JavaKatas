package katas.exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SemanticVersionComparatorTest {

    @Test
    public void testVersion1LessThanVersion2() {
        assertEquals(-1, SemanticVersionComparator.compareVersions("1.0.0", "1.0.1"), "Version 1 should be less than Version 2.");
        assertEquals(-1, SemanticVersionComparator.compareVersions("1.0.0", "1.1.0"), "Version 1 should be less than Version 2.");
        assertEquals(-1, SemanticVersionComparator.compareVersions("1.0.0", "2.0.0"), "Version 1 should be less than Version 2.");
    }

    @Test
    public void testVersion1GreaterThanVersion2() {
        assertEquals(1, SemanticVersionComparator.compareVersions("1.0.1", "1.0.0"), "Version 1 should be greater than Version 2.");
        assertEquals(1, SemanticVersionComparator.compareVersions("1.1.0", "1.0.0"), "Version 1 should be greater than Version 2.");
        assertEquals(1, SemanticVersionComparator.compareVersions("2.0.0", "1.0.0"), "Version 1 should be greater than Version 2.");
    }

    @Test
    public void testVersionsEqual() {
        assertEquals(0, SemanticVersionComparator.compareVersions("1.0.0", "1.0.0"), "Versions should be equal.");
        assertEquals(0, SemanticVersionComparator.compareVersions("1.2.3", "1.2.3"), "Versions should be equal.");
        assertEquals(0, SemanticVersionComparator.compareVersions("2.0.1", "2.0.1"), "Versions should be equal.");
    }

    @Test
    public void testInvalidVersion1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                SemanticVersionComparator.compareVersions("", "1.0.0")
        );
        assertEquals("version cannot be empty", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () ->
                SemanticVersionComparator.compareVersions("1.0", "1.0.0")
        );
        assertEquals("incorrect syntax for version 1", exception.getMessage());
    }

    @Test
    public void testInvalidVersion2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                SemanticVersionComparator.compareVersions("1.0.0", "")
        );
        assertEquals("version cannot be empty", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () ->
                SemanticVersionComparator.compareVersions("1.0.0", "1.0")
        );
        assertEquals("incorrect syntax for version 2", exception.getMessage());
    }

    @Test
    public void testLeadingZeros() {
        assertEquals(0, SemanticVersionComparator.compareVersions("01.0.0", "1.0.0"), "Leading zeros should not affect comparison.");
        assertEquals(-1, SemanticVersionComparator.compareVersions("1.0.0", "1.01.0"), "Leading zeros should not affect comparison.");
    }
}
