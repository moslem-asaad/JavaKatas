package katas.exercises;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;

import java.util.ArrayList;
import java.util.List;


public class GitHubRepoActivityIntensity {
    /**
     * Analyze the activity level of a specific GitHub repository.
     *
     * Calculate the average time (in hours) between commits in a given GitHub repository.
     *
     * Use the GitHub API to:
     *
     *     - Fetch commit timestamps for a given repository (paginate to fetch ALL commits!!!)
     *     - Calculate the average time (in hours) between consecutive commits.
     *
     * Use the GitHub REST API to fetch commit data for a repository.
     * The API endpoint is: GET https://api.github.com/repos/{owner}/{repo}/commits
     *
     *
     * Note: the unittest for this kata is partially implemented under `GitHubRepoActivityIntensityTest`.
     */

    private static final String GITHUB_API_BASE_URL = "https://api.github.com/repos";


    /**
     * Fetches commit timestamps for the specified repository using the GitHub API.
     *
     * @param owner the owner of the repository
     * @param repo the name of the repository
     * @return a list of commit timestamps as Instant objects
     * @throws Exception if there is an error fetching or parsing the data
     */
    public static List<Instant> fetchCommitTimestamps(String owner, String repo) throws Exception {
        // example:
        String apiUrl = "https://api.github.com/repos/" + owner + "/" + repo + "/commits";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/vnd.github+json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new Exception("Failed to fetch data: HTTP response code " + response.statusCode());
        }

        List<Instant> commitTimestamps = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode commitsArray = objectMapper.readTree(response.body());

        for (JsonNode commitNode : commitsArray) {
            String timestamp = commitNode.get("commit").get("author").get("date").asText();
            commitTimestamps.add(Instant.parse(timestamp));
        }
        return commitTimestamps;

    }

    public static List<Instant> fetchCommitTimestamps(String owner, String repo, HttpClient httpClient) throws Exception {
        String apiUrl = "https://api.github.com/repos/" + owner + "/" + repo + "/commits";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/vnd.github+json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new Exception("Failed to fetch data: HTTP response code " + response.statusCode());
        }

        List<Instant> commitTimestamps = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode commitsArray = objectMapper.readTree(response.body());

        for (JsonNode commitNode : commitsArray) {
            String timestamp = commitNode.get("commit").get("author").get("date").asText();
            commitTimestamps.add(Instant.parse(timestamp));
        }
        return commitTimestamps;
    }


    /**
     * Calculates the average time between consecutive commits.
     *
     * @param timestamps a list of commit timestamps
     * @return the average time in hours
     */
    public static double calculateAverageTimeBetweenCommits(List<Instant> timestamps) {
        if(timestamps == null || timestamps.size() < 2){
            throw new IllegalArgumentException("At least two timestamps are required to calculate the average time.");
        }
        long totalSeconds = 0;
        for(int i = 1; i<timestamps.size();i++){
            Duration duration = Duration.between(timestamps.get(i), timestamps.get(i-1));
            totalSeconds+=Math.abs(duration.getSeconds());
        }
        double avgSeconds = (double) totalSeconds / (timestamps.size()-1);
        return avgSeconds/3600.0;
    }

    public static void main(String[] args) {
        try {
            List<Instant> timestamps = fetchCommitTimestamps("torvalds", "linux");
            double avgTime = calculateAverageTimeBetweenCommits(timestamps);
            for (Instant in: timestamps){
                System.out.println(" -- " + in);
            }
            System.out.printf("The average time between commits in the repository is %.2f hours.%n", avgTime);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}


