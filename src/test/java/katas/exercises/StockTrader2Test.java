package katas.exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class StockTrader2Test {

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

    @Test
    public void testMaxProfitMultipleTransactions() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        assertEquals(7, StockTrader2.maxProfit(prices), "Max profit for multiple transactions should be 7.");
    }

    @Test
    public void testMultipleTransactionsNoProfit() {
        int[] prices = {7, 6, 4, 3, 1};
        assertEquals(0, StockTrader2.maxProfit(prices), "No profit should be achievable for multiple transactions, return 0.");
    }

    @Test
    public void testMultipleTransactionsEmptyPrices() {
        int[] prices = {};
        assertEquals(0, StockTrader2.maxProfit(prices), "Empty price array for multiple transactions should return 0.");
    }

    @Test
    public void testMultipleTransactionsSingleDay() {
        int[] prices = {5};
        assertEquals(0, StockTrader2.maxProfit(prices), "Single day prices for multiple transactions should return 0.");
    }

    @Test
    public void testMultipleTransactionsIncreasingPrices() {
        int[] prices = {1, 2, 3, 4, 5};
        assertEquals(4, StockTrader2.maxProfit(prices), "Increasing prices should return max profit (sum of all gains).");
    }

    @Test
    public void testMultipleTransactionsFluctuatingPrices() {
        int[] prices = {3, 2, 6, 5, 0, 3};
        assertEquals(7, StockTrader2.maxProfit(prices), "Fluctuating prices should return the correct max profit for multiple transactions.");
    }
}

