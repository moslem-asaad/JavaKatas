package katas.exercises;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NginxLogsParserTest {

    @Test
    void testValidLogEntry() {
        String logEntry = "122.176.223.47 - - [05/Feb/2024:08:29:40 +0000] " +
                "\"GET /web-enabled/Enhanced-portal/bifurcated-forecast.js HTTP/1.1\" 200 1684 " +
                "\"-\" \"Opera/9.58 (X11; Linux i686; en-US) Presto/2.12.344 Version/13.00\"";

        Map<String, String> parsedLog = NginxLogsParser.parseLog(logEntry);

        assertEquals("122.176.223.47", parsedLog.get("client_ip"));
        assertEquals("05/Feb/2024:08:29:40 +0000", parsedLog.get("date"));
        assertEquals("GET", parsedLog.get("http_method"));
        assertEquals("/web-enabled/Enhanced-portal/bifurcated-forecast.js", parsedLog.get("path"));
        assertEquals("1.1", parsedLog.get("http_version"));
        assertEquals("200", parsedLog.get("status"));
        assertEquals("1684", parsedLog.get("response_bytes"));
        assertEquals("Opera/9.58 (X11; Linux i686; en-US) Presto/2.12.344 Version/13.00", parsedLog.get("user_agent"));
    }




    @Test
    void testInvalidHttpMethod() {
        String logEntry = "122.176.223.47 - - [05/Feb/2024:08:29:40 +0000] " +
                "\"INVALID /web-enabled/path.js HTTP/1.1\" 200 1234 " +
                "\"-\" \"Opera/9.58\"";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            NginxLogsParser.parseLog(logEntry);
        });

        assertEquals("Invalid HTTP method: INVALID", exception.getMessage());
    }

    @Test
    void testInvalidHttpVersion() {
        String logEntry = "122.176.223.47 - - [05/Feb/2024:08:29:40 +0000] " +
                "\"GET /web-enabled/path.js HTTP/3\" 200 1234 " +
                "\"-\" \"Opera/9.58\"";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            NginxLogsParser.parseLog(logEntry);
        });

        assertEquals("Invalid HTTP version: 3", exception.getMessage());
    }

    @Test
    void testInvalidStatus() {
        String logEntry = "122.176.223.47 - - [05/Feb/2024:08:29:40 +0000] " +
                "\"GET /web-enabled/path.js HTTP/1.1\" 9999 1234 " +
                "\"-\" \"Opera/9.58\"";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            NginxLogsParser.parseLog(logEntry);
        });

        assertEquals("Invalid status code: 9999", exception.getMessage());
    }

    
    @Test
    void testEmptyUserAgent() {
        String logEntry = "122.176.223.47 - - [05/Feb/2024:08:29:40 +0000] " +
                "\"GET /web-enabled/path.js HTTP/1.1\" 200 1234 " +
                "\"-\" \"\"";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            NginxLogsParser.parseLog(logEntry);
        });

        assertEquals("Invalid user agent: ", exception.getMessage());
    }

    @Test
    void testInvalidLogFormat() {
        String logEntry = "122.176.223.47 - - 05/Feb/2024:08:29:40 +0000 " +
                "GET /web-enabled/path.js HTTP/1.1 200 1234 - Opera/9.58";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            NginxLogsParser.parseLog(logEntry);
        });

        assertEquals("Invalid log format", exception.getMessage());
    }
}
