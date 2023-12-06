package com.jcesarperez.aoc2023;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day03Part1 {

    private char[][] engineSchema;
    String sNumber;
    boolean isNumber;
    boolean isAdjacent;

    public static void main(String[] args) throws IOException {
        String input = "src/main/resources/day03_input.txt";

        Day03Part1 part1 = new Day03Part1();

        int result = part1.execute(input);
        System.out.println("Day03 Part1 = " + result);
    }

    public int execute(String inputPath) throws IOException {
        buildEngineSchema(inputPath);

        int total = 0;
        for (int i = 0; i < engineSchema.length ; i++) {
            reset();
            for (int j = 0; j < engineSchema[i].length; j++) {
                if (Character.isDigit(engineSchema[i][j])) {
                    isNumber = true;
                    sNumber = sNumber + engineSchema[i][j];
                    isAdjacent = isAdjacent || isAdjacent(i, j);
                }
                if (!Character.isDigit(engineSchema[i][j]) || j == engineSchema[i].length - 1) {
                    if (isAdjacent) {
                        total += Integer.parseInt(sNumber);
                    }
                    if (isNumber) {
                        reset();
                    }
                }
            }
        }

        return total;
    }

    private void reset() {
        isAdjacent = false;
        isNumber = false;
        sNumber = "";
    }

    private void buildEngineSchema(String inputPath) throws IOException {
        var lines = Files.readAllLines(Paths.get(inputPath), StandardCharsets.UTF_8);
        engineSchema = new char[lines.size()][];
        for (int i = 0; i < lines.size() ; i++) {
            engineSchema[i] = lines.get(i).toCharArray();
        }
    }

    private boolean isAdjacent(int i, int j) {
        if (isSymbol(i - 1,j - 1)) {
            return true;
        }
        if (isSymbol(i - 1, j)) {
            return true;
        }
        if (isSymbol(i - 1,j + 1)) {
            return true;
        }
        if (isSymbol(i, j - 1)) {
            return true;
        }
        if (isSymbol(i, j + 1)) {
            return true;
        }
        if (isSymbol(i + 1, j - 1)) {
            return true;
        }
        if (isSymbol(i + 1, j)) {
            return true;
        }
        if (isSymbol(i + 1, j + 1)) {
            return true;
        }
        return false;
    }

    private boolean isSymbol(int i, int j) {
        if (i < 0 || j < 0 || i > engineSchema.length - 1 || j > engineSchema[i].length - 1) {
            return false;
        }

        char c = engineSchema[i][j];
        String symbols = "+-*@#%&/=$";
        return symbols.contains(String.valueOf(c));
    }

}
