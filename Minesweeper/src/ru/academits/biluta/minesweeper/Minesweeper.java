package ru.academits.biluta.minesweeper;

import java.util.*;
import java.util.function.IntPredicate;
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

    public Minesweeper(Level level, Cell firstOpenedCell) {
        this.level = level;

        height = level.getHeight();
        width = level.getWidth();

        int firstOpenedCellX = firstOpenedCell.getX();
        int firstOpenedCellY = firstOpenedCell.getY();

        mines = new int[height][width];
        neighbouringMinesCount = new int[height][width];
        openedCells = new int[height][width];

        placeMines(mines, level.getMinesCount(), firstOpenedCellY / width + firstOpenedCellX);
        countNeighbouringMines();
    }

    private void placeMines(int[][] mines, int minesCount, int excludedIndex) {
        // Get cells indices to place mines except first opened cell
        Integer[] minedCellsIndices = getRandomCellsIndices(minesCount, excludedIndex);

        // Place mines among closed cells
        for (int i = 0; i < minesCount; ++i) {
            mines[minedCellsIndices[i] / width][minedCellsIndices[i] % width] = 1;
        }

        //TODO: remove
        //printArray(mines);
    }

    private void countNeighbouringMines() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (mines[i][j] != 1) {
                    neighbouringMinesCount[i][j] = getAdjacentMinesCount(i, j);
                }
            }
        }
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

    private Integer[] getRandomCellsIndices(int minesCount, int excludedValue) {
        List<Integer> cellsIndices = IntStream.range(0, height * width - 1).filter(x -> x != excludedValue).boxed().collect(Collectors.toList());
        Collections.shuffle(cellsIndices);

        Integer[] minedCellsIndices = new Integer[minesCount];
        new ArrayList<>(cellsIndices.subList(0, minesCount)).toArray(minedCellsIndices);

        return minedCellsIndices;
    }

    private void openCells(Cell cell) {
        Queue<Cell> cellsToOpen = new LinkedList<>();
        cellsToOpen.add(cell);

        while (!cellsToOpen.isEmpty()) {
            Cell current = cellsToOpen.poll();
            int currentX = current.getX();
            int currentY = current.getY();

            if ((neighbouringMinesCount[currentX][currentY] = getAdjacentMinesCount(currentX, currentY)) > 0) {
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