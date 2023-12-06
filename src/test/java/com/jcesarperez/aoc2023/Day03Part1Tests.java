package com.jcesarperez.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Part1Tests {

    private final Day03Part1 part1 = new Day03Part1();

    @Test
    public void testSamplePart1() throws IOException {
        String input = "src/test/resources/day03_sample.txt";

        int result = part1.execute(input);

        assertEquals(4361, result);
    }

}
