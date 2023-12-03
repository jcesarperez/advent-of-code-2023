package com.jcesarperez.aoc2023;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02Part2 {

     private Pattern cubesPattern;

    public static void main(String[] args) throws IOException {
        String input = "src/main/resources/day02_input.txt";

        Day02Part2 day02 = new Day02Part2();

        int result = day02.execute(input);
        System.out.println("Day02 Part2 = " + result);
    }

    public Day02Part2() {
        cubesPattern = Pattern.compile("(\\d+)\\s+(green|blue|red)");
    }

    public int execute(String inputPath) throws IOException {
        var lines = Files.readAllLines(Paths.get(inputPath), StandardCharsets.UTF_8);

        return lines.stream()
                .mapToInt(this::calculateLine)
                .sum();
    }

    private int calculateLine(String line) {
        String cubes = line.split(":")[1];
        String[] cubesSubsets = cubes.split(";");
        Map<String, Integer> max = new HashMap<>();
        max.put("red", 0);
        max.put("green", 0);
        max.put("blue", 0);

        for (var subset: cubesSubsets) {
            Matcher matcher = cubesPattern.matcher(subset);
            while (matcher.find()) {
                int quantity = Integer.parseInt(matcher.group(1));
                String color = matcher.group(2);

                if (quantity > max.get(color)) {
                    max.put(color, quantity);
                }
            }
        }

        return max.values().stream()
                .reduce(1, Math::multiplyExact);
    }
}
