package com.jcesarperez.aoc2023;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day01Part1 {

    public static void main(String[] args) throws IOException {
        String input = "src/main/resources/day01_input.txt";

        Day01Part1 day01 = new Day01Part1();

        int result = day01.execute(input);
        System.out.println("Day01 Part1 = " + result);
    }

    public int execute(String inventoryPath) throws IOException {
        var lines = Files.readAllLines(Paths.get(inventoryPath), StandardCharsets.UTF_8);

        var total = 0;

        for (var line : lines) {
            total += calculateLineNumber(line);
        }

        return total;
    }

    private int calculateLineNumber(String line) {
        if (line.isBlank()) {
            return 0;
        }

        char firstNumber = findFirstNumber(line);
        char lastNumber = findLastNumber(line);
        String lineNumber = Character.toString(firstNumber) + lastNumber;
        return Integer.parseInt(lineNumber);
    }

    private char findFirstNumber(String line) {
        for (char character : line.toCharArray()) {
            if (Character.isDigit(character)) {
                return character;
            }
        }

        throw new IllegalArgumentException(line + " doesn't contain any number");
    }

    private char findLastNumber(String line) {
        char lastNumber = Character.MAX_VALUE;
        for (char character : line.toCharArray()) {
            if (Character.isDigit(character)) {
                lastNumber = character;
            }
        }
        if (Character.isDigit(lastNumber)) {
            return lastNumber;
        }

        throw new IllegalArgumentException(line + " doesn't contain any number");
    }

}
