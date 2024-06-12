package com.example.normalizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobTitleNormalizerTest {

    @Test
    public void testNormalize() {
        JobTitleNormalizer normalizer = new JobTitleNormalizer();

        assertEquals("Software engineer", normalizer.normalize("Java engineer"));
        assertEquals("Software engineer", normalizer.normalize("C# engineer"));
        assertEquals("Accountant", normalizer.normalize("Chief Accountant"));
        assertEquals("Accountant", normalizer.normalize("Accountant"));
    }

    @Test
    public void testNormalizeWithEmptyInput() {
        JobTitleNormalizer normalizer = new JobTitleNormalizer();
        assertThrows(IllegalArgumentException.class, () -> normalizer.normalize(""));
    }

    @Test
    public void testNormalizeWithNullInput() {
        JobTitleNormalizer normalizer = new JobTitleNormalizer();
        assertThrows(IllegalArgumentException.class, () -> normalizer.normalize(null));
    }
}
