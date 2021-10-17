package ru.academits.biluta.minesweeper.logic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinesweeperGame implements Game {
    private final int width;
    private final int height;

    // Game state
    private int[][] mines;
    private int[][] nearbyMinesCountMatrix;
    private int[][] revealedCells;
    private int closedCellsCount;
    private final ArrayList<Cell> minedCells;

    // Game level and state
    private final Level level;

    public MinesweeperGame(Level level) {
        this.level = level;

        height = level.getHeight();
        width = level.getWidth();

        closedCellsCount = height * width;
        minedCells = new ArrayList<>(level.getMinesCount());
    }

    public Level getLevel() {
        return level;
    }

    public int getClosedCellsCount() {
        return closedCellsCount;
    }

    public ArrayList<Cell> getMines() {
        return minedCells;
    }

    @Override
    public Deque<Cell> getCellsRangeToReveal(int column, int row) {
        if (closedCellsCount == height * width) {
            // TODO: add timer, start counting
            initializeGame(column, row);
        }

        Deque<Cell> cellsQueue = new LinkedList<>();
        Deque<Cell> cellsRangeToReveal = new LinkedList<>();

        Cell cell = new Cell(column, row, nearbyMinesCountMatrix[row][column]);

        cellsQueue.addLast(cell);
        cellsRangeToReveal.addLast(cell);

        revealedCells[row][column] = 1;

        while (!cellsQueue.isEmpty()) {
            Cell currentCell = cellsQueue.removeFirst();

            int currentCellX = currentCell.getX();
            int currentCellY = currentCell.getY();

            // Collecting neighbouring cells
            if (nearbyMinesCountMatrix[currentCellY][currentCellX] == 0) {
                for (int j = currentCellY - 1; j < currentCellY + 2; ++j) {
                    for (int i = currentCellX - 1; i < currentCellX + 2; ++i) {
                        if (isInRange(i, j) && revealedCells[j][i] == 0) {
                            revealedCells[j][i] = 1;
                            Cell nextCell = new Cell(i, j, nearbyMinesCountMatrix[j][i]);

                            cellsQueue.addLast(nextCell);
                            cellsRangeToReveal.addLast(nextCell);
                        }
                    }
                }
            }
        }

        closedCellsCount -= cellsRangeToReveal.size();
        return cellsRangeToReveal;
    }

    private void initializeGame(int column, int row) {
        mines = new int[height][width];
        nearbyMinesCountMatrix = new int[height][width];
        revealedCells = new int[height][width];

        placeMines(mines, level.getMinesCount(), column + row * width);
        initializeNearbyMinesCountMatrix();

        // TODO: remove println-s
        System.out.println("MINE FIELD");
        printArray(mines);

        System.out.println("NEIGHBOURING MINES COUNT");
        printArray(nearbyMinesCountMatrix);
    }

    private void placeMines(int[][] mines, int minesCount, int excludedIndex) {
        // Get cells indices to place mines except first revealed cell
        Integer[] randomIndices = getRandomCellsIndices(minesCount, excludedIndex);

        // Place mines among closed cells
        for (int i = 0; i < minesCount; ++i) {
            mines[randomIndices[i] / width][randomIndices[i] % width] = 1;
        }
    }

    private Integer[] getRandomCellsIndices(int numbersCount, int excludedNumber) {
        List<Integer> cellsIndices = IntStream.range(0, height * width - 2)
                .filter(index -> index != excludedNumber)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(cellsIndices);

        Integer[] randomCellIndices = new Integer[numbersCount];
        new ArrayList<>(cellsIndices.subList(0, numbersCount)).toArray(randomCellIndices);

        return randomCellIndices;
    }

    private void initializeNearbyMinesCountMatrix() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (mines[i][j] == 1) {
                    nearbyMinesCountMatrix[i][j] = -1;
                    minedCells.add(new Cell(j, i, nearbyMinesCountMatrix[i][j]));
                    continue;
                }

                nearbyMinesCountMatrix[i][j] = getNearbyMinesCount(j, i);
            }
        }
    }

    private int getNearbyMinesCount(int column, int row) {
        int nearbyMinesCount = 0;

        for (int j = column - 1; j < column + 2; ++j) {
            if (j >= 0 && j < width) {
                for (int i = row - 1; i < row + 2; ++i) {
                    if (i >= 0 && i < height) {
                        nearbyMinesCount += mines[i][j];
                    }
                }
            }
        }

        return nearbyMinesCount;
    }

    private boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    // TODO: remove printArray
    private static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int number : row) {
                System.out.printf("%3d ", number);
            }

            System.out.println();
        }
    }
}