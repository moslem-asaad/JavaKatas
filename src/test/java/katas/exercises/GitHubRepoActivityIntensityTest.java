package katas.exercises;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GitHubRepoActivityIntensityTest {

    @Test
    void testCalculateAverageTimeBetweenCommits() throws Exception {
        List<Instant> mockTimestamps = List.of(
                Instant.parse("2023-12-01T12:00:00Z"),
                Instant.parse("2023-12-01T14:00:00Z"),
                Instant.parse("2023-12-01T18:00:00Z"),
                Instant.parse("2023-12-02T06:00:00Z")
        );

        double averageTime = GitHubRepoActivityIntensity.calculateAverageTimeBetweenCommits(mockTimestamps);
        assertEquals(6.0,averageTime);
    }

    @Test
    void testFetchCommitTimestamps() throws Exception {
        HttpClient mockHttpClient = mock(HttpClient.class);
        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        String mockResponseBody  = "[\n" +
                "  {\n" +
                "    \"commit\": {\n" +
                "      \"author\": {\"date\": \"2023-12-01T12:00:00Z\"}\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"commit\": {\n" +
                "      \"author\": {\"date\": \"2023-12-01T14:00:00Z\"}\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"commit\": {\n" +
                "      \"author\": {\"date\": \"2023-12-01T18:00:00Z\"}\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"commit\": {\n" +
                "      \"author\": {\"date\": \"2023-12-02T06:00:00Z\"}\n" +
                "    }\n" +
                "  }\n" +
                "]";
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);
        List<Instant> timestamps = GitHubRepoActivityIntensity.fetchCommitTimestamps("mockOwner", "mockRepo", mockHttpClient);
        assertEquals(4, timestamps.size());
        assertEquals(Instant.parse("2023-12-01T12:00:00Z"), timestamps.get(0));
        assertEquals(Instant.parse("2023-12-01T14:00:00Z"), timestamps.get(1));
        assertEquals(Instant.parse("2023-12-01T18:00:00Z"), timestamps.get(2));

        double averageTime = GitHubRepoActivityIntensity.calculateAverageTimeBetweenCommits(timestamps);
        assertEquals(6.0, averageTime);
    }

    @Test
    void testCalculateAverageTimeWithInvalidInput() {
        List<Instant> mockTimestamps = List.of(
                Instant.parse("2023-12-01T12:00:00Z")
        );

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> GitHubRepoActivityIntensity.calculateAverageTimeBetweenCommits(mockTimestamps),
                "Expected an exception for less than two timestamps."
        );

        assertEquals("At least two timestamps are required to calculate the average time.", exception.getMessage());
    }

    @Test
    void testEmptyResponse() throws Exception {
        HttpClient mockHttpClient = mock(HttpClient.class);
        HttpResponse<String> mockResponse = mock(HttpResponse.class);

        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn("[]");
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        List<Instant> timestamps = GitHubRepoActivityIntensity.fetchCommitTimestamps("mockOwner", "mockRepo", mockHttpClient);

        assertTrue(timestamps.isEmpty(), "The list of timestamps should be empty for an empty API response.");
    }

    @Test
    void testHttpErrorHandling() throws Exception {
        HttpClient mockHttpClient = mock(HttpClient.class);
        HttpResponse<String> mockResponse = mock(HttpResponse.class);

        when(mockResponse.statusCode()).thenReturn(404);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Exception exception = assertThrows(Exception.class, () ->
                GitHubRepoActivityIntensity.fetchCommitTimestamps("mockOwner", "mockRepo", mockHttpClient));

        assertTrue(exception.getMessage().contains("HTTP response code 404"), "Expected an HTTP error exception.");
    }

    @Test
    void testExtremeTimeDifferences() {
        List<Instant> timestamps = List.of(
                Instant.parse("2000-01-01T00:00:00Z"),
                Instant.parse("2023-12-01T00:00:00Z")
        );

        double averageTime = GitHubRepoActivityIntensity.calculateAverageTimeBetweenCommits(timestamps);

        assertTrue(averageTime > 200000, "The average time should be very large for extreme time differences.");
    }

    @Test
    void testCalculateAverageTimeWithTwoIdenticalTimestamps() {
        List<Instant> timestamps = List.of(
                Instant.parse("2023-12-01T12:00:00Z"),
                Instant.parse("2023-12-01T12:00:00Z")
        );

        double averageTime = GitHubRepoActivityIntensity.calculateAverageTimeBetweenCommits(timestamps);

        assertEquals(0.0, averageTime, "The average time should be 0.0 when timestamps are identical.");
    }

    @Test
    void testCalculateAverageTimeWithNegativeIntervals() {
        List<Instant> timestamps = List.of(
                Instant.parse("2023-12-01T12:00:00Z"),
                Instant.parse("2023-11-30T12:00:00Z")
        );

        double averageTime = GitHubRepoActivityIntensity.calculateAverageTimeBetweenCommits(timestamps);

        assertEquals(24.0, averageTime, "The average time should handle negative intervals correctly.");
    }

}
