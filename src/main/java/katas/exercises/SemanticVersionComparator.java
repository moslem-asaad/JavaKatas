package katas.exercises;

public class SemanticVersionComparator {

    /**
     * Compares two semantic version strings to determine their relative order.
     *
     * @param version1 the first version string (e.g., "MAJOR.MINOR.PATCH")
     * @param version2 the second version string (e.g., "MAJOR.MINOR.PATCH")
     * @return -1 if version1 < version2, 1 if version1 > version2, 0 if they are equal
     */
    public static int compareVersions(String version1, String version2) {
        if (version1.isEmpty() || version2.isEmpty()) {
            throw new IllegalArgumentException("version cannot be empty");
        }
        String[] v1 = version1.split("\\.");
        if (v1.length < 3) throw new IllegalArgumentException("incorrect syntax for version 1");

        String[] v2 = version2.split("\\.");
        if (v2.length < 3) throw new IllegalArgumentException("incorrect syntax for version 2");

        for (int i = 0; i < 3; i++) {
            int v1Part = Integer.parseInt(v1[i]);
            int v2Part = Integer.parseInt(v2[i]);

            if (v1Part < v2Part) return -1;
            else if (v1Part > v2Part) return 1;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(compareVersions("1.0.0", "1.0.1")); // Expected: -1
        System.out.println(compareVersions("2.1.0", "1.9.9")); // Expected: 1
        System.out.println(compareVersions("1.2.3", "1.2.3")); // Expected: 0
    }
}
