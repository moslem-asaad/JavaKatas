package katas.exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class StockTraderTest {

    @Test
    public void testMaxProfitSingleTransaction() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        assertEquals(5, StockTrader.maxProfit(prices), "Max profit should be 5.");
    }

    @Test
    public void testNoProfitPossible() {
        int[] prices = {7, 6, 4, 3, 1};
        assertEquals(0, StockTrader.maxProfit(prices), "No profit should be achievable, return 0.");
    }

    @Test
    public void testEmptyPrices() {
        int[] prices = {};
        assertEquals(0, StockTrader.maxProfit(prices), "Empty price array should return 0.");
    }

    @Test
    public void testSingleDay() {
        int[] prices = {5};
        assertEquals(0, StockTrader.maxProfit(prices), "Single day prices should return 0.");
    }

    @Test
    public void testDecreasingPrices() {
        int[] prices = {10, 9, 8, 7, 6};
        assertEquals(0, StockTrader.maxProfit(prices), "Decreasing prices should return 0.");
    }

    @Test
    public void testIncreasingPrices() {
        int[] prices = {1, 2, 3, 4, 5};
        assertEquals(4, StockTrader.maxProfit(prices), "Increasing prices should return max profit (last - first).");
    }

    @Test
    public void testFluctuatingPrices() {
        int[] prices = {3, 2, 6, 5, 0, 3};
        assertEquals(4, StockTrader.maxProfit(prices), "Fluctuating prices should return the correct max profit.");
    }
}
