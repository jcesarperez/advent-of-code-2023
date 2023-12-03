package com.jcesarperez.aoc2023;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02Part1 {

    private static HashMap<String, Integer> cubesBag;
    private Pattern cubesPattern;

    public static void main(String[] args) throws IOException {
        String input = "src/main/resources/day02_input.txt";

        Day02Part1 day02 = new Day02Part1();

        int result = day02.execute(input);
        System.out.println("Day02 Part1 = " + result);
    }

    public Day02Part1() {
        cubesBag = new HashMap<>();
        cubesBag.put("red", 12);
        cubesBag.put("green", 13);
        cubesBag.put("blue", 14);

        cubesPattern = Pattern.compile("(\\d+)\\s+(green|blue|red)");
    }

    public int execute(String inputPath) throws IOException {
        var lines = Files.readAllLines(Paths.get(inputPath), StandardCharsets.UTF_8);

        var total = 0;
        for (var line : lines) {
            total += calculateLine(line);
        }

        return total;
    }

    private int calculateLine(String line) {
        String cubes = line.split(":")[1];
        String[] cubesSubsets = cubes.split(";");

        for (var subset: cubesSubsets) {
            Matcher matcher = cubesPattern.matcher(subset);
            while (matcher.find()) {
                int quantity = Integer.parseInt(matcher.group(1));
                String color = matcher.group(2);

                if (quantity > cubesBag.get(color)) {
                    return 0;
                }
            }
        }

        String game = line.split(":")[0];
        int id = Integer.parseInt(game.split(" ")[1]);
        return id;
    }
}
