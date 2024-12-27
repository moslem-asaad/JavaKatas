package katas.exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaxStorageCapacityTest {

    @Test
    void testMaxStorageAreaExample1() {
        int[] containers = {2, 1, 5, 6, 2, 3};
        assertEquals(10, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    void testMaxStorageAreaExample2() {
        int[] containers = {4, 2, 0, 3, 2, 5};
        assertEquals(6, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    void testMaxStorageAreaUniformHeight() {
        int[] containers = {1, 1, 1, 1, 1};
        assertEquals(5, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    void testMaxStorageAreaIncreasingHeights() {
        int[] containers = {1, 2, 3, 4, 5};
        assertEquals(9, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    void testMaxStorageAreaDecreasingHeights() {
        int[] containers = {5, 4, 3, 2, 1};
        assertEquals(9, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    void testMaxStorageAreaSingleContainer() {
        int[] containers = {7};
        assertEquals(7, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    void testMaxStorageAreaEmptyArray() {
        int[] containers = {};
        assertEquals(0, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    void testMaxStorageAreaAllZeros() {
        int[] containers = {0, 0, 0, 0};
        assertEquals(0, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    void testMaxStorageAreaComplexCase() {
        int[] containers = {6, 2, 5, 4, 5, 1, 6};
        assertEquals(12, MaxStorageCapacity.maxStorageArea(containers));
    }
}

