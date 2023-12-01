package com.jcesarperez.aoc2023;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Part2Tests {

    private final Day01Part2 part2 = new Day01Part2();

    @Test
    public void testSamplePart2() throws IOException {
        String input = "src/test/resources/day01_part2_sample.txt";

        int result = part2.execute(input);

        assertEquals(281, result);
    }

}
