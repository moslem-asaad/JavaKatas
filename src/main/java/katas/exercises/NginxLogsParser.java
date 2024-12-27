package katas.exercises;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NginxLogsParser {

    /**
     * Parses a single Nginx access log entry into a structured map.
     *
     * The log format is:
     * 122.176.223.47 - - [05/Feb/2024:08:29:40 +0000] "GET /web-enabled/Enhanced-portal/bifurcated-forecast.js HTTP/1.1" 200 1684 "-" "Opera/9.58 (X11; Linux i686; en-US) Presto/2.12.344 Version/13.00"
     *
     * The parsed log data should be returned as a map:
     * {
     *   "client_ip": "122.176.223.47",
     *   "date": "05/Feb/2024:08:29:40 +0000",
     *   "http_method": "GET",
     *   "path": "/web-enabled/Enhanced-portal/bifurcated-forecast.js",
     *   "http_version": "1.1",
     *   "status": "200",
     *   "response_bytes": "1684",
     *   "user_agent": "Opera/9.58 (X11; Linux i686; en-US) Presto/2.12.344 Version/13.00"
     * }
     *
     * Hint: Use regex
     *
     * @param log the Nginx log string
     * @return a map containing parsed log data
     * @throws IllegalArgumentException if the log format is invalid
     */
    public static Map<String, String> parseLog(String log) {
        String logPattern = "(\\S+) - - \\[(.*?)] \"(\\S+) (\\S+) HTTP/(\\S+)\" (\\d+) (\\d+) \"-\" \"(.*?)\"";

        Pattern pattern = Pattern.compile(logPattern);
        Matcher matcher = pattern.matcher(log);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid log format");
        }

        Map<String, String> parsedData = new HashMap<>();
        String clientIp = matcher.group(1);
        validateClientIp(clientIp);
        parsedData.put("client_ip", clientIp);

        String date = matcher.group(2);
        validateDate(date);
        parsedData.put("date", date);

        String httpMethod = matcher.group(3);
        validateHttpMethod(httpMethod);
        parsedData.put("http_method", httpMethod);

        String path = matcher.group(4);
        validatePath(path);
        parsedData.put("path", path);

        String httpVersion = matcher.group(5);
        validateHttpVersion(httpVersion);
        parsedData.put("http_version", httpVersion);

        String status = matcher.group(6);
        validateStatus(status);
        parsedData.put("status", status);

        String responseBytes = matcher.group(7);
        validateResponseBytes(responseBytes);
        parsedData.put("response_bytes", responseBytes);

        String userAgent = matcher.group(8);
        validateUserAgent(userAgent);
        parsedData.put("user_agent", userAgent);

        return parsedData;
    }
    private static void validateClientIp(String clientIp) {
        String ipPattern = "\\b(\\d{1,3}\\.){3}\\d{1,3}\\b";
        if (!clientIp.matches(ipPattern)) {
            throw new IllegalArgumentException("Invalid client IP: " + clientIp);
        }
    }

    private static void validateDate(String date) {
        Pattern datePattern = Pattern.compile("(\\d{2})/(\\w{3})/(\\d{4}):(\\d{2}):(\\d{2}):(\\d{2}) ([+-]\\d{4})");
        if (!datePattern.matcher(date).matches()) {
            throw new IllegalArgumentException("Invalid date format: " + date);
        }
    }

    private static void validateHttpMethod(String httpMethod) {
        String[] validMethods = {"GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH"};
        for (String method : validMethods) {
            if (method.equals(httpMethod)) {
                return;
            }
        }
        throw new IllegalArgumentException("Invalid HTTP method: " + httpMethod);
    }

    private static void validatePath(String path) {
        if (!path.startsWith("/")) {
            throw new IllegalArgumentException("Invalid path: " + path);
        }
    }

    private static void validateHttpVersion(String httpVersion) {
        if (!httpVersion.matches("\\d\\.\\d")) {
            throw new IllegalArgumentException("Invalid HTTP version: " + httpVersion);
        }
    }

    private static void validateStatus(String status) {
        if (!status.matches("\\d{3}")) {
            throw new IllegalArgumentException("Invalid status code: " + status);
        }
    }

    private static void validateResponseBytes(String responseBytes) {
        if (!responseBytes.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid response bytes: " + responseBytes);
        }
    }

    private static void validateUserAgent(String userAgent) {
        if (userAgent.isEmpty()) {
            throw new IllegalArgumentException("Invalid user agent: " + userAgent);
        }
    }

    public static void main(String[] args) {
        String logEntry = "122.176.223.47 - - [05/Feb/2024:08:29:40 +0000] " +
                "\"GET /web-enabled/Enhanced-portal/bifurcated-forecast.js HTTP/1.1\" 200 1684 " +
                "\"-\" \"Opera/9.58 (X11; Linux i686; en-US) Presto/2.12.344 Version/13.00\"";

        Map<String, String> parsedLog = parseLog(logEntry);
        System.out.println("Parsed log data: " + parsedLog);
    }
}
