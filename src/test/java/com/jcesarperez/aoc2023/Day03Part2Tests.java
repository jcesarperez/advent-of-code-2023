package com.jcesarperez.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Part2Tests {

    private final Day03Part2 part2 = new Day03Part2();

    @Test
    public void testSample() throws IOException {
        String input = "src/test/resources/day03_sample.txt";

        int result = part2.execute(input);

        assertEquals(467835, result);
    }

}
