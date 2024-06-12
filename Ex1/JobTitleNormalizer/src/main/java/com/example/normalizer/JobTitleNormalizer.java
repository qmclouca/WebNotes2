package com.example.normalizer;

import java.util.Arrays;
import java.util.List;

public class JobTitleNormalizer {
    private static final List<String> NORMALIZED_TITLES = Arrays.asList(
            "Architect", "Software engineer", "Quantity surveyor", "Accountant"
    );

    public String normalize(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input job title cannot be null or empty");
        }

        String bestMatch = null;
        double highestScore = 0.0;

        for (String title : NORMALIZED_TITLES) {
            double score = calculateQualityScore(input, title);
            if (score > highestScore) {
                highestScore = score;
                bestMatch = title;
            }
        }

        return bestMatch;
    }

    private double calculateQualityScore(String input, String title) {

        List<String> inputWords = Arrays.asList(input.toLowerCase().split(" "));
        List<String> titleWords = Arrays.asList(title.toLowerCase().split(" "));

        long intersection = inputWords.stream().filter(titleWords::contains).count();
        long union = inputWords.size() + titleWords.size() - intersection;

        return (double) intersection / union;
    }

    public static void main(String[] args) {
        JobTitleNormalizer normalizer = new JobTitleNormalizer();
        System.out.println(normalizer.normalize("Java engineer")); // Should print "Software engineer"
        System.out.println(normalizer.normalize("C# engineer")); // Should print "Software engineer"
        System.out.println(normalizer.normalize("Chief Accountant")); // Should print "Accountant"
    }
}
