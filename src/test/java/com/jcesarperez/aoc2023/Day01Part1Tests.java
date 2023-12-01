package com.jcesarperez.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Part1Tests {

    private final Day01Part1 part1 = new Day01Part1();

    @Test
    public void testSamplePart1() throws IOException {
        String input = "src/test/resources/day01_part1_sample.txt";

        int result = part1.execute(input);

        assertEquals(142, result);
    }

}
