package com.jcesarperez.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Part2Tests {

    private final Day02Part2 part2 = new Day02Part2();

    @Test
    public void testSample() throws IOException {
        String input = "src/test/resources/day02_sample.txt";

        int result = part2.execute(input);

        assertEquals(2286, result);
    }

}
