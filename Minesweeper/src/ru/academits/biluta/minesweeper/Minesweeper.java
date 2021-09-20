package ru.academits.biluta.minesweeper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Minesweeper {
    private int width;
    private int height;

    // Game state
    private int[][] mines;
    private int[][] neighbouringMinesCount;
    private int[][] openedCells;

    // Game level
    private Level level;

    public Minesweeper(Level level) {
        this.level = level;

        height = level.getHeight();
        width = level.getWidth();

        mines = new int[height][width];
        neighbouringMinesCount = new int[height][width];
        openedCells = new int[height][width];
    }

    public void initializeGame() {
        placeMines(mines, level.getMinesCount());
    }

    private void placeMines(int[][] mines, int minesCount) {
        // Get cells indices to place mines
        Integer[] minedCells = getRandomCellsIndices(minesCount);

        // Place mines
        for (int i = 0; i < minesCount; ++i) {
            mines[minedCells[i] / width][minedCells[i] % width] = 1;
        }

        //TODO: remove
        //printArray(mines);
    }

    public int getAdjacentMinesCount(int x, int y) {
        int adjacentMinesCount = 0;

        for (int j = y - 1; j < y + 2; ++j) {
            if (j >= 0 && j < height) {
                for (int i = x - 1; i < x + 2; ++i) {
                    if (i >= 0 && i < width) {
                        adjacentMinesCount += mines[j][i];
                    }
                }
            }
        }

        return adjacentMinesCount;
    }

    private Integer[] getRandomCellsIndices(int minesCount) {
        List<Integer> random = IntStream.range(0, height * width - 1).boxed().collect(Collectors.toList());
        Collections.shuffle(random);

        Integer[] minedCells = new Integer[minesCount];
        new ArrayList<>(random.subList(0, minesCount)).toArray(minedCells);

        return minedCells;
    }

    private void openCells(int x, int y) {
        Queue<Cell> cellsToOpen = new LinkedList<>();
        cellsToOpen.add(new Cell(x, y));

        while (!cellsToOpen.isEmpty()) {
            Cell current = cellsToOpen.poll();
            int currentX = current.getX();
            int currentY = current.getY();

            if ((neighbouringMinesCount[currentX][currentY] = getAdjacentMinesCount(currentX,currentY)) > 0){

                for (int j = currentY - 1; j < currentY + 2; ++j) {
                    if (j >= 0 && j < height) {
                        for (int i = currentX - 1; i < currentX + 2; ++i) {
                            if (i >= 0 && i < width) {
                                cellsToOpen.add(new Cell(j, i));
                            }
                        }
                    }
                }

            }
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