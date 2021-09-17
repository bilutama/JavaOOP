package ru.academits.biluta.minesweeper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Minesweeper {
    private int fieldWidth;
    private int fieldHeight;

    // Game state
    private int[][] mines;
    private int[][] flags;
    private int[][] openedCells;

    Complexity c;

    public Minesweeper(Complexity c) {
        this.c = c;

        fieldHeight = c.getHeight();
        fieldWidth = c.getWidth();

        mines = new int[fieldHeight][fieldWidth];
        flags = new int[fieldHeight][fieldWidth];
        openedCells = new int[fieldHeight][fieldWidth];
    }

    public void initializeGame() {
        placeMines(mines, c.getMinesCount());
    }

    private void placeMines(int[][] mines, int minesCount) {
        // Get cells indices to place mines
        Integer[] minedCells = getRandomCellsIndices(minesCount);

        // Place mines
        for (int i = 0; i < minesCount; ++i) {
            mines[minedCells[i] / fieldWidth][minedCells[i] % fieldWidth] = 1;
        }

        //TODO: remove
        //printArray(mines);
    }

    public int getAdjacentMinesCount(int x, int y) {
        int adjacentMinesCount = 0;

        for (int j = y - 1; j < y + 2; ++j) {
            if (j >= 0 && j < fieldHeight) {
                for (int i = x - 1; i < x + 2; ++i) {
                    if (i >= 0 && i < fieldWidth) {
                        adjacentMinesCount += mines[j][i];
                    }
                }
            }
        }

        return adjacentMinesCount;
    }

    private Integer[] getRandomCellsIndices(int minesCount) {
        List<Integer> random = IntStream.range(0, fieldHeight * fieldWidth - 1).boxed().collect(Collectors.toList());
        Collections.shuffle(random);

        Integer[] minedCells = new Integer[minesCount];
        new ArrayList<>(random.subList(0, minesCount)).toArray(minedCells);

        return minedCells;
    }

    private void openCells(int x, int y) {
        if (mines[x][y] != 1) {

        }
    }

    // TODO: remove debugging code
    private static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int number : row) {
                System.out.printf("%d ", number);
            }

            System.out.println();
        }
    }
}