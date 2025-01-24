package katas.exercises;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LagsTest {

    @Test
    void testMaximizeProfitWithNonOverlappingRequests() {
        List<Lags.Request> requests = new ArrayList<>();
        requests.add(new Lags.Request("REQ01", 0, 5, 50));
        requests.add(new Lags.Request("REQ02", 5, 4, 60));
        requests.add(new Lags.Request("REQ03", 9, 3, 70));

        int result = Lags.maximizeProfit(requests);
        assertEquals(180, result);
    }

    @Test
    void testMaximizeProfitWithOverlappingRequests() {
        List<Lags.Request> requests = new ArrayList<>();
        requests.add(new Lags.Request("REQ01", 0, 5, 50));
        requests.add(new Lags.Request("REQ02", 3, 7, 80));
        requests.add(new Lags.Request("REQ03", 5, 4, 60));
        requests.add(new Lags.Request("REQ04", 6, 3, 70));

        int result = Lags.maximizeProfit(requests);
        assertEquals(120, result);
    }

    @Test
    void testMaximizeProfitWithSingleRequest() {
        List<Lags.Request> requests = new ArrayList<>();
        requests.add(new Lags.Request("REQ01", 0, 5, 50));

        int result = Lags.maximizeProfit(requests);
        assertEquals(50, result);
    }

    @Test
    void testMaximizeProfitWithNoRequests() {
        List<Lags.Request> requests = new ArrayList<>();

        int result = Lags.maximizeProfit(requests);
        assertEquals(0, result);
    }

    @Test
    void testMaximizeProfitWithAllOverlappingRequests() {
        List<Lags.Request> requests = new ArrayList<>();
        requests.add(new Lags.Request("REQ01", 0, 5, 50));
        requests.add(new Lags.Request("REQ02", 1, 4, 80));
        requests.add(new Lags.Request("REQ03", 2, 3, 60));

        int result = Lags.maximizeProfit(requests);
        assertEquals(80, result);
    }

    @Test
    void testMaximizeProfitWithComplexOverlap() {
        List<Lags.Request> requests = new ArrayList<>();
        requests.add(new Lags.Request("REQ01", 0, 3, 30));
        requests.add(new Lags.Request("REQ02", 3, 4, 60));
        requests.add(new Lags.Request("REQ03", 4, 3, 50));
        requests.add(new Lags.Request("REQ04", 7, 3, 70));
        requests.add(new Lags.Request("REQ05", 8, 2, 80));

        int result = Lags.maximizeProfit(requests);
        assertEquals(170, result);
    }
}

