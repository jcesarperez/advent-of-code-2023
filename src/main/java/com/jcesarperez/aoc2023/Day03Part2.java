package com.jcesarperez.aoc2023;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day03Part2 {

    private char[][] engineSchema;
    private Map<Position, Integer> numbers = new HashMap<>();
    private List<Position> gears = new ArrayList<>();
    private boolean isNumber = false;
    private String sNumber = "";


    public static void main(String[] args) throws IOException {
        String input = "src/main/resources/day03_input.txt";

        Day03Part2 part2 = new Day03Part2();

        int result = part2.execute(input);
        System.out.println("Day03 Part2 = " + result);
    }

    public int execute(String inputPath) throws IOException {
        buildEngineSchema(inputPath);

        parseEngineSchema();

        int total = 0;
        for (var gear: gears) {
            total += calculateRatio(gear);
        }
        return total;
    }

    private int calculateRatio(Position gear) {
        Set<Integer> adjacents = getAdjacents(gear);
        if (adjacents.size() == 2) {
            return adjacents.stream()
                    .reduce(1, Math::multiplyExact);
        }
        return 0;
    }

    private Set<Integer> getAdjacents(Position gear) {
        Set<Integer> adjacents = new HashSet<>();

        int numRows = engineSchema.length;
        int numCols = engineSchema[0].length;

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int d = 0; d < 8; d++) {
            int newRow = gear.i() + dx[d];
            int newCol = gear.j() + dy[d];

            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols) {
                Integer number = numbers.get(new Position(newRow, newCol));
                if (number != null) {
                    adjacents.add(number);
                }
            }
        }

        return adjacents;
    }

    private void parseEngineSchema() {
        for (int i = 0; i < engineSchema.length ; i++) {
            reset();
            for (int j = 0; j < engineSchema[i].length; j++) {
                if (engineSchema[i][j] == '*') {
                    gears.add(new Position(i, j));
                }
                if (Character.isDigit(engineSchema[i][j])) {
                    isNumber = true;
                    sNumber = sNumber + engineSchema[i][j];
                }
                if (!Character.isDigit(engineSchema[i][j]) || j == engineSchema[i].length - 1) {
                    if (isNumber) {
                        addNumberToNumbers(sNumber, i, j - 1);
                        reset();
                    }
                }
            }
        }

    }

    private void addNumberToNumbers(String sNumber, int i, int j) {
        Integer number = Integer.parseInt(sNumber);
        for (int k = 0; k < sNumber.length(); k++) {
            numbers.put(new Position(i, j - k), number);
        }
    }

    private void reset() {
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

    private record Position(int i, int j){}

}
