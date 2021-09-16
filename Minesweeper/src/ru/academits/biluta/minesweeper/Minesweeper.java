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

    public void initializeGame(){
        placeMines(mines, c.getMinesCount());
    }

    private void placeMines(int[][] mines, int minesCount) {
        // Get cells indices to place mines
        Integer[] minedCells = getRandomCellsIndicesForMines(minesCount);

        // Place mines
        for (int i = 0; i < minesCount; ++i) {
            mines[minedCells[i] / fieldWidth][minedCells[i] % fieldWidth] = 1;
        }

        printArray(mines);
    }

    private Integer[] getRandomCellsIndicesForMines(int minesCount) {
        List<Integer> random = IntStream.range(0, fieldHeight * fieldWidth - 1).boxed().collect(Collectors.toList());
        Collections.shuffle(random);

        Integer[] minedCells = new Integer[minesCount];
        new ArrayList<>(random.subList(0, minesCount)).toArray(minedCells);

        return minedCells;
    }

    private void updateState() {

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