package com.jcesarperez.aoc2023;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;

public class Day01Part2 {

    private static final HashMap<String, Integer> NUMBERS = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String input = "src/main/resources/day01_input.txt";

        Day01Part2 day01Part2 = new Day01Part2();

        int result = day01Part2.execute(input);
        System.out.println("Day01 Part2 = " + result);
    }

    public Day01Part2() {
        NUMBERS.put("one", 1);
        NUMBERS.put("two", 2);
        NUMBERS.put("three", 3);
        NUMBERS.put("four", 4);
        NUMBERS.put("five", 5);
        NUMBERS.put("six", 6);
        NUMBERS.put("seven", 7);
        NUMBERS.put("eight", 8);
        NUMBERS.put("nine", 9);
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

        int firstNumber = findFirstNumber(line);
        int lastNumber = findLastNumber(line);
        String lineNumber = "" + firstNumber + lastNumber;
        return Integer.parseInt(lineNumber);
    }

    private int findFirstNumber(String line) {
        StringBuilder builder = new StringBuilder();
        for (char character : line.toCharArray()) {
            if (Character.isDigit(character)) {
                return Character.getNumericValue(character);
            }

            builder.append(character);
            String insideNumber = insideNumber(builder.toString());
            if (NUMBERS.containsKey(insideNumber)) {
                return NUMBERS.get(insideNumber);
            }
        }

        throw new IllegalArgumentException(line + " doesn't contain any number");
    }


    private int findLastNumber(String line) {
        int lastNumber = Integer.MIN_VALUE;

        StringBuilder builder = new StringBuilder();
        char[] linechar = line.toCharArray();
        char character;
        for (int i = linechar.length - 1;  i >= 0; i--) {
            character = linechar[i];
            if (Character.isDigit(character)) {
                return Character.getNumericValue(character);
            }

            builder.insert(0, character);
            String insideNumber = insideNumber(builder.toString());
            if (NUMBERS.containsKey(insideNumber)) {
                return NUMBERS.get(insideNumber);

            }
        }

        throw new IllegalArgumentException(line + " doesn't contain any number");
    }

    private String insideNumber(String str) {
        Set<String> keys = NUMBERS.keySet();
        for(var key: keys) {
            if (str.contains(key)) {
                return key;
            }
        }
        return "";
    }

}
