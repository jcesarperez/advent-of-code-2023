package com.jcesarperez.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Part1Tests {

    private final Day02Part1 part1 = new Day02Part1();

    @Test
    public void testSamplePart1() throws IOException {
        String input = "src/test/resources/day02_part1_sample.txt";

        int result = part1.execute(input);

        assertEquals(8, result);
    }

}
